package com.ewallet.userservice.repository;

import com.ewallet.userservice.entities.PaymentUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<PaymentUser,Long> {
    Optional<PaymentUser> findByUsername(String s);
}
