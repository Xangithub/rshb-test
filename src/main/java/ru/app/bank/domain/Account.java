package ru.app.bank.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@EntityListeners({AuditingEntityListener.class})
@Accessors(chain = true)
@Setter
@Getter
@NoArgsConstructor
public class Account extends Auditable<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String owner;
    @Column
    private Integer balance;
}
