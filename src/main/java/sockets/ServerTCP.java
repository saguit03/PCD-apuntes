package sockets;

import BookExample.Book;
import grpc.EjemploExamen;
import grpc.PromocionGrpc;
import io.grpc.Grpc;
import io.grpc.InsecureChannelCredentials;
import io.grpc.ManagedChannel;
import io.grpc.stub.StreamObserver;
import pcd.util.Ventana;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class ServerTCP {
    static final double MIN_PRECIO_PEDIDO = 12.0;
    static final String negAns = "no\n";
    static final String posAns = "yes\n";
    static Ventana v;
    static int puerto = 10_000;
    static int cont;
    static String target = "localhost:9090";
    static ManagedChannel channel;
    static PromocionGrpc.PromocionStub promoStub;
    Socket s;

    static void revisarLibro(Socket s) throws IOException, ClassNotFoundException, InterruptedException {
        ObjectInputStream in = new ObjectInputStream(s.getInputStream());
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
        Book b = (Book) in.readObject();
        cont++;
        String titulo = b.getTitulo();
        v.addText("Libro leido: " + titulo);
        final CountDownLatch finishLatch = new CountDownLatch(1);

        StreamObserver<EjemploExamen.ExamenReply> responseObserver = new StreamObserver<EjemploExamen.ExamenReply>() {
            String respuesta = "";

            @Override
            public void onNext(EjemploExamen.ExamenReply value) {
                v.addText("Promocionado? " + value.getMessage());

                respuesta = value.getMessage();
                try {
                    out.write(respuesta);
                    out.flush();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                finishLatch.countDown();
            }

            @Override
            public void onError(Throwable t) {
                t.printStackTrace();
                finishLatch.countDown();
            }

            @Override
            public void onCompleted() {
                finishLatch.countDown();
            }
        };
        EjemploExamen.ExamenRequest request = EjemploExamen.ExamenRequest.newBuilder().setName(titulo).build();
        promoStub.esPromocion(request, responseObserver);
        //Esperar un minuto a la respuesta
        if (!finishLatch.await(1, TimeUnit.MINUTES)) {
            System.out.print("clientSideStreaming can not finish within 1 minutes \n");
        }

    }

    public static void main(String[] args) {
        v = new Ventana("Servidor TCP", 0, 200);
        channel = Grpc.newChannelBuilder(target, InsecureChannelCredentials.create()).build();
        promoStub = PromocionGrpc.newStub(channel);
        cont = 0;
        try {
            ServerSocket ss = new ServerSocket(puerto);
            while (true) {
                Socket s = ss.accept();
                revisarLibro(s);
                s.close();
            }
//            ss.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
