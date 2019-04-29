package com.my_cash_machine.domen;

import com.my_cash_machine.utils.Status;
import lombok.Builder;
import lombok.ToString;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Objects;

@Entity
@ToString
@Table(name = "bills")
@Builder
public class Bill {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer idBill;


    //ToDO change to SQLData
    private String date;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;
    

    @Enumerated(EnumType.STRING)
    private Status billStatus;

    public Bill() {
    }

    public Bill(String date, User user, Status billStatus) {
        this.date = date;
        this.user = user;
        this.billStatus = billStatus;
    }

    public Bill(Integer idBill, String date, User user, Status billStatus) {
        this.idBill = idBill;
        this.date = date;
        this.user = user;
        this.billStatus = billStatus;
    }


    public Integer getIdBill() {
        return idBill;
    }

    public void setIdBill(Integer idBill) {
        this.idBill = idBill;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Status getBillStatus() {
        return billStatus;
    }

    public void setBillStatus(Status billStatus) {
        this.billStatus = billStatus;
    }
}
