package fr.smartds.connmoncash.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Currency;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "TRANSFER")
public class Transfer extends Transaction implements Serializable {
    public static final long serializableUID = 1l;

    @Id
    @Column(nullable = false, name = "ID_TRANSACTION")
    private String transaction_id;

    @Column(nullable = false, name = "AMOUNT")
    private Currency amount;

    @Column(nullable = false, name = "RECEIVER")
    private String receiver;

    @Column(nullable = false, name = "MESSAGE")
    private String message;

    @Column(nullable = false, name = "DESCRIPTION")
    private String desc;
}
