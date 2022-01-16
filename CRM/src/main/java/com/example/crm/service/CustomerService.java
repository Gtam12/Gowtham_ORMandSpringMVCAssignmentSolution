package com.example.crm.service;

import java.util.*;

import org.springframework.stereotype.Service;

import com.example.crm.entity.Customers;


@Service
public interface CustomerService {
	
	public List<Customers> findAll();
	
	public Customers findBy(int Id);
	
	public void save(Customers customer);
	
	public void deleteById(int Id);
	
	
}
