package com.home.demo;

import com.home.demo.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CustomerResource {

    @Autowired
    private CustomerRepository customerRepository;

    @RequestMapping("/customers/search")
    public List<Customer> findByLastName(@RequestParam("lastName") String lastName){
        List<Customer> customers = customerRepository.findByLastName( lastName );
        return customers;
    }

    @RequestMapping("/customers")
    public List<Customer> findAll(){
        List<Customer> customers = customerRepository.findAll( );
        return customers;
    }
}
