package jku.bac.grpc.Cart;

import com.google.errorprone.annotations.RestrictedApi;
import jku.bac.grpc.Cart.service.GRPCCartService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GRPCCartEndpoint {

    private GRPCCartService grpcCartService;

    public GRPCCartEndpoint(GRPCCartService grpcCartService){
        this.grpcCartService = grpcCartService;
    }

    @GetMapping("/clearCart")
    public String clearCart(){
        this.grpcCartService.clearCart();
        return "Cart has been ceared";
    }

}
