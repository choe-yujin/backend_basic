package com.devyujin.chap01.section03;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.math.BigDecimal;
import java.util.Objects;

@Embeddable
public class Money {
    @Column(name = "price_amount", nullable = false, precision = 10, scale = 2)
    private BigDecimal amount;

    @Column(name = "price_currenty", length = 10)
    private String currency;

    protected Money() {
    }

    public Money(BigDecimal amount, String currency) {
        if(amount == null || currency == null || currency.isBlank()) {
            throw new IllegalArgumentException("금액과 통화 정보는 필수입니다");
        }
        this.amount = amount;
        this.currency = currency;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

    public Money add(Money other) {
        if(!this.currency.equals(other.currency)) {
            throw new IllegalArgumentException("통화가 같지 않습니다." + this.currency + "vs " + other.currency);
        }

        return new Money(this.amount.add(other.amount), this.currency);
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return Objects.equals(amount, money.amount) && Objects.equals(currency, money.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, currency);
    }

    @Override
    public String toString() {
        return "Money{" +
                "amount=" + amount +
                ", currency='" + currency + '\'' +
                '}';
    }
}
