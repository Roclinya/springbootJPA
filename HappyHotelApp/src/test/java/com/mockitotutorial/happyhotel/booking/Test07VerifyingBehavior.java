package com.mockitotutorial.happyhotel.booking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.*;

import java.time.LocalDate;

import org.junit.jupiter.api.*;

class Test07VerifyingBehavior {

	private BookingService bookingService;
	private PaymentService paymentServiceMock;
	private RoomService roomServiceMock;
	private BookingDAO bookingDAOMock;
	private MailSender mailSenderMock;

	// instaniate BookingService in setup method
	@BeforeEach
	void setup() {
		// creat mocks for these 4 dependencies of BookingService
		this.paymentServiceMock = mock(PaymentService.class); // create dummy object of PaymentService
		this.roomServiceMock = mock(RoomService.class); // create dummy object of RoomService
		this.bookingDAOMock = mock(BookingDAO.class); // create dummy object of BookingDAO
		this.mailSenderMock = mock(MailSender.class); // create dummy object of MailSender

		// instaniate BookingService by providing the mocks as the dependencies
		this.bookingService = new BookingService(paymentServiceMock, roomServiceMock, bookingDAOMock, mailSenderMock);

	}

	@Test
	void should_InvokePayment_When_Prepaid() {
		// Behavior-driven development => BDD styles
		// given
		BookingRequest bookingRequest = new BookingRequest("1", LocalDate.of(2023, 01, 01), LocalDate.of(2023, 01, 05),
				2, true);// => isPrepaid = true means paymentService.pay(bookingRequest, price) would be called;

		// when
		// we simply know the method was invoked,but we don't know what happened behind
		// the scenes.
		// we want to verify the paymentService mock was called.
		// In other words, we want to make sure that paymentService.pay() was executed
		// correctly in makeBooking method.
		bookingService.makeBooking(bookingRequest);
		// then
		// On this line,we check whether the method pay() from the paymentServiceMock
		// was called with these specific argument.
//		verify(paymentServiceMock).pay(bookingRequest, 400.0); //price 輸入500會fail
		verify(paymentServiceMock, times(1)).pay(bookingRequest, 400.0);
		// this Mockito method checks if any other methods form this mock were called.
		// If paymentServiceMock is called
		// again for a second time, this will also throw an exception.
		// verify(paymentServiceMock,times(2)).pay(bookingRequest, 400.0);
		// //time改成2就會throw exception
		verifyNoMoreInteractions(paymentServiceMock);

	}

	@Test
	void should_NotInvokePayment_When_NotPrepaid() {
		// Behavior-driven development => BDD styles
		// given
		BookingRequest bookingRequest = new BookingRequest("1", LocalDate.of(2023, 01, 01), LocalDate.of(2023, 01, 05),
				2, false); // => isPrepaid=false neglect paymentService.pay(bookingRequest, price);

		// when
		bookingService.makeBooking(bookingRequest);
		// then
		//never() : we expect the paymentServiceMock to never be called with the method pay()
		// bookingRequest => any() & double price => anyDouble : we don't want it to be called with any kind of input.
		verify(paymentServiceMock, never()).pay(any(), anyDouble());
	}

}
