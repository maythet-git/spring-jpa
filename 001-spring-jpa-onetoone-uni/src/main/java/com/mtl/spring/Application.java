package com.mtl.spring;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.mtl.spring.entity.OrderDetails;
import com.mtl.spring.entity.Orders;
import com.mtl.spring.repo.OrderDetailsRepo;
import com.mtl.spring.repo.OrdersRepo;

@SpringBootApplication
@EnableTransactionManagement
@EnableJpaRepositories(basePackages =  "com.mtl.spring.repo")
@EntityScan(basePackages = "com.mtl.spring.entity")
@ComponentScan (basePackages =  "com.mtl.spring" )
public class Application implements CommandLineRunner{

	@Autowired
	private OrdersRepo ordersRepo;
	
	@Autowired
	private OrderDetailsRepo detialsRepo;
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmSSS");
		LocalDateTime d = LocalDateTime.now();

		String orderNo = d.format(dtf);

		Orders order = new Orders();
		order.setOrderNo(orderNo);
		this.ordersRepo.save(order);
		//order.setId(null);

		OrderDetails orderDetails = new OrderDetails();
		orderDetails.setOrders(order);

		this.detialsRepo.save(orderDetails);

		OrderDetails de = this.detialsRepo.findAllById(1);
		de.setDeliveryDate(new Date());
		//de.getOrders().setOrderNo("AAAAAAAAAAAAAAA");
		de.setShippingAddress("Tokyo");
		this.detialsRepo.save(de);
		
	}

}
