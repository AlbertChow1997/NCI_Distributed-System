package generated.grpc.emission;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.64.0)",
    comments = "Source: emission.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class EmissionAccountingServiceGrpc {

  private EmissionAccountingServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "SDG.Emission.EmissionAccountingService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<generated.grpc.emission.SnapshotRequest,
      generated.grpc.emission.CarbonSnapshot> getGetCarbonSnapshotMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetCarbonSnapshot",
      requestType = generated.grpc.emission.SnapshotRequest.class,
      responseType = generated.grpc.emission.CarbonSnapshot.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<generated.grpc.emission.SnapshotRequest,
      generated.grpc.emission.CarbonSnapshot> getGetCarbonSnapshotMethod() {
    io.grpc.MethodDescriptor<generated.grpc.emission.SnapshotRequest, generated.grpc.emission.CarbonSnapshot> getGetCarbonSnapshotMethod;
    if ((getGetCarbonSnapshotMethod = EmissionAccountingServiceGrpc.getGetCarbonSnapshotMethod) == null) {
      synchronized (EmissionAccountingServiceGrpc.class) {
        if ((getGetCarbonSnapshotMethod = EmissionAccountingServiceGrpc.getGetCarbonSnapshotMethod) == null) {
          EmissionAccountingServiceGrpc.getGetCarbonSnapshotMethod = getGetCarbonSnapshotMethod =
              io.grpc.MethodDescriptor.<generated.grpc.emission.SnapshotRequest, generated.grpc.emission.CarbonSnapshot>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetCarbonSnapshot"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.grpc.emission.SnapshotRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.grpc.emission.CarbonSnapshot.getDefaultInstance()))
              .setSchemaDescriptor(new EmissionAccountingServiceMethodDescriptorSupplier("GetCarbonSnapshot"))
              .build();
        }
      }
    }
    return getGetCarbonSnapshotMethod;
  }

  private static volatile io.grpc.MethodDescriptor<generated.grpc.emission.UsageRecord,
      generated.grpc.emission.BatchSummary> getUploadUsageBatchMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UploadUsageBatch",
      requestType = generated.grpc.emission.UsageRecord.class,
      responseType = generated.grpc.emission.BatchSummary.class,
      methodType = io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
  public static io.grpc.MethodDescriptor<generated.grpc.emission.UsageRecord,
      generated.grpc.emission.BatchSummary> getUploadUsageBatchMethod() {
    io.grpc.MethodDescriptor<generated.grpc.emission.UsageRecord, generated.grpc.emission.BatchSummary> getUploadUsageBatchMethod;
    if ((getUploadUsageBatchMethod = EmissionAccountingServiceGrpc.getUploadUsageBatchMethod) == null) {
      synchronized (EmissionAccountingServiceGrpc.class) {
        if ((getUploadUsageBatchMethod = EmissionAccountingServiceGrpc.getUploadUsageBatchMethod) == null) {
          EmissionAccountingServiceGrpc.getUploadUsageBatchMethod = getUploadUsageBatchMethod =
              io.grpc.MethodDescriptor.<generated.grpc.emission.UsageRecord, generated.grpc.emission.BatchSummary>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UploadUsageBatch"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.grpc.emission.UsageRecord.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.grpc.emission.BatchSummary.getDefaultInstance()))
              .setSchemaDescriptor(new EmissionAccountingServiceMethodDescriptorSupplier("UploadUsageBatch"))
              .build();
        }
      }
    }
    return getUploadUsageBatchMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static EmissionAccountingServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<EmissionAccountingServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<EmissionAccountingServiceStub>() {
        @java.lang.Override
        public EmissionAccountingServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new EmissionAccountingServiceStub(channel, callOptions);
        }
      };
    return EmissionAccountingServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static EmissionAccountingServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<EmissionAccountingServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<EmissionAccountingServiceBlockingStub>() {
        @java.lang.Override
        public EmissionAccountingServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new EmissionAccountingServiceBlockingStub(channel, callOptions);
        }
      };
    return EmissionAccountingServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static EmissionAccountingServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<EmissionAccountingServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<EmissionAccountingServiceFutureStub>() {
        @java.lang.Override
        public EmissionAccountingServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new EmissionAccountingServiceFutureStub(channel, callOptions);
        }
      };
    return EmissionAccountingServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void getCarbonSnapshot(generated.grpc.emission.SnapshotRequest request,
        io.grpc.stub.StreamObserver<generated.grpc.emission.CarbonSnapshot> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetCarbonSnapshotMethod(), responseObserver);
    }

    /**
     */
    default io.grpc.stub.StreamObserver<generated.grpc.emission.UsageRecord> uploadUsageBatch(
        io.grpc.stub.StreamObserver<generated.grpc.emission.BatchSummary> responseObserver) {
      return io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall(getUploadUsageBatchMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service EmissionAccountingService.
   */
  public static abstract class EmissionAccountingServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return EmissionAccountingServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service EmissionAccountingService.
   */
  public static final class EmissionAccountingServiceStub
      extends io.grpc.stub.AbstractAsyncStub<EmissionAccountingServiceStub> {
    private EmissionAccountingServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected EmissionAccountingServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new EmissionAccountingServiceStub(channel, callOptions);
    }

    /**
     */
    public void getCarbonSnapshot(generated.grpc.emission.SnapshotRequest request,
        io.grpc.stub.StreamObserver<generated.grpc.emission.CarbonSnapshot> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetCarbonSnapshotMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<generated.grpc.emission.UsageRecord> uploadUsageBatch(
        io.grpc.stub.StreamObserver<generated.grpc.emission.BatchSummary> responseObserver) {
      return io.grpc.stub.ClientCalls.asyncClientStreamingCall(
          getChannel().newCall(getUploadUsageBatchMethod(), getCallOptions()), responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service EmissionAccountingService.
   */
  public static final class EmissionAccountingServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<EmissionAccountingServiceBlockingStub> {
    private EmissionAccountingServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected EmissionAccountingServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new EmissionAccountingServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public generated.grpc.emission.CarbonSnapshot getCarbonSnapshot(generated.grpc.emission.SnapshotRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetCarbonSnapshotMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service EmissionAccountingService.
   */
  public static final class EmissionAccountingServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<EmissionAccountingServiceFutureStub> {
    private EmissionAccountingServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected EmissionAccountingServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new EmissionAccountingServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<generated.grpc.emission.CarbonSnapshot> getCarbonSnapshot(
        generated.grpc.emission.SnapshotRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetCarbonSnapshotMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_CARBON_SNAPSHOT = 0;
  private static final int METHODID_UPLOAD_USAGE_BATCH = 1;

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
        case METHODID_GET_CARBON_SNAPSHOT:
          serviceImpl.getCarbonSnapshot((generated.grpc.emission.SnapshotRequest) request,
              (io.grpc.stub.StreamObserver<generated.grpc.emission.CarbonSnapshot>) responseObserver);
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
        case METHODID_UPLOAD_USAGE_BATCH:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.uploadUsageBatch(
              (io.grpc.stub.StreamObserver<generated.grpc.emission.BatchSummary>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getGetCarbonSnapshotMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              generated.grpc.emission.SnapshotRequest,
              generated.grpc.emission.CarbonSnapshot>(
                service, METHODID_GET_CARBON_SNAPSHOT)))
        .addMethod(
          getUploadUsageBatchMethod(),
          io.grpc.stub.ServerCalls.asyncClientStreamingCall(
            new MethodHandlers<
              generated.grpc.emission.UsageRecord,
              generated.grpc.emission.BatchSummary>(
                service, METHODID_UPLOAD_USAGE_BATCH)))
        .build();
  }

  private static abstract class EmissionAccountingServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    EmissionAccountingServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return generated.grpc.emission.EmissionProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("EmissionAccountingService");
    }
  }

  private static final class EmissionAccountingServiceFileDescriptorSupplier
      extends EmissionAccountingServiceBaseDescriptorSupplier {
    EmissionAccountingServiceFileDescriptorSupplier() {}
  }

  private static final class EmissionAccountingServiceMethodDescriptorSupplier
      extends EmissionAccountingServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    EmissionAccountingServiceMethodDescriptorSupplier(java.lang.String methodName) {
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
      synchronized (EmissionAccountingServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new EmissionAccountingServiceFileDescriptorSupplier())
              .addMethod(getGetCarbonSnapshotMethod())
              .addMethod(getUploadUsageBatchMethod())
              .build();
        }
      }
    }
    return result;
  }
}
