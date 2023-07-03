package fr.smartds.connmoncash.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Currency;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TransferDto {
    private Currency amount;
    private String receiver;
    private String desc;
}
