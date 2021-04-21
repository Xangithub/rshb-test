package ru.app.bank.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties
public class AccountDto {
    private String name;
    private Long number;
    private Integer operation;
}
