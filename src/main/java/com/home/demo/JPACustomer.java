package com.home.demo;

import javax.persistence.*;
import java.util.List;

public class JPACustomer {
    @SuppressWarnings( "unchecked" )
    public static void main(String[] args) {
        System.out.println("JPACustomer.main()");
        EntityManagerFactory entityManagerFactory = null;
        EntityManager entityManager = null;
        try{
            entityManagerFactory = Persistence.createEntityManagerFactory( "demo" );
            entityManager = entityManagerFactory.createEntityManager();

            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();

            //create in database
            Customer c = new Customer(" Quang", "Pham" );
            System.out.println(c);
            entityManager.persist( c );
            transaction.commit();


            int id = Math.toIntExact( c.getId() );
            System.out.println("Create Customer with id" + id);
            transaction = entityManager.getTransaction();
            transaction.begin();

            Query query = entityManager.createQuery( "select c from Customer c order by c.lastName" );
            List<Customer> list = query.getResultList();
            System.out.println("There are" + list.size() + "customer:");
            list.forEach( customer -> System.out.println(
                    customer.getFirstName() + " " + customer.getLastName()) );

        }finally {
            if (entityManager != null)
                entityManager.close();
            if (entityManagerFactory !=null)
                entityManagerFactory.close();
        }
    }
}
