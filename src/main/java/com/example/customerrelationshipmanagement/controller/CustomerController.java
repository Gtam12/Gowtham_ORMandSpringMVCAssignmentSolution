package com.example.customerrelationshipmanagement.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import java.util.*;


import com.example.customerrelationshipmanagement.entity.Customers;
import com.example.customerrelationshipmanagement.service.CustomerService;

@Controller
@RequestMapping("/customers")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@RequestMapping("/list")
	public String listCustomers(Model model) {
		List<Customers> customers = customerService.findAll();
		model.addAttribute("Customers", customers);
		return "list-customers";
	}

	@RequestMapping("/showFormforAdd")
	public String showFormforAdd(Model model) {
		Customers customer = new Customers();
		model.addAttribute("Customers",customer);
		return "Customer-form";
	}

	@RequestMapping("showFormforUpdate")
	public String showFormforUpdate(@RequestParam("customerId") int Id, Model model) {
		Customers customer = customerService.findBy(Id);
		model.addAttribute("Customers",customer);
		return "Customer-form";
	}

	@PostMapping("/save")
	public String saveStudent(@RequestParam("id") int id, @RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName, @RequestParam("email") String email) {

		System.out.println(id);
		Customers customer;
		if (id != 0) {
			
			customer = customerService.findBy(id);
			customer.setEmail(email);
			customer.setFirstName(firstName);
			customer.setLastName(lastName);
			

		} else
			customer = new Customers(firstName, lastName, email);
		
		customerService.save(customer);

		return "redirect:/customers/list";
	}

	@RequestMapping("/delete")
	public String delete(@RequestParam("customerId") int id) {
		customerService.deleteById(id);
		return "redirect:/customers/list";
	}

	@RequestMapping(value = "/403")
	public ModelAndView accessDenied(Principal user) {

		ModelAndView model = new ModelAndView();

		if (user != null) {
			model.addObject("msg", "Hi " + user.getName() + "you do not have permission to access this page");
		}

		else {
			model.addObject("msg", "You do not have permission to access thi page!");
		}

		model.setViewName("403");

		return model;

	}

}
