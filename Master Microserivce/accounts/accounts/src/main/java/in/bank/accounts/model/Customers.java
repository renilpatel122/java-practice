package in.bank.accounts.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Customers {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "customer_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "mobile_number")
    private String mobileNumber;

    @Column(name = "email_id")
    private String emailId;

    @Column(name = "created_date")
    private LocalDate createdDate;


}
