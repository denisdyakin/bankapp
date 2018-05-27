package ru.tinkoff;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import ru.tinkoff.exception.NoDataException;
import ru.tinkoff.jdo.Customer;
import ru.tinkoff.jdo.Order;
import ru.tinkoff.service.BankServiceImpl;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BankappApplicationTests {

	@Autowired
	BankServiceImpl bankService;

	@Test
	public void bankServiceCustomerTest() {
		Page<Customer> customers = bankService.findAllCustomers(PageRequest.of(0, 5));

		Assert.assertEquals(3, customers.getContent().size());
	}

	@Test
	@Transactional
	public void bankServiceOrderTest() {

		//no data test
		try {
			bankService.findLatestOrder(10L);
		} catch (RuntimeException exception) {
			Assert.assertEquals(true, exception instanceof NoDataException);
		}

		//find latestOrder
		Order expectedOrder = new Order();
		expectedOrder.setId(4L);
		expectedOrder.setCreatedDate(new Date(1520715602000L));
		expectedOrder.setProductName("smf");

		Customer expectedCustomer = new Customer(2L);

		Order orderOfCustomer = bankService.findLatestOrder(expectedCustomer.getContactId());
		Customer customer = orderOfCustomer.getCustomer();
		Assert.assertEquals(expectedOrder.getId(), orderOfCustomer.getId());
		Assert.assertEquals(expectedOrder.getCreatedDate().getTime(), orderOfCustomer.getCreatedDate().getTime());
		Assert.assertEquals(expectedOrder.getProductName(), orderOfCustomer.getProductName());
		Assert.assertEquals(expectedCustomer.getContactId(), customer.getContactId());
	}

}
