package ru.tinkoff;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import ru.tinkoff.exception.NoDataException;
import ru.tinkoff.jdo.LatestOrderResponse;
import ru.tinkoff.jdo.Order;
import ru.tinkoff.service.BankServiceImpl;

import java.time.LocalDateTime;
import java.time.Month;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BankappApplicationTests {

	@Autowired
	BankServiceImpl bankService;

	@Test
	@Transactional
	public void bankServiceOrderNotFoundTest() {
		try {
			bankService.findLatestOrder(3L);
		} catch (RuntimeException exception) {
			assertEquals(exception.getMessage(), "Latest order for 3 wasn't found");
			assertEquals(true, exception instanceof NoDataException);
		}
	}

	@Test
	@Transactional
	public void bankServiceCustomerNotExistTest() {
		try {
			bankService.findLatestOrder(10L);
		} catch (RuntimeException exception) {
			assertEquals(exception.getMessage(), "Customer 10 is not exist");
			assertEquals(true, exception instanceof NoDataException);
		}
	}

	@Test
	@Transactional
	public void bankServiceLatestOrderTest() {
		Order expectedOrder = new Order();
		expectedOrder.setId(4L);
		expectedOrder.setCreatedDate(LocalDateTime.of(2018, Month.MARCH, 11, 0, 0, 2));
		expectedOrder.setProductName("smf");

		LatestOrderResponse orderOfCustomer = bankService.findLatestOrder(2L);
		assertEquals(Long.valueOf(2), orderOfCustomer.getContactId());
		assertEquals(expectedOrder.getId(), orderOfCustomer.getId());
		assertEquals(expectedOrder.getCreatedDate(), orderOfCustomer.getCreatedDate());
		assertEquals(expectedOrder.getProductName(), orderOfCustomer.getProductName());
	}

}
