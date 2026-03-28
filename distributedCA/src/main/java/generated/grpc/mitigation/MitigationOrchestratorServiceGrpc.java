package generated.grpc.mitigation;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.64.0)",
    comments = "Source: mitigation.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class MitigationOrchestratorServiceGrpc {

  private MitigationOrchestratorServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "SDG.Mitigation.MitigationOrchestratorService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<generated.grpc.mitigation.ControlCommand,
      generated.grpc.mitigation.ControlEvent> getRunControlLoopMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "RunControlLoop",
      requestType = generated.grpc.mitigation.ControlCommand.class,
      responseType = generated.grpc.mitigation.ControlEvent.class,
      methodType = io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
  public static io.grpc.MethodDescriptor<generated.grpc.mitigation.ControlCommand,
      generated.grpc.mitigation.ControlEvent> getRunControlLoopMethod() {
    io.grpc.MethodDescriptor<generated.grpc.mitigation.ControlCommand, generated.grpc.mitigation.ControlEvent> getRunControlLoopMethod;
    if ((getRunControlLoopMethod = MitigationOrchestratorServiceGrpc.getRunControlLoopMethod) == null) {
      synchronized (MitigationOrchestratorServiceGrpc.class) {
        if ((getRunControlLoopMethod = MitigationOrchestratorServiceGrpc.getRunControlLoopMethod) == null) {
          MitigationOrchestratorServiceGrpc.getRunControlLoopMethod = getRunControlLoopMethod =
              io.grpc.MethodDescriptor.<generated.grpc.mitigation.ControlCommand, generated.grpc.mitigation.ControlEvent>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "RunControlLoop"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.grpc.mitigation.ControlCommand.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.grpc.mitigation.ControlEvent.getDefaultInstance()))
              .setSchemaDescriptor(new MitigationOrchestratorServiceMethodDescriptorSupplier("RunControlLoop"))
              .build();
        }
      }
    }
    return getRunControlLoopMethod;
  }

  private static volatile io.grpc.MethodDescriptor<generated.grpc.mitigation.CancelRequest,
      generated.grpc.mitigation.CancelReply> getCancelStrategyMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CancelStrategy",
      requestType = generated.grpc.mitigation.CancelRequest.class,
      responseType = generated.grpc.mitigation.CancelReply.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<generated.grpc.mitigation.CancelRequest,
      generated.grpc.mitigation.CancelReply> getCancelStrategyMethod() {
    io.grpc.MethodDescriptor<generated.grpc.mitigation.CancelRequest, generated.grpc.mitigation.CancelReply> getCancelStrategyMethod;
    if ((getCancelStrategyMethod = MitigationOrchestratorServiceGrpc.getCancelStrategyMethod) == null) {
      synchronized (MitigationOrchestratorServiceGrpc.class) {
        if ((getCancelStrategyMethod = MitigationOrchestratorServiceGrpc.getCancelStrategyMethod) == null) {
          MitigationOrchestratorServiceGrpc.getCancelStrategyMethod = getCancelStrategyMethod =
              io.grpc.MethodDescriptor.<generated.grpc.mitigation.CancelRequest, generated.grpc.mitigation.CancelReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CancelStrategy"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.grpc.mitigation.CancelRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.grpc.mitigation.CancelReply.getDefaultInstance()))
              .setSchemaDescriptor(new MitigationOrchestratorServiceMethodDescriptorSupplier("CancelStrategy"))
              .build();
        }
      }
    }
    return getCancelStrategyMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static MitigationOrchestratorServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<MitigationOrchestratorServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<MitigationOrchestratorServiceStub>() {
        @java.lang.Override
        public MitigationOrchestratorServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new MitigationOrchestratorServiceStub(channel, callOptions);
        }
      };
    return MitigationOrchestratorServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static MitigationOrchestratorServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<MitigationOrchestratorServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<MitigationOrchestratorServiceBlockingStub>() {
        @java.lang.Override
        public MitigationOrchestratorServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new MitigationOrchestratorServiceBlockingStub(channel, callOptions);
        }
      };
    return MitigationOrchestratorServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static MitigationOrchestratorServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<MitigationOrchestratorServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<MitigationOrchestratorServiceFutureStub>() {
        @java.lang.Override
        public MitigationOrchestratorServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new MitigationOrchestratorServiceFutureStub(channel, callOptions);
        }
      };
    return MitigationOrchestratorServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default io.grpc.stub.StreamObserver<generated.grpc.mitigation.ControlCommand> runControlLoop(
        io.grpc.stub.StreamObserver<generated.grpc.mitigation.ControlEvent> responseObserver) {
      return io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall(getRunControlLoopMethod(), responseObserver);
    }

    /**
     */
    default void cancelStrategy(generated.grpc.mitigation.CancelRequest request,
        io.grpc.stub.StreamObserver<generated.grpc.mitigation.CancelReply> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCancelStrategyMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service MitigationOrchestratorService.
   */
  public static abstract class MitigationOrchestratorServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return MitigationOrchestratorServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service MitigationOrchestratorService.
   */
  public static final class MitigationOrchestratorServiceStub
      extends io.grpc.stub.AbstractAsyncStub<MitigationOrchestratorServiceStub> {
    private MitigationOrchestratorServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MitigationOrchestratorServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new MitigationOrchestratorServiceStub(channel, callOptions);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<generated.grpc.mitigation.ControlCommand> runControlLoop(
        io.grpc.stub.StreamObserver<generated.grpc.mitigation.ControlEvent> responseObserver) {
      return io.grpc.stub.ClientCalls.asyncBidiStreamingCall(
          getChannel().newCall(getRunControlLoopMethod(), getCallOptions()), responseObserver);
    }

    /**
     */
    public void cancelStrategy(generated.grpc.mitigation.CancelRequest request,
        io.grpc.stub.StreamObserver<generated.grpc.mitigation.CancelReply> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCancelStrategyMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service MitigationOrchestratorService.
   */
  public static final class MitigationOrchestratorServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<MitigationOrchestratorServiceBlockingStub> {
    private MitigationOrchestratorServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MitigationOrchestratorServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new MitigationOrchestratorServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public generated.grpc.mitigation.CancelReply cancelStrategy(generated.grpc.mitigation.CancelRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCancelStrategyMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service MitigationOrchestratorService.
   */
  public static final class MitigationOrchestratorServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<MitigationOrchestratorServiceFutureStub> {
    private MitigationOrchestratorServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MitigationOrchestratorServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new MitigationOrchestratorServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<generated.grpc.mitigation.CancelReply> cancelStrategy(
        generated.grpc.mitigation.CancelRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCancelStrategyMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CANCEL_STRATEGY = 0;
  private static final int METHODID_RUN_CONTROL_LOOP = 1;

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
        case METHODID_CANCEL_STRATEGY:
          serviceImpl.cancelStrategy((generated.grpc.mitigation.CancelRequest) request,
              (io.grpc.stub.StreamObserver<generated.grpc.mitigation.CancelReply>) responseObserver);
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
        case METHODID_RUN_CONTROL_LOOP:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.runControlLoop(
              (io.grpc.stub.StreamObserver<generated.grpc.mitigation.ControlEvent>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getRunControlLoopMethod(),
          io.grpc.stub.ServerCalls.asyncBidiStreamingCall(
            new MethodHandlers<
              generated.grpc.mitigation.ControlCommand,
              generated.grpc.mitigation.ControlEvent>(
                service, METHODID_RUN_CONTROL_LOOP)))
        .addMethod(
          getCancelStrategyMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              generated.grpc.mitigation.CancelRequest,
              generated.grpc.mitigation.CancelReply>(
                service, METHODID_CANCEL_STRATEGY)))
        .build();
  }

  private static abstract class MitigationOrchestratorServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    MitigationOrchestratorServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return generated.grpc.mitigation.MitigationProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("MitigationOrchestratorService");
    }
  }

  private static final class MitigationOrchestratorServiceFileDescriptorSupplier
      extends MitigationOrchestratorServiceBaseDescriptorSupplier {
    MitigationOrchestratorServiceFileDescriptorSupplier() {}
  }

  private static final class MitigationOrchestratorServiceMethodDescriptorSupplier
      extends MitigationOrchestratorServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    MitigationOrchestratorServiceMethodDescriptorSupplier(java.lang.String methodName) {
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
      synchronized (MitigationOrchestratorServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new MitigationOrchestratorServiceFileDescriptorSupplier())
              .addMethod(getRunControlLoopMethod())
              .addMethod(getCancelStrategyMethod())
              .build();
        }
      }
    }
    return result;
  }
}
