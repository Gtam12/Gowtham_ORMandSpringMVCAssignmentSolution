package com.example.customerrelationshipmanagement;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import com.example.customerrelationshipmanagement.service.*;
import com.example.customerrelationshipmanagement.entity.*;



public class CustomerRelationshipManagementApplication {


	public static void main(String[] args) {
		SessionFactory sessionFactory = new Configuration().configure("hibernate.config.xml").addAnnotatedClass(Customers.class)
				.buildSessionFactory();
	    CustomerService service = new CustomerServiceImpl(sessionFactory);
	}

}
