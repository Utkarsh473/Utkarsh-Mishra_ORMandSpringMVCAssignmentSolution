package com.gl.crm.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gl.crm.model.Customer;

@Repository
public class CustomerServiceImpl implements CustomerService {

	private SessionFactory sf;

	private Session session;

	public CustomerServiceImpl(SessionFactory sf) {
		// TODO Auto-generated constructor stub
		this.sf = sf;
		this.session = this.sf.openSession();
	}

	@Transactional
	public List<Customer> readAll() {

		String stmt = "from Customer";

		List<Customer> cust = new ArrayList<Customer>();

		Transaction tx = session.beginTransaction();
		cust = session.createQuery(stmt, Customer.class).list();

		for (Customer c : cust) {
			System.out.println(c.toString());
		}
		tx.commit();
		return cust;
	}

	@Transactional
	public Customer readById(int id) {
		Transaction tx = session.beginTransaction();
		Customer c = session.get(Customer.class, id);
		tx.commit();
		return c;
	}

	@Transactional
	public void createOrUpdate(Customer cust) {
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(cust);
		tx.commit();
	}

	@Transactional
	public void delete(int id) {
		Transaction tx = session.beginTransaction();
		Customer cust = session.get(Customer.class, id);

		System.out.println(cust.toString());

		session.delete(cust);
		tx.commit();

	}

}
