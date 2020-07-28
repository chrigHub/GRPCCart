package jku.bac.grpc.Cart.grpcClient;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import jku.bac.grpc.Cart.entity.Item;
import jku.bac.grpc.Cart.util.Util;
import jku.bac.grpc.server.grpcserver.CaOfItem;
import jku.bac.grpc.server.grpcserver.CartOfferingServiceGrpc;
import jku.bac.grpc.server.grpcserver.CartRequest;
import jku.bac.grpc.server.grpcserver.Response;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartOfferingClient {

    private static ManagedChannel channel;

    StreamObserver<Response> buyCartObserver = new StreamObserver<Response>() {

        @Override
        public void onNext(Response response) {
            System.out.println("buyCart Observer is on next!");
        }

        @Override
        public void onError(Throwable throwable) {
            System.out.println("buyCart Observer is on Error!");
            channel.shutdown();
            throwable.printStackTrace();
        }

        @Override
        public void onCompleted() {
            System.out.println("buyCart Observer is on complete!");
            channel.shutdown();
        }
    };

    public void buyCart(List<Item> itemList){
        System.out.println("Buying the Cart!");
        List<CaOfItem> caofList = new ArrayList<>();
        caofList = Util.ItemlistToCaOfItemlist(itemList);
        this.channel = initChannel();
        CartOfferingServiceGrpc.CartOfferingServiceStub stub = CartOfferingServiceGrpc.newStub(channel);
        CartRequest cartRequest = CartRequest.newBuilder()
                .addAllCaOfItem(caofList)
                .build();
        stub.buyCart(cartRequest, buyCartObserver);
    }

    private static ManagedChannel initChannel(){
        return ManagedChannelBuilder.forAddress("localhost", 9091)
                .usePlaintext()
                .build();
    }
}
