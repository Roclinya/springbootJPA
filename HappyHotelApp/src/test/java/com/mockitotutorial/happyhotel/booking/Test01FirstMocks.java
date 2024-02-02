package com.mockitotutorial.happyhotel.booking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import java.time.LocalDate;

import org.junit.jupiter.api.*;

class Test01FirstMocks {

	private BookingService bookingService;
	private PaymentService paymentServiceMock;
	private RoomService roomServiceMock;
	private BookingDAO bookingDAOMock;
	private MailSender mailSenderMock;

	// instaniate BookingService in setup method
	@BeforeEach
	void setup() {
		//creat mocks for these 4 dependencies of BookingService
		this.paymentServiceMock = mock(PaymentService.class); // create dummy object of PaymentService
		this.roomServiceMock = mock(RoomService.class); // create dummy object of RoomService
		this.bookingDAOMock = mock(BookingDAO.class); // create dummy object of BookingDAO
		this.mailSenderMock = mock(MailSender.class); // create dummy object of MailSender

		// instaniate BookingService by providing the mocks as the dependencies
		this.bookingService = new BookingService(paymentServiceMock, roomServiceMock, bookingDAOMock, mailSenderMock);

	}

	@Test
	void should_CaculateCorrectPrice_When_CorrectInput() {
		// Behavior-driven development => BDD styles
		// given
		BookingRequest bookingRequest = new BookingRequest("1", LocalDate.of(2023, 01, 01), LocalDate.of(2023, 01, 05),
				2, false);
		double expected = 4 * 2 * 50.0; //BookingService.BASE_PRICE_USD=50.0
		// when
		double actual = bookingService.calculatePrice(bookingRequest);
		// then
		assertEquals(expected, actual);
	}

}
