package generated.grpc.climaterisk;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.64.0)",
    comments = "Source: climaterisk.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class ClimateRiskAlertServiceGrpc {

  private ClimateRiskAlertServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "SDG.ClimateRisk.ClimateRiskAlertService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<generated.grpc.climaterisk.AlertSubscription,
      generated.grpc.climaterisk.RiskAlert> getSubscribeAlertsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SubscribeAlerts",
      requestType = generated.grpc.climaterisk.AlertSubscription.class,
      responseType = generated.grpc.climaterisk.RiskAlert.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<generated.grpc.climaterisk.AlertSubscription,
      generated.grpc.climaterisk.RiskAlert> getSubscribeAlertsMethod() {
    io.grpc.MethodDescriptor<generated.grpc.climaterisk.AlertSubscription, generated.grpc.climaterisk.RiskAlert> getSubscribeAlertsMethod;
    if ((getSubscribeAlertsMethod = ClimateRiskAlertServiceGrpc.getSubscribeAlertsMethod) == null) {
      synchronized (ClimateRiskAlertServiceGrpc.class) {
        if ((getSubscribeAlertsMethod = ClimateRiskAlertServiceGrpc.getSubscribeAlertsMethod) == null) {
          ClimateRiskAlertServiceGrpc.getSubscribeAlertsMethod = getSubscribeAlertsMethod =
              io.grpc.MethodDescriptor.<generated.grpc.climaterisk.AlertSubscription, generated.grpc.climaterisk.RiskAlert>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SubscribeAlerts"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.grpc.climaterisk.AlertSubscription.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.grpc.climaterisk.RiskAlert.getDefaultInstance()))
              .setSchemaDescriptor(new ClimateRiskAlertServiceMethodDescriptorSupplier("SubscribeAlerts"))
              .build();
        }
      }
    }
    return getSubscribeAlertsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<generated.grpc.climaterisk.RiskRequest,
      generated.grpc.climaterisk.RiskState> getGetCurrentRiskMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetCurrentRisk",
      requestType = generated.grpc.climaterisk.RiskRequest.class,
      responseType = generated.grpc.climaterisk.RiskState.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<generated.grpc.climaterisk.RiskRequest,
      generated.grpc.climaterisk.RiskState> getGetCurrentRiskMethod() {
    io.grpc.MethodDescriptor<generated.grpc.climaterisk.RiskRequest, generated.grpc.climaterisk.RiskState> getGetCurrentRiskMethod;
    if ((getGetCurrentRiskMethod = ClimateRiskAlertServiceGrpc.getGetCurrentRiskMethod) == null) {
      synchronized (ClimateRiskAlertServiceGrpc.class) {
        if ((getGetCurrentRiskMethod = ClimateRiskAlertServiceGrpc.getGetCurrentRiskMethod) == null) {
          ClimateRiskAlertServiceGrpc.getGetCurrentRiskMethod = getGetCurrentRiskMethod =
              io.grpc.MethodDescriptor.<generated.grpc.climaterisk.RiskRequest, generated.grpc.climaterisk.RiskState>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetCurrentRisk"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.grpc.climaterisk.RiskRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.grpc.climaterisk.RiskState.getDefaultInstance()))
              .setSchemaDescriptor(new ClimateRiskAlertServiceMethodDescriptorSupplier("GetCurrentRisk"))
              .build();
        }
      }
    }
    return getGetCurrentRiskMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static ClimateRiskAlertServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ClimateRiskAlertServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ClimateRiskAlertServiceStub>() {
        @java.lang.Override
        public ClimateRiskAlertServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ClimateRiskAlertServiceStub(channel, callOptions);
        }
      };
    return ClimateRiskAlertServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static ClimateRiskAlertServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ClimateRiskAlertServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ClimateRiskAlertServiceBlockingStub>() {
        @java.lang.Override
        public ClimateRiskAlertServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ClimateRiskAlertServiceBlockingStub(channel, callOptions);
        }
      };
    return ClimateRiskAlertServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static ClimateRiskAlertServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ClimateRiskAlertServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ClimateRiskAlertServiceFutureStub>() {
        @java.lang.Override
        public ClimateRiskAlertServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ClimateRiskAlertServiceFutureStub(channel, callOptions);
        }
      };
    return ClimateRiskAlertServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void subscribeAlerts(generated.grpc.climaterisk.AlertSubscription request,
        io.grpc.stub.StreamObserver<generated.grpc.climaterisk.RiskAlert> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSubscribeAlertsMethod(), responseObserver);
    }

    /**
     */
    default void getCurrentRisk(generated.grpc.climaterisk.RiskRequest request,
        io.grpc.stub.StreamObserver<generated.grpc.climaterisk.RiskState> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetCurrentRiskMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service ClimateRiskAlertService.
   */
  public static abstract class ClimateRiskAlertServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return ClimateRiskAlertServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service ClimateRiskAlertService.
   */
  public static final class ClimateRiskAlertServiceStub
      extends io.grpc.stub.AbstractAsyncStub<ClimateRiskAlertServiceStub> {
    private ClimateRiskAlertServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ClimateRiskAlertServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ClimateRiskAlertServiceStub(channel, callOptions);
    }

    /**
     */
    public void subscribeAlerts(generated.grpc.climaterisk.AlertSubscription request,
        io.grpc.stub.StreamObserver<generated.grpc.climaterisk.RiskAlert> responseObserver) {
      io.grpc.stub.ClientCalls.asyncServerStreamingCall(
          getChannel().newCall(getSubscribeAlertsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getCurrentRisk(generated.grpc.climaterisk.RiskRequest request,
        io.grpc.stub.StreamObserver<generated.grpc.climaterisk.RiskState> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetCurrentRiskMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service ClimateRiskAlertService.
   */
  public static final class ClimateRiskAlertServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<ClimateRiskAlertServiceBlockingStub> {
    private ClimateRiskAlertServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ClimateRiskAlertServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ClimateRiskAlertServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public java.util.Iterator<generated.grpc.climaterisk.RiskAlert> subscribeAlerts(
        generated.grpc.climaterisk.AlertSubscription request) {
      return io.grpc.stub.ClientCalls.blockingServerStreamingCall(
          getChannel(), getSubscribeAlertsMethod(), getCallOptions(), request);
    }

    /**
     */
    public generated.grpc.climaterisk.RiskState getCurrentRisk(generated.grpc.climaterisk.RiskRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetCurrentRiskMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service ClimateRiskAlertService.
   */
  public static final class ClimateRiskAlertServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<ClimateRiskAlertServiceFutureStub> {
    private ClimateRiskAlertServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ClimateRiskAlertServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ClimateRiskAlertServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<generated.grpc.climaterisk.RiskState> getCurrentRisk(
        generated.grpc.climaterisk.RiskRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetCurrentRiskMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_SUBSCRIBE_ALERTS = 0;
  private static final int METHODID_GET_CURRENT_RISK = 1;

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
        case METHODID_SUBSCRIBE_ALERTS:
          serviceImpl.subscribeAlerts((generated.grpc.climaterisk.AlertSubscription) request,
              (io.grpc.stub.StreamObserver<generated.grpc.climaterisk.RiskAlert>) responseObserver);
          break;
        case METHODID_GET_CURRENT_RISK:
          serviceImpl.getCurrentRisk((generated.grpc.climaterisk.RiskRequest) request,
              (io.grpc.stub.StreamObserver<generated.grpc.climaterisk.RiskState>) responseObserver);
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
          getSubscribeAlertsMethod(),
          io.grpc.stub.ServerCalls.asyncServerStreamingCall(
            new MethodHandlers<
              generated.grpc.climaterisk.AlertSubscription,
              generated.grpc.climaterisk.RiskAlert>(
                service, METHODID_SUBSCRIBE_ALERTS)))
        .addMethod(
          getGetCurrentRiskMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              generated.grpc.climaterisk.RiskRequest,
              generated.grpc.climaterisk.RiskState>(
                service, METHODID_GET_CURRENT_RISK)))
        .build();
  }

  private static abstract class ClimateRiskAlertServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    ClimateRiskAlertServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return generated.grpc.climaterisk.ClimateRiskProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("ClimateRiskAlertService");
    }
  }

  private static final class ClimateRiskAlertServiceFileDescriptorSupplier
      extends ClimateRiskAlertServiceBaseDescriptorSupplier {
    ClimateRiskAlertServiceFileDescriptorSupplier() {}
  }

  private static final class ClimateRiskAlertServiceMethodDescriptorSupplier
      extends ClimateRiskAlertServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    ClimateRiskAlertServiceMethodDescriptorSupplier(java.lang.String methodName) {
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
      synchronized (ClimateRiskAlertServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new ClimateRiskAlertServiceFileDescriptorSupplier())
              .addMethod(getSubscribeAlertsMethod())
              .addMethod(getGetCurrentRiskMethod())
              .build();
        }
      }
    }
    return result;
  }
}
