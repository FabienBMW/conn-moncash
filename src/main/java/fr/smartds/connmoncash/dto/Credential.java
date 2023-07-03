package fr.smartds.connmoncash.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Credential {

    @Value("${credential.client_id}")
    private Long client_id;

    @Value("${credential.client_secret}")
    private  String client_secret;
}
