package promocion;

import grpc.EjemploExamen;
import io.grpc.stub.StreamObserver;
import pcd.util.Ventana;

public class PromocionGrpc extends grpc.PromocionGrpc.PromocionImplBase {
    static final int LONGITUD = 20;
    static final String resPositiva = "yes\n";
    static final String resNegativa = "no\n";
    static final Ventana v = new Ventana("Servicio gRPC", 0, 600);

    @Override
    public void esPromocion(EjemploExamen.ExamenRequest request, StreamObserver<EjemploExamen.ExamenReply> responseObserver) {
        String respuesta;
        String titulo = request.getName();
        if (titulo.length() > LONGITUD) {
            respuesta = resPositiva;
        } else {
            respuesta = resNegativa;
        }

        v.addText("Libro recibido del cliente: " + titulo);
        responseObserver.onNext(EjemploExamen.ExamenReply.newBuilder().setMessage(respuesta).build());
        responseObserver.onCompleted();
    }
}
