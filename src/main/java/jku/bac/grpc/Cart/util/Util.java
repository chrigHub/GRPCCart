package jku.bac.grpc.Cart.util;

import jku.bac.grpc.Cart.entity.Item;
import jku.bac.grpc.server.grpcserver.CaOfItem;
import jku.bac.grpc.server.grpcserver.ClcaItem;

import java.util.ArrayList;
import java.util.List;

public class Util {

    public static Item ClcaItemToItem(ClcaItem clcaItem){
        return new Item(clcaItem.getId(), clcaItem.getLabel(),clcaItem.getPrize(),clcaItem.getAmount());
    }

    public static List<CaOfItem> ItemlistToCaOfItemlist(List<Item> itemList){
        List<CaOfItem> caOfItemList = new ArrayList<>();
        for (Item item : itemList){
            CaOfItem caofItem = CaOfItem.newBuilder()
                    .setId(item.getId())
                    .setLabel(item.getLabel())
                    .setAmount(item.getAmount())
                    .setPrize(item.getPrize())
                    .build();
            caOfItemList.add(caofItem);
        }
        return caOfItemList;
    }
}
