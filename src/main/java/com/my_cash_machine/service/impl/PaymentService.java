package com.my_cash_machine.service.impl;

import com.my_cash_machine.domen.Bill;
import com.my_cash_machine.domen.Payment;
import com.my_cash_machine.domen.Product;
import com.my_cash_machine.repository.BillRepository;
import com.my_cash_machine.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.my_cash_machine.repository.PaymentRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private BillRepository billRepository;

    public PaymentService() {
    }

    public Payment save(Payment payment) {
        return paymentRepository.save(payment);
    }

    public Boolean delete(int id) {
        if (paymentRepository.existsById(id)) {
            paymentRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Payment update(Payment payment) {
        return paymentRepository.save(payment);
    }

    public Payment findById(int id) {
        return paymentRepository.findById(id).get();
    }

    public Collection<Payment> findAll() {
        Iterable<Payment> itr = paymentRepository.findAll();
        return (Collection<Payment>) itr;
    }

    @Transactional
    public Payment addProductToBill(Payment payment) {
        paymentRepository.save(payment);
        Optional<Product> optionalProduct = productRepository.findById(payment.getProduct().getId());
        Product product = optionalProduct.get();
        product.setQuantity(product.getQuantity() - payment.getQuantity());
        productRepository.save(product);
        return payment;
    }

    public List<Payment> findPaymentsByBill(Integer idBill) {
        Optional<Bill> optionalBill = billRepository.findById(idBill);
        Bill bill = optionalBill.get();
        return paymentRepository.findAllByBill(bill);
    }

    @Transactional
    public Payment deleteProductFromBill(Payment payment) {
        paymentRepository.delete(payment);
        Optional<Product> optionalProduct = productRepository.findById(payment.getProduct().getId());
        Product product = optionalProduct.get();
        product.setQuantity(product.getQuantity() + payment.getQuantity());
        productRepository.save(product);
        return payment;
    }
}
