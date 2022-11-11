package com.Shopping.Services;

import java.util.Optional;

import javax.crypto.Cipher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Shopping.Exception.CustomerException;
import com.Shopping.Exception.LoginException;
import com.Shopping.Model.Address;
import com.Shopping.Model.Cart;
import com.Shopping.Model.CurrentUserSession;
import com.Shopping.Model.Customer;
import com.Shopping.Model.Product;
import com.Shopping.Model.SellerProducts;
import com.Shopping.Repository.AddressRepo;
import com.Shopping.Repository.CurrentUserSessionRepo;
import com.Shopping.Repository.CustomerRepo;
import com.Shopping.Repository.ProductRepo;
import com.Shopping.Repository.SellerProductRepo;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepo cr;

	@Autowired
	private AddressRepo ar;
	@Autowired
	private ProductRepo pr;
	@Autowired
	private SellerProductRepo spr;

	@Autowired
	private CurrentUserSessionRepo cusr;

	@Override
	public Customer createAccount(Customer cus) throws CustomerException {
		
		
		Customer c = cr.save(cus);

		return c;

	}

	@Override
	public Address setTheAddress(Address add, Integer customerId,String key) throws CustomerException, LoginException {
		Optional<Customer> co = cr.findById(customerId);
		if (co.isPresent()) {
			Customer c = co.get();
			
			//login----->
			
			CurrentUserSession RunningSession = cusr.findByUuid(key);

			if (RunningSession == null) {
				throw new LoginException("Please provide a valid key");
			}
			
			if(c.getCustomerId()!=RunningSession.getUserId()) {
				throw new CustomerException("Please login first");
			}
			
			//<--------login end
			
			c.setAddress(add);

//			add.setCustomer(c);

			Address address = ar.save(add);
			ar.save(address);

			return add;
		} else
			throw new CustomerException("Customer id is wrong");
	}

	@Override
	public String addProduct(Integer pid, Integer cusId, String key) throws CustomerException, LoginException { /// adding																									/// cart
		Optional<SellerProducts> po = spr.findById(pid);
		Customer customer = cr.findByCustomerId(cusId);

		//login----->
		System.out.println(po.get());
		System.out.println(cr);
		CurrentUserSession RunningSession = cusr.findByUuid(key);

		if (RunningSession == null) {
			throw new LoginException("Please provide a valid key");
		}
		
		if (po.isPresent() && customer != null &&customer.getCustomerId()==RunningSession.getUserId()) {

			SellerProducts sp = po.get();
			Product p = new Product();
System.out.println("================= Inside add product =====================");
			p.setProductId(sp.getProductId());
			p.setCategory(sp.getCategory());
			p.setColor(sp.getCategory());
			p.setDimension(sp.getDimension());
			p.setManufacturer(sp.getManufacutrer());
			p.setPrice(sp.getPrice());
			p.setProductName(sp.getProductName());
			p.setQuantity(sp.getQuantity());
			p.setSpecification(sp.getSpecification());

			if (customer.getCart() == null) {
				Cart cart = new Cart();
				cart.getProductList().add(p);
				customer.setCart(cart);
				cart.setCustomer(customer);

//				customer.getCart().getProductList().add(p);
				cr.save(customer);
			} else {
				System.out.println("###########################################################################");
//				customer.getCart().setCustomer(customer);
				
				Cart c=customer.getCart();
				c.getProductList().add(p);
				customer.setCart(c);
//				customer.getCart().getProductList().add(p);
				cr.save(customer);

//				System.out.println(customer.getCart().getProductList());

				System.out.println("###########################################################################");
			}

			return "Added to Cart ";
		}
		if(customer.getCustomerId()!=RunningSession.getUserId()) {
			throw new CustomerException("Please login first");
		}

		return "Not Found";
	}

	@Override
	public Customer updatecustomer(Customer customer, String key) throws LoginException, CustomerException {

		CurrentUserSession RunningSession = cusr.findByUuid(key);

		if (RunningSession == null) {
			throw new LoginException("Please provide a valid key");
		}

		if (customer.getCustomerId() == RunningSession.getUserId()) {
			return cr.save(customer);
		} else {
			throw new CustomerException("Please login first");
		}
	}

}
