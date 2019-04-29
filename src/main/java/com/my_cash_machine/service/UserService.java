package com.my_cash_machine.service;

import com.my_cash_machine.domen.User;

import java.util.Collection;
import java.util.Optional;


public interface UserService {

    User save(User user);

    Boolean delete(int id);

    User update(User user);

    User findById(int id);

    User findByUserName(String username);


    User findByEmail(String email);

    Collection<User> findAll();
}
