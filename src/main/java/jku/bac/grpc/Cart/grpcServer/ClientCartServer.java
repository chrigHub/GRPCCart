package jku.bac.grpc.Cart.grpcServer;

import io.grpc.Context;
import io.grpc.stub.StreamObserver;
import jku.bac.grpc.Cart.service.GRPCCartService;
import jku.bac.grpc.Cart.util.Util;
import jku.bac.grpc.server.grpcserver.ClcaItem;
import jku.bac.grpc.server.grpcserver.ClientCartServiceGrpc;
import jku.bac.grpc.server.grpcserver.Empty;
import jku.bac.grpc.server.grpcserver.Notification;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

@GrpcService
public class ClientCartServer extends ClientCartServiceGrpc.ClientCartServiceImplBase {
    @Autowired
    private GRPCCartService cartService;

    @Override
    public void toCart(ClcaItem item, StreamObserver<Empty> responseObserver) {
        cartService.addToCart(Util.ClcaItemToItem(item));
        responseObserver.onNext(null);
        responseObserver.onCompleted();
    }

    @Override
    public void checkout(Notification request, StreamObserver<Empty> responseObserver) {
        Context ctx = Context.current().fork();
        ctx.run(() -> {
            cartService.checkout();
        });
        responseObserver.onNext(null);
        responseObserver.onCompleted();
    }
}
