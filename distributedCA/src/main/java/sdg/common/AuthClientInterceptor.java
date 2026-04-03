package sdg.common;

import io.grpc.CallOptions;
import io.grpc.Channel;
import io.grpc.ClientCall;
import io.grpc.ClientInterceptor;
import io.grpc.ForwardingClientCall;
import io.grpc.Metadata;
import io.grpc.MethodDescriptor;

public class AuthClientInterceptor implements ClientInterceptor {
    // Metadata key used by the client before the RPC is sent to the server.
    private static final Metadata.Key<String> API_KEY =
            Metadata.Key.of(AuthConfig.API_KEY_HEADER, Metadata.ASCII_STRING_MARSHALLER);

    @Override
    public <ReqT, RespT> ClientCall<ReqT, RespT> interceptCall(MethodDescriptor<ReqT, RespT> method,
                                                               CallOptions callOptions, Channel next) {
        // The interceptor sits between the stub and the real call.
        // Its job is to add the API key automatically, so the GUI code stays cleaner.
        return new ForwardingClientCall.SimpleForwardingClientCall<>(next.newCall(method, callOptions)) {
            @Override
            public void start(Listener<RespT> responseListener, Metadata headers) {
                // Put auth info into metadata before the call starts.
                headers.put(API_KEY, AuthConfig.API_KEY_VALUE);
                super.start(responseListener, headers);
            }
        };
    }
}
