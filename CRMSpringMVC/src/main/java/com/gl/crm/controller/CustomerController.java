package com.gl.crm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.gl.crm.model.Customer;
import com.gl.crm.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerService cs;

	@RequestMapping("/list")
	public String read(Model m) {
		List<Customer> cust = cs.readAll();

		m.addAttribute("customers", cust);

		return "Customer-list";
	}

	@RequestMapping("/showFormForAdd")
	public String addCustomer(Model m) {
		Customer c = new Customer();

		m.addAttribute("customer", c);

		return "Customer-form";
	}

	@RequestMapping("/showFormForUpdate")
	public String updateCustomer(@RequestParam("customerId") int id, Model m) {
		Customer c = cs.readById(id);

		m.addAttribute("customer", c);

		return "Customer-form";
	}

	@PostMapping("/save")
	public String save(@RequestParam("id") int id, @RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName, @RequestParam("email") String email) {
		Customer c;
		if (id != 0) {
			c = cs.readById(id);
			c.setEmail(email);
			c.setFirstName(firstName);
			c.setLastName(lastName);
		}

		else {
			c = new Customer(firstName, lastName, email);
		}

		cs.createOrUpdate(c);

		return "redirect:/customer/list";
	}

	@RequestMapping(value = "/delete", method = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE,
			RequestMethod.PUT })
	public String delete(@RequestParam("id") int id) {
		cs.delete(id);

		return "redirect:/customer/list";
	}

}
