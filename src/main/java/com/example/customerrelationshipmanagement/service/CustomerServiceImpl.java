package com.example.customerrelationshipmanagement.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.customerrelationshipmanagement.entity.Customers;


@Repository
public class CustomerServiceImpl implements CustomerService {

	private SessionFactory sessionFactory;
	private Session session;

	@Autowired
	public CustomerServiceImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;

		try {
			this.session = sessionFactory.getCurrentSession();
		} catch (Exception e) {
			this.session = this.sessionFactory.openSession();
		}
	}

	@Transactional
	public List<Customers> findAll() {
		List<Customers> customers = session.createQuery("from Customers", Customers.class).list();

		return customers;
	}

	@Transactional
	public Customers findBy(int Id) {
		// TODO Auto-generated method stub
		return session.get(Customers.class, "id");
	}

	@Transactional
	public void save(Customers customer) {
		session.saveOrUpdate(customer);

	}

	@Transactional
	public void deleteById(int Id) {
		Customers customer = session.get(Customers.class, "Id");
		session.delete(customer);

	}

}
