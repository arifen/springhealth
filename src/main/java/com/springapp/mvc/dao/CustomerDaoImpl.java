package com.springapp.mvc.dao;

import com.springapp.mvc.model.Customer;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by arifen on 7/5/16.
 */
@Repository("customerDao")
public class CustomerDaoImpl implements CustomerDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void saveCustomer(Customer customer) {
        Session session = this.sessionFactory.getCurrentSession();
        // TODO Auto-generated method stub
        System.out.println("AMLOG:: user: " + customer);
        session.saveOrUpdate(customer);
        System.out.println("AMLOG:: CustomerId: " + customer.getCustomerId());
    }

    @Override
    public List<Customer> findCustomerByUserId(String userId) {
        Session session = this.sessionFactory.getCurrentSession();
        System.out.print("customer Id " + userId);
        Query query = session.createQuery("from Customer where customerId = :userId ");
        query.setParameter("userId", userId);
        List<Customer> list = query.list();
        return list;
    }

    @Override
    public List<Customer> findCustomerByPackageId(long packageId) {
        Session session = this.sessionFactory.getCurrentSession();
        System.out.print("Package Id " + packageId);
        Query query = session.createQuery("from Customer where package_id = :packageId ");
        query.setParameter("packageId", packageId);
        List<Customer> list = query.list();
        return list;
    }

    @Override
    public List<Customer> findAllCustomer() {
        System.out.println("customer dao");
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Customer ");
        List<Customer> list = query.list();
        System.out.print("customerlist size customer dao" + list.size());
        return list;
    }

    @Override
    public Customer findCustomerById(long id) {
        /*
        there is subtle difference between get and load method
        */
        Session session = this.sessionFactory.getCurrentSession();
        return (Customer) session.get(Customer.class, id);
    }

    @Override
    public void deleteCustomer(Customer customer) {
        Session session = this.sessionFactory.getCurrentSession();
        /*
        * first remove customer from package set(list of customer) for delete customer .
        * */
        customer.getaPackage().getCustomerSet().remove(customer);
        session.delete(customer);
    }
}