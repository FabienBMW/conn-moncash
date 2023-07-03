package fr.smartds.connmoncash.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PaymentResponse {
    private String path;
    private Payment_token payment_token;
    private Long timestamp;
    private HttpStatus status;
    private String mode;
}
