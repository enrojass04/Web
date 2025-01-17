package co.edu.poli.mongodb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.poli.mongodb.model.Customer;
import co.edu.poli.mongodb.repository.CustomerRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;

@CrossOrigin(origins = "http://127.0.0.1:4200")
@Api(tags = {"Class: CustomerController"}) //tag defined in SwaggerConfig.java
@RestController // Defines that this class is a spring bean
@RequestMapping("/api/v1/")
public class CustomerController {

	@Autowired
	CustomerRepository customerRepository;
	
	@GetMapping("/Customers")
	@ApiOperation(value = "*** Service Method Get All Customers ***", notes = "*** Get All Customers from MongoDB\\\\WebApp ***")
	@ApiResponses(value = {@ApiResponse(code = 404, message = "*** Error Get All Customers!!! ***")})
	public List<Customer> getAllCustomers(){
		return customerRepository.findAll();
	}
	
	@GetMapping("/Customers/{id}")
	public Customer getCustomerByID(@PathVariable String id) {
		return customerRepository.findById(id).get();
	}
	
	@PostMapping("/Customers")
	public Customer saveCustomer(@RequestBody Customer customer){
		return customerRepository.save(customer);
	}
	
	@PostMapping("/CustomersList")
	public List<Customer> saveListCustomers(@RequestBody List<Customer> customers) {
		return customerRepository.saveAll(customers);
	}
	
	@PutMapping("/Customers/{id}")
	public Customer updateCustomer(@PathVariable String id, @RequestBody Customer customer) {
		
		Customer _customer = customerRepository.findById(id).get();
		
		_customer.setActive(customer.isActive());
		_customer.setBalance (customer.getBalance());
		_customer.setAge (customer.getAge());
		_customer.setName (customer.getName());
		_customer.setGender (customer.getGender());
		_customer.setCompany (customer.getCompany());
		_customer.setEmail (customer.getEmail());
		_customer.setPhone (customer.getPhone());
		_customer.setAddress (customer.getAddress());
		_customer.setSalary (customer.getSalary());
		_customer.setSkills (customer.getSkills());
		_customer.setTransactions (customer.getTransactions());
		
		customerRepository.save(_customer);
		
		return _customer;
	}
	
	@DeleteMapping("/Customers/{id}")
	public Customer deleteCustomerById(@PathVariable String id) {
		Customer _customer = customerRepository.findById(id).get();
		customerRepository.deleteById(id);
		return _customer;
	}
	
	@DeleteMapping("/CustomersList")
	public void deleteAll() {
		customerRepository.deleteAll();
	}
	
	//Query
	@GetMapping("/CustomersQ/{lastname}")
	public List<Customer> findCustomers1(@PathVariable String lastname){
		return customerRepository.findCustomers(lastname);
	}

	//Query
	@GetMapping("/CustomersQ1/{min}/{max}")
	public List<Customer> findCustomers2(@PathVariable double min, @PathVariable double max){
		return customerRepository.findCustomersQ1(min, max);
	}
	
	//Query
	@GetMapping("/CustomersQ2")
	public List<Customer> findCustomers3(){
		return customerRepository.findCustomersQ2();
	}
	
	//Query
	@GetMapping("/CustomersQ3")
	public List<Customer> findCustomers4(){
		return customerRepository.findCustomersQ3();
	}

	//Query
	@GetMapping("/CustomersQ4")
	public List<Customer> findCustomers5(){
		return customerRepository.findCustomersQ4();
	}
	
}