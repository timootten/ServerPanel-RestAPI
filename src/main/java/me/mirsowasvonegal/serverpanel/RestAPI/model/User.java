package me.mirsowasvonegal.serverpanel.RestAPI.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@Document("user")
public class User {

    @Id
    @Getter @Setter
    private String id;

    @Getter @Setter
    private String supportid;

    @Getter @Setter
    private String passwordtoken;

    @NotBlank
    @Size(max = 20, min = 4)
    @Getter @Setter
    private String username;

    @NotBlank
    @Size(max = 50, min = 6)
    @Email
    @Getter @Setter
    private String email;

    @NotBlank
    @Size(max = 120, min = 8)
    @Getter @Setter
    private String password;

    @Getter @Setter
    private int rankid;

    @Size(max = 120)
    @Getter @Setter
    private String firstname;

    @Size(max = 120)
    @Getter @Setter
    private String lastname;

    @Size(max = 120)
    @Getter @Setter
    private String street;

    @Size(max = 120)
    @Getter @Setter
    private String housenumber;

    @Size(max = 120)
    @Getter @Setter
    private String city;

    @Size(max = 120)
    @Getter @Setter
    private String country;

    @Size(max = 120)
    @Getter @Setter
    private String zipcode;

    @Size(max = 120)
    @Getter @Setter
    private long created;

    @NotBlank
    @Size(max = 120)
    @Getter @Setter
    private String rankname;

    @NotBlank
    @Getter @Setter
    private String confirmed;

    @Getter @Setter
    private Double credits;

}
