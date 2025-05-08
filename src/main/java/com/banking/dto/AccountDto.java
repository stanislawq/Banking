package com.banking.dto;

import java.math.BigDecimal;

public record AccountDto(String currency, BigDecimal balance) {

}