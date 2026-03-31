package sdg.common;

import io.grpc.Metadata;
import io.grpc.ServerCall;
import io.grpc.ServerCallHandler;
import io.grpc.ServerInterceptor;
import io.grpc.Status;

public class AuthServerInterceptor implements ServerInterceptor {
    private static final Metadata.Key<String> API_KEY =
            Metadata.Key.of(AuthConfig.API_KEY_HEADER, Metadata.ASCII_STRING_MARSHALLER);

    @Override
    public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(ServerCall<ReqT, RespT> call,
                                                                 Metadata headers,
                                                                 ServerCallHandler<ReqT, RespT> next) {
        String apiKey = headers.get(API_KEY);
        if (!AuthConfig.API_KEY_VALUE.equals(apiKey)) {
            call.close(Status.PERMISSION_DENIED.withDescription("Missing or invalid API key"), new Metadata());
            return new ServerCall.Listener<>() {
            };
        }
        return next.startCall(call, headers);
    }
}
