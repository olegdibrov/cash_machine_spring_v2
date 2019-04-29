package com.my_cash_machine.domen;

import lombok.Builder;
import lombok.ToString;

import javax.persistence.*;
import java.util.Objects;

/**
 * Class describing product entity
 *
 * @author Oleg Dibrov
 */

@ToString
@Entity
@Builder
@Table(name="products")
public class Product {

    /**
     * product id field
     */
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "cost")
    private Double cost;

    @Column(name = "quantity")
    private Integer quantity;

    public Product() {
    }

    public Product(Integer id, String name, String description, Double cost, Integer quantity) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.cost = cost;
        this.quantity = quantity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id) &&
                Objects.equals(name, product.name) &&
                Objects.equals(description, product.description) &&
                Objects.equals(cost, product.cost) &&
                Objects.equals(quantity, product.quantity);
    }

    @Override
    public int hashCode() {
        return 31 * id.hashCode() + 31;
    }
}
