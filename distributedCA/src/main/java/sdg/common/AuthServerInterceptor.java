package sdg.common;

import io.grpc.Metadata;
import io.grpc.ServerCall;
import io.grpc.ServerCallHandler;
import io.grpc.ServerInterceptor;
import io.grpc.Status;

public class AuthServerInterceptor implements ServerInterceptor {
    // The server uses the same metadata key as the client.
    private static final Metadata.Key<String> API_KEY =
            Metadata.Key.of(AuthConfig.API_KEY_HEADER, Metadata.ASCII_STRING_MARSHALLER);

    @Override
    public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(ServerCall<ReqT, RespT> call,
                                                                 Metadata headers,
                                                                 ServerCallHandler<ReqT, RespT> next) {
        // Read the API key from metadata.
        String apiKey = headers.get(API_KEY);
        if (!AuthConfig.API_KEY_VALUE.equals(apiKey)) {
            // If auth is wrong, the RPC stops here and does not go into the real service implementation.
            call.close(Status.PERMISSION_DENIED.withDescription("Missing or invalid API key"), new Metadata());
            return new ServerCall.Listener<>() {
            };
        }

        // If auth is correct, continue to the actual gRPC service method.
        return next.startCall(call, headers);
    }
}
