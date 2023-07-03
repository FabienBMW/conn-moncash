package fr.smartds.connmoncash.dto;

import fr.smartds.connmoncash.entities.Transfer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.http.HttpStatus;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TransferResponse {
    private String path;
    private Transfer transfer;
    private Long timestamp;
    private HttpStatus status;
}
