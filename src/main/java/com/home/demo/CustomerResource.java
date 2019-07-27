package com.home.demo;

import com.home.demo.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.Repository;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
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

    @GetMapping("/customers")
    public List<Customer> findAll(){
        List<Customer> customers = customerRepository.findAll( );
        return customers;
    }

    @Autowired
    private EntityManager entityManager;


    @GetMapping("/customers/new")
    @Transactional
    public Customer createCustomerWithGetMethod(@RequestParam("name") String name){
//        Customer c = customerRepository.save( new Customer( "Quang", "Pham" ) );
//        return c;

        String[] names = name.split( " " );

        Customer c = new Customer(names[0], names[1]);
       entityManager.persist( c );
        //customerRepository.deleteById(  11L);

       return c;
    }

    @PostMapping("/customers")
    public Customer createCustomer(@RequestBody Customer c){
        customerRepository.save( c);
        //customerRepository.deleteById(  11L);

        return c;
    }

    @PutMapping("/customers/{id}")
    Customer replaceCustomer(@RequestBody Customer c, @PathVariable Long id ){
            return customerRepository.findById(id)
                .map(customer -> {
                    customer.setFirstName(c.getFirstName());
                    customer.setLastName(c.getLastName());
                    return customerRepository.save(customer);
                })
                .orElseGet(() -> {
                   throw new  IllegalArgumentException();
                });

    }

    @DeleteMapping("/customers/{id}")
    public void deleteCustomer(@PathVariable long id){
        customerRepository.deleteById(id);
    }
}
