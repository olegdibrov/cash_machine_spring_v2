package com.my_cash_machine.repository;

import org.springframework.stereotype.Repository;
import com.my_cash_machine.domen.User;
import org.springframework.data.repository.CrudRepository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    User findByUsername(String username);

    User findByEmail(String email);
}