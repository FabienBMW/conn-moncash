package fr.smartds.connmoncash.entities;

import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Table;

@MappedSuperclass
@Table(name = "TRANSACTION")
public abstract class Transaction {

    private String transactionId;
    private String referenceId;
    private String phone;
    private String status;
    private String amount;
    private String currency;
    private String statusCode;
    private String externalId;
    private String partyIdType;
    private String transferDate;
    private String collbackURL;
}
