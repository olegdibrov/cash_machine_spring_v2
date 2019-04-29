package com.my_cash_machine.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.my_cash_machine.domen.Bill;

@Repository
public interface BillRepository extends JpaRepository<Bill, Integer> {

}
