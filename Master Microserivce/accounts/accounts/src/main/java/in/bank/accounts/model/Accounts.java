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
public class Accounts {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "account_type")
    private String accountType;

    @Column(name = "branch")
    private String branch;

    @Column(name = "created_date")
    private LocalDate createdDate;
}
