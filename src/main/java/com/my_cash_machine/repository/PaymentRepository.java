package com.my_cash_machine.repository;

import com.my_cash_machine.domen.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.my_cash_machine.domen.Payment;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {
    List<Payment> findAllByBill(Bill bill);

}
