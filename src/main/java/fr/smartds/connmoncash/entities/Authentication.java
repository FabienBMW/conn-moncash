package fr.smartds.connmoncash.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "AUTHENTICATION")
public class Authentication implements Serializable {
    public static final long serializableUID = 1l;

    @Id
    @Column(nullable = false, name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(nullable = false, name = "TOKEN")
    private String access_token;

    @Column(nullable = false, name = "TOKEN_TYPE")
    private String token_type;

    @Column(nullable = false, name = "EXPIRED_TOKEN")
    private int expires_in;

    @Column(nullable = false, name = "CREATE_TOKEN_DATE")
    private Date create_date;

    @Column(nullable = false, name = "SCOPE")
    private List<String> scope;

    @Column(nullable = false, name = "JTI")
    private String jti;
}
