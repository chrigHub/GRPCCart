package jku.bac.grpc.Cart.service;

import jku.bac.grpc.Cart.entity.Item;
import jku.bac.grpc.Cart.grpcClient.CartOfferingClient;
import jku.bac.grpc.server.grpcserver.ClcaItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GRPCCartService {

    private List<Item> itemList = new ArrayList<>();
    @Autowired
    private CartOfferingClient cartOfferingClient;

    public void addToCart(Item item){
        this.itemList.add(item);
    }

    public void checkout(){
        System.out.println("checkout");
        cartOfferingClient.buyCart(this.itemList);
        this.itemList = new ArrayList<Item>();
    }

    public void clearCart(){
        this.itemList = new ArrayList<>();
    }
}
