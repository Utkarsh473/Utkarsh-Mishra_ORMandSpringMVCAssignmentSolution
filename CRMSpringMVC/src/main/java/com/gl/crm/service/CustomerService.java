package com.gl.crm.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gl.crm.model.Customer;

@Service
public interface CustomerService {

	public List<Customer> readAll();

	public Customer readById(int id);

	public void createOrUpdate(Customer cust);

	public void delete(int id);
}
