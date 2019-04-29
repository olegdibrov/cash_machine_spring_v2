package com.my_cash_machine.service.impl;

import com.my_cash_machine.domen.Bill;
import com.my_cash_machine.repository.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class BillService {

    @Autowired
    private BillRepository billRepository;


    public Bill save(Bill bill) {
        return billRepository.save(bill);
    }

    public Boolean delete(int id) {
        if (billRepository.existsById(id)) {
            billRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Bill update(Bill user) {
        return billRepository.save(user);
    }

    public Bill findById(int id) {
        return billRepository.findById(id).get();
    }

    public Collection<Bill> findAll() {
        Iterable<Bill> itr = billRepository.findAll();
        return (Collection<Bill>) itr;
    }


}
