package com.techno2know.friends.service;

import org.springframework.data.repository.CrudRepository;

import com.techno2know.friends.model.Address;

public interface AddressService extends CrudRepository<Address, Integer> {

}
