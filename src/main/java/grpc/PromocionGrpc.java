package grpc;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 *
 */
@javax.annotation.Generated(
        value = "by gRPC proto compiler (version 1.54.0)",
        comments = "Source: EjemploExamen.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class PromocionGrpc {

    public static final String SERVICE_NAME = "Promocion";
    private static final int METHODID_ES_PROMOCION = 0;
    // Static method descriptors that strictly reflect the proto.
    private static volatile io.grpc.MethodDescriptor<grpc.EjemploExamen.ExamenRequest,
            grpc.EjemploExamen.ExamenReply> getEsPromocionMethod;
    private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

    private PromocionGrpc() {
    }

    @io.grpc.stub.annotations.RpcMethod(
            fullMethodName = SERVICE_NAME + '/' + "esPromocion",
            requestType = grpc.EjemploExamen.ExamenRequest.class,
            responseType = grpc.EjemploExamen.ExamenReply.class,
            methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<grpc.EjemploExamen.ExamenRequest,
            grpc.EjemploExamen.ExamenReply> getEsPromocionMethod() {
        io.grpc.MethodDescriptor<grpc.EjemploExamen.ExamenRequest, grpc.EjemploExamen.ExamenReply> getEsPromocionMethod;
        if ((getEsPromocionMethod = PromocionGrpc.getEsPromocionMethod) == null) {
            synchronized (PromocionGrpc.class) {
                if ((getEsPromocionMethod = PromocionGrpc.getEsPromocionMethod) == null) {
                    PromocionGrpc.getEsPromocionMethod = getEsPromocionMethod =
                            io.grpc.MethodDescriptor.<grpc.EjemploExamen.ExamenRequest, grpc.EjemploExamen.ExamenReply>newBuilder()
                                    .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                                    .setFullMethodName(generateFullMethodName(SERVICE_NAME, "esPromocion"))
                                    .setSampledToLocalTracing(true)
                                    .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                            grpc.EjemploExamen.ExamenRequest.getDefaultInstance()))
                                    .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                            grpc.EjemploExamen.ExamenReply.getDefaultInstance()))
                                    .setSchemaDescriptor(new PromocionMethodDescriptorSupplier("esPromocion"))
                                    .build();
                }
            }
        }
        return getEsPromocionMethod;
    }

    /**
     * Creates a new async stub that supports all call types for the service
     */
    public static PromocionStub newStub(io.grpc.Channel channel) {
        io.grpc.stub.AbstractStub.StubFactory<PromocionStub> factory =
                new io.grpc.stub.AbstractStub.StubFactory<PromocionStub>() {
                    @java.lang.Override
                    public PromocionStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
                        return new PromocionStub(channel, callOptions);
                    }
                };
        return PromocionStub.newStub(factory, channel);
    }

    /**
     * Creates a new blocking-style stub that supports unary and streaming output calls on the service
     */
    public static PromocionBlockingStub newBlockingStub(
            io.grpc.Channel channel) {
        io.grpc.stub.AbstractStub.StubFactory<PromocionBlockingStub> factory =
                new io.grpc.stub.AbstractStub.StubFactory<PromocionBlockingStub>() {
                    @java.lang.Override
                    public PromocionBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
                        return new PromocionBlockingStub(channel, callOptions);
                    }
                };
        return PromocionBlockingStub.newStub(factory, channel);
    }

    /**
     * Creates a new ListenableFuture-style stub that supports unary calls on the service
     */
    public static PromocionFutureStub newFutureStub(
            io.grpc.Channel channel) {
        io.grpc.stub.AbstractStub.StubFactory<PromocionFutureStub> factory =
                new io.grpc.stub.AbstractStub.StubFactory<PromocionFutureStub>() {
                    @java.lang.Override
                    public PromocionFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
                        return new PromocionFutureStub(channel, callOptions);
                    }
                };
        return PromocionFutureStub.newStub(factory, channel);
    }

    public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
        return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
                .addMethod(
                        getEsPromocionMethod(),
                        io.grpc.stub.ServerCalls.asyncUnaryCall(
                                new MethodHandlers<
                                        grpc.EjemploExamen.ExamenRequest,
                                        grpc.EjemploExamen.ExamenReply>(
                                        service, METHODID_ES_PROMOCION)))
                .build();
    }

    public static io.grpc.ServiceDescriptor getServiceDescriptor() {
        io.grpc.ServiceDescriptor result = serviceDescriptor;
        if (result == null) {
            synchronized (PromocionGrpc.class) {
                result = serviceDescriptor;
                if (result == null) {
                    serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
                            .setSchemaDescriptor(new PromocionFileDescriptorSupplier())
                            .addMethod(getEsPromocionMethod())
                            .build();
                }
            }
        }
        return result;
    }

    /**
     *
     */
    public interface AsyncService {

        /**
         *
         */
        default void esPromocion(grpc.EjemploExamen.ExamenRequest request,
                                 io.grpc.stub.StreamObserver<grpc.EjemploExamen.ExamenReply> responseObserver) {
            io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getEsPromocionMethod(), responseObserver);
        }
    }

    /**
     * Base class for the server implementation of the service Promocion.
     */
    public static abstract class PromocionImplBase
            implements io.grpc.BindableService, AsyncService {

        @java.lang.Override
        public final io.grpc.ServerServiceDefinition bindService() {
            return PromocionGrpc.bindService(this);
        }
    }

    /**
     * A stub to allow clients to do asynchronous rpc calls to service Promocion.
     */
    public static final class PromocionStub
            extends io.grpc.stub.AbstractAsyncStub<PromocionStub> {
        private PromocionStub(
                io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            super(channel, callOptions);
        }

        @java.lang.Override
        protected PromocionStub build(
                io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            return new PromocionStub(channel, callOptions);
        }

        /**
         *
         */
        public void esPromocion(grpc.EjemploExamen.ExamenRequest request,
                                io.grpc.stub.StreamObserver<grpc.EjemploExamen.ExamenReply> responseObserver) {
            io.grpc.stub.ClientCalls.asyncUnaryCall(
                    getChannel().newCall(getEsPromocionMethod(), getCallOptions()), request, responseObserver);
        }
    }

    /**
     * A stub to allow clients to do synchronous rpc calls to service Promocion.
     */
    public static final class PromocionBlockingStub
            extends io.grpc.stub.AbstractBlockingStub<PromocionBlockingStub> {
        private PromocionBlockingStub(
                io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            super(channel, callOptions);
        }

        @java.lang.Override
        protected PromocionBlockingStub build(
                io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            return new PromocionBlockingStub(channel, callOptions);
        }

        /**
         *
         */
        public grpc.EjemploExamen.ExamenReply esPromocion(grpc.EjemploExamen.ExamenRequest request) {
            return io.grpc.stub.ClientCalls.blockingUnaryCall(
                    getChannel(), getEsPromocionMethod(), getCallOptions(), request);
        }
    }

    /**
     * A stub to allow clients to do ListenableFuture-style rpc calls to service Promocion.
     */
    public static final class PromocionFutureStub
            extends io.grpc.stub.AbstractFutureStub<PromocionFutureStub> {
        private PromocionFutureStub(
                io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            super(channel, callOptions);
        }

        @java.lang.Override
        protected PromocionFutureStub build(
                io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            return new PromocionFutureStub(channel, callOptions);
        }

        /**
         *
         */
        public com.google.common.util.concurrent.ListenableFuture<grpc.EjemploExamen.ExamenReply> esPromocion(
                grpc.EjemploExamen.ExamenRequest request) {
            return io.grpc.stub.ClientCalls.futureUnaryCall(
                    getChannel().newCall(getEsPromocionMethod(), getCallOptions()), request);
        }
    }

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
                case METHODID_ES_PROMOCION:
                    serviceImpl.esPromocion((grpc.EjemploExamen.ExamenRequest) request,
                            (io.grpc.stub.StreamObserver<grpc.EjemploExamen.ExamenReply>) responseObserver);
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

    private static abstract class PromocionBaseDescriptorSupplier
            implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
        PromocionBaseDescriptorSupplier() {
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
            return grpc.EjemploExamen.getDescriptor();
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
            return getFileDescriptor().findServiceByName("Promocion");
        }
    }

    private static final class PromocionFileDescriptorSupplier
            extends PromocionBaseDescriptorSupplier {
        PromocionFileDescriptorSupplier() {
        }
    }

    private static final class PromocionMethodDescriptorSupplier
            extends PromocionBaseDescriptorSupplier
            implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
        private final String methodName;

        PromocionMethodDescriptorSupplier(String methodName) {
            this.methodName = methodName;
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
            return getServiceDescriptor().findMethodByName(methodName);
        }
    }
}
