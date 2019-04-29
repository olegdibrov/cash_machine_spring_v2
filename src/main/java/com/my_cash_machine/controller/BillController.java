package com.my_cash_machine.controller;

import com.my_cash_machine.domen.Bill;
import com.my_cash_machine.domen.Payment;
import com.my_cash_machine.domen.User;
import com.my_cash_machine.service.UserService;
import com.my_cash_machine.service.impl.PaymentService;
import com.my_cash_machine.utils.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.my_cash_machine.domen.Product;
import com.my_cash_machine.service.impl.BillService;
import com.my_cash_machine.service.impl.ProductService;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Controller
public class BillController {

    @Autowired
    private BillService billService;

    @Autowired
    private ProductService productService;


    @Autowired
    private UserService userService;

    @Autowired
    private PaymentService paymentService;


    //ToDO save bill (idBill)  in session
    @PostMapping("bill/new_bill")
    public String newBill(Model model, Principal principal, HttpSession session) {
        Optional optionalBill = Optional.ofNullable(session.getAttribute("bill"));
        if (!optionalBill.isPresent()) {
            Bill bill = Bill.builder()
                    .date(LocalDate.now().toString())
                    .user(userService.findByUserName(principal.getName()))
                    .billStatus(Status.CREATED)
                    .build();
            billService.save(bill);
            session.setAttribute("bill", bill);
        }
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "bill";
    }


    @PostMapping("bill/add_product")
    public String addProductToCart(HttpSession session, @RequestParam(name = "idProduct") Integer idProduct,
                                   @RequestParam(name = "quantity") Integer quantity, Model model) {
        Bill bill = (Bill) session.getAttribute("bill");
        System.out.println(idProduct);
        System.out.println(quantity);
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        Payment payment = Payment.builder()
                .bill(billService.findById(bill.getIdBill()))
                .product(productService.findById(idProduct))
                .quantity(quantity)
                .build();
        paymentService.addProductToBill(payment);
        return "bill";
    }

    @PostMapping("/bill/showBill")
    public String showPaymentsInBill(HttpSession session, Model model) {
        Bill bill = (Bill) session.getAttribute("bill");
        List<Payment> payments = paymentService.findPaymentsByBill(bill.getIdBill());
        model.addAttribute("payments", payments);
        return "view_bill";
    }

    @PostMapping("/bill/deleteProduct")
    public String deleteProduct(@RequestParam(name = "payment") Integer idPayment, Model model, HttpSession session) {
        paymentService.deleteProductFromBill(paymentService.findById(idPayment));
        List<Payment> payments = paymentService.findPaymentsByBill(((Bill)session.getAttribute("bill")).getIdBill());
        model.addAttribute("payments", payments);
        return "view_bill";
    }

    @GetMapping("bill/showBill/close_bill")
    public String closeBill(HttpSession session) {
        Optional<Bill> optionalBill = Optional.ofNullable((Bill) session.getAttribute("bill"));
        optionalBill.ifPresent(bill -> {
            bill.setBillStatus(Status.FINISHED);
            billService.update(bill);
        });
        session.removeAttribute("bill");
        return "redirect:/home";
    }

    @PostMapping("bill/viw_open_bill_page")
    public String openBillPage(Model model) {
        Collection<Bill> bills =  billService.findAll();
        model.addAttribute("bills", bills);
        return "open_bill";
    }

    @PostMapping("/bill/open_bill")
    public String openBill(@RequestParam(name = "idBill") Integer idBill, HttpSession session, Model model) {
        Bill bill = billService.findById(idBill);
        session.setAttribute("bill", bill);
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "bill";
    }
}


