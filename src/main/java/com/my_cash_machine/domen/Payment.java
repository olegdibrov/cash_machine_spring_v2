package com.my_cash_machine.domen;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.ToString;

import javax.persistence.*;
import java.util.Objects;

@ToString
@Entity
@Builder
@AllArgsConstructor
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer idPayment;

    @ManyToOne
    @JoinColumn(name = "id_bill")
    private Bill bill;

    @ManyToOne
    @JoinColumn(name = "id_product")
    private Product product;

    @Column(name = "quantity")
    private Integer quantity;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return Objects.equals(idPayment, payment.idPayment) &&
                Objects.equals(bill, payment.bill) &&
                Objects.equals(product, payment.product) &&
                Objects.equals(quantity, payment.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPayment, bill, product, quantity);
    }

    public Integer getIdPayment() {
        return idPayment;
    }

    public void setIdPayment(Integer idPayment) {
        this.idPayment = idPayment;
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Payment(Bill bill, Product product, Integer quantity) {
        this.bill = bill;
        this.product = product;
        this.quantity = quantity;
    }

    public Payment() {
    }
}

