package com.epam.jdi.bookstore.repository.user;

import com.epam.jdi.bookstore.model.user.Address;
import com.epam.jdi.bookstore.model.user.User;
import com.epam.jdi.bookstore.repository.BaseRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface AddressRepository extends BaseRepository<Address, Long> {

    List<Address> getAddressByUser(User user);
}
