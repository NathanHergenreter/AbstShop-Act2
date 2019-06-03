package abstshop2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import abstshop2.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
	
	Customer findByName(String name);
}
