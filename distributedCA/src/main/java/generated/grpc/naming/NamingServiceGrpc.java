package generated.grpc.naming;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.64.0)",
    comments = "Source: naming.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class NamingServiceGrpc {

  private NamingServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "SDG.Naming.NamingService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<generated.grpc.naming.ServiceInfo,
      generated.grpc.naming.RegisterReply> getRegisterMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Register",
      requestType = generated.grpc.naming.ServiceInfo.class,
      responseType = generated.grpc.naming.RegisterReply.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<generated.grpc.naming.ServiceInfo,
      generated.grpc.naming.RegisterReply> getRegisterMethod() {
    io.grpc.MethodDescriptor<generated.grpc.naming.ServiceInfo, generated.grpc.naming.RegisterReply> getRegisterMethod;
    if ((getRegisterMethod = NamingServiceGrpc.getRegisterMethod) == null) {
      synchronized (NamingServiceGrpc.class) {
        if ((getRegisterMethod = NamingServiceGrpc.getRegisterMethod) == null) {
          NamingServiceGrpc.getRegisterMethod = getRegisterMethod =
              io.grpc.MethodDescriptor.<generated.grpc.naming.ServiceInfo, generated.grpc.naming.RegisterReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Register"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.grpc.naming.ServiceInfo.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.grpc.naming.RegisterReply.getDefaultInstance()))
              .setSchemaDescriptor(new NamingServiceMethodDescriptorSupplier("Register"))
              .build();
        }
      }
    }
    return getRegisterMethod;
  }

  private static volatile io.grpc.MethodDescriptor<generated.grpc.naming.DiscoverRequest,
      generated.grpc.naming.DiscoverReply> getDiscoverMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Discover",
      requestType = generated.grpc.naming.DiscoverRequest.class,
      responseType = generated.grpc.naming.DiscoverReply.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<generated.grpc.naming.DiscoverRequest,
      generated.grpc.naming.DiscoverReply> getDiscoverMethod() {
    io.grpc.MethodDescriptor<generated.grpc.naming.DiscoverRequest, generated.grpc.naming.DiscoverReply> getDiscoverMethod;
    if ((getDiscoverMethod = NamingServiceGrpc.getDiscoverMethod) == null) {
      synchronized (NamingServiceGrpc.class) {
        if ((getDiscoverMethod = NamingServiceGrpc.getDiscoverMethod) == null) {
          NamingServiceGrpc.getDiscoverMethod = getDiscoverMethod =
              io.grpc.MethodDescriptor.<generated.grpc.naming.DiscoverRequest, generated.grpc.naming.DiscoverReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Discover"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.grpc.naming.DiscoverRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.grpc.naming.DiscoverReply.getDefaultInstance()))
              .setSchemaDescriptor(new NamingServiceMethodDescriptorSupplier("Discover"))
              .build();
        }
      }
    }
    return getDiscoverMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static NamingServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<NamingServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<NamingServiceStub>() {
        @java.lang.Override
        public NamingServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new NamingServiceStub(channel, callOptions);
        }
      };
    return NamingServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static NamingServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<NamingServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<NamingServiceBlockingStub>() {
        @java.lang.Override
        public NamingServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new NamingServiceBlockingStub(channel, callOptions);
        }
      };
    return NamingServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static NamingServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<NamingServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<NamingServiceFutureStub>() {
        @java.lang.Override
        public NamingServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new NamingServiceFutureStub(channel, callOptions);
        }
      };
    return NamingServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void register(generated.grpc.naming.ServiceInfo request,
        io.grpc.stub.StreamObserver<generated.grpc.naming.RegisterReply> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getRegisterMethod(), responseObserver);
    }

    /**
     */
    default void discover(generated.grpc.naming.DiscoverRequest request,
        io.grpc.stub.StreamObserver<generated.grpc.naming.DiscoverReply> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getDiscoverMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service NamingService.
   */
  public static abstract class NamingServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return NamingServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service NamingService.
   */
  public static final class NamingServiceStub
      extends io.grpc.stub.AbstractAsyncStub<NamingServiceStub> {
    private NamingServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected NamingServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new NamingServiceStub(channel, callOptions);
    }

    /**
     */
    public void register(generated.grpc.naming.ServiceInfo request,
        io.grpc.stub.StreamObserver<generated.grpc.naming.RegisterReply> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getRegisterMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void discover(generated.grpc.naming.DiscoverRequest request,
        io.grpc.stub.StreamObserver<generated.grpc.naming.DiscoverReply> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getDiscoverMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service NamingService.
   */
  public static final class NamingServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<NamingServiceBlockingStub> {
    private NamingServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected NamingServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new NamingServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public generated.grpc.naming.RegisterReply register(generated.grpc.naming.ServiceInfo request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getRegisterMethod(), getCallOptions(), request);
    }

    /**
     */
    public generated.grpc.naming.DiscoverReply discover(generated.grpc.naming.DiscoverRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getDiscoverMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service NamingService.
   */
  public static final class NamingServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<NamingServiceFutureStub> {
    private NamingServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected NamingServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new NamingServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<generated.grpc.naming.RegisterReply> register(
        generated.grpc.naming.ServiceInfo request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getRegisterMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<generated.grpc.naming.DiscoverReply> discover(
        generated.grpc.naming.DiscoverRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getDiscoverMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_REGISTER = 0;
  private static final int METHODID_DISCOVER = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AsyncService serviceImpl;
    private final int methodId;

    MethodHandlers(AsyncService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_REGISTER:
          serviceImpl.register((generated.grpc.naming.ServiceInfo) request,
              (io.grpc.stub.StreamObserver<generated.grpc.naming.RegisterReply>) responseObserver);
          break;
        case METHODID_DISCOVER:
          serviceImpl.discover((generated.grpc.naming.DiscoverRequest) request,
              (io.grpc.stub.StreamObserver<generated.grpc.naming.DiscoverReply>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getRegisterMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              generated.grpc.naming.ServiceInfo,
              generated.grpc.naming.RegisterReply>(
                service, METHODID_REGISTER)))
        .addMethod(
          getDiscoverMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              generated.grpc.naming.DiscoverRequest,
              generated.grpc.naming.DiscoverReply>(
                service, METHODID_DISCOVER)))
        .build();
  }

  private static abstract class NamingServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    NamingServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return generated.grpc.naming.NamingProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("NamingService");
    }
  }

  private static final class NamingServiceFileDescriptorSupplier
      extends NamingServiceBaseDescriptorSupplier {
    NamingServiceFileDescriptorSupplier() {}
  }

  private static final class NamingServiceMethodDescriptorSupplier
      extends NamingServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    NamingServiceMethodDescriptorSupplier(java.lang.String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (NamingServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new NamingServiceFileDescriptorSupplier())
              .addMethod(getRegisterMethod())
              .addMethod(getDiscoverMethod())
              .build();
        }
      }
    }
    return result;
  }
}
