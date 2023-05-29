package promocion;

import io.grpc.Grpc;
import io.grpc.InsecureServerCredentials;
import io.grpc.Server;
import pcd.util.Ventana;

import java.io.IOException;

public class ServerGRPC {
    final static int puerto = 9090;
    public static Ventana v;

    public static void main(String[] args) throws IOException, InterruptedException {
        Server server = Grpc.newServerBuilderForPort(puerto, InsecureServerCredentials.create())
                .addService(new PromocionGrpc())
                .build();
//       v = new Ventana("Servidor gRPC", 500, 600);

        server.start();
        System.out.println("ControlVentasServer iniciado en " + server.getPort());
        server.awaitTermination();
    }
}
