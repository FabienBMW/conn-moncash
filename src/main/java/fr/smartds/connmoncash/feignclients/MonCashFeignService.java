package fr.smartds.connmoncash.feignclients;


import feign.Headers;
import fr.smartds.connmoncash.dto.PaymentResponse;
import fr.smartds.connmoncash.dto.TransferResponse;
import fr.smartds.connmoncash.entities.Authentication;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Currency;

@FeignClient(
        value = "connMonCash",
        url = "${conn-moncash.url}",
        path = "${conn-moncash.path}",
        configuration = FeignConfig.class
)

public interface MonCashFeignService {

    @PostMapping(
            name = "auth",
            value = "/oauth/token",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Headers( "Accept: application/json" )
    Authentication authentication(
            @RequestParam Long client_id,
            @RequestParam String client_secret
    );

    @PostMapping(
            name = "payment",
            value = "/v1/CreatePayment",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Headers("content-type: application/json")
    PaymentResponse payment(
            @RequestParam Currency amount,
            @RequestParam Long orderId,
            @RequestHeader(name = "Authorization", required = true) String token
            );

    @PostMapping(
            name = "transfert",
            value = "/v1/Transfert",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Headers("content-type: application/json")
    TransferResponse transfer(
            @RequestParam Currency amount,
            @RequestParam String receiver,
            @RequestParam String desc,
            @RequestHeader(name="Authorization", required=true) String token
            );
}
