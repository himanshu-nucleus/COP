package com.ecommerce.payment.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class ErrorResponseTest {

	public ErrorResponse buildErrorResponse(Integer errorCode, List<String> errorMessages) {
		ErrorResponse errorResponseTest = new ErrorResponse();
		errorResponseTest.setErrorCode(errorCode);
		errorResponseTest.setErrorMessages(errorMessages);
		return errorResponseTest;
	}

	@Test
	public void getterSetterTest() {

		Integer errorCode = 400;
		List<String> errorMessages = new ArrayList<String>();
		errorMessages.add("str1");

		ErrorResponse errorResponse = new ErrorResponse();

		assertNull(errorResponse.getErrorCode());
		errorResponse.setErrorCode(errorCode);
		assertEquals(errorCode, errorResponse.getErrorCode());

		assertNull(errorResponse.getErrorMessages());
		errorResponse.setErrorMessages(errorMessages);
		assertEquals(1, errorResponse.getErrorMessages().size());

		System.out.println(errorResponse);
	}

	@Test
	public void toStringTest() {

		String errorResponseTestString = "ErrorResponse(errorCode=400, errorMessages=[str1])";

		Integer errorCode = 400;
		List<String> errorMessages = new ArrayList<String>();
		errorMessages.add("str1");

		ErrorResponse errorResponseTest = buildErrorResponse(errorCode, errorMessages);

		assertEquals(errorResponseTestString, errorResponseTest.toString());

	}

	@Test
	public void equalsAndHashCodeTest() {


		Integer errorCode = 400;
		List<String> errorMessages = new ArrayList<String>();
		errorMessages.add("str1");
		
		ErrorResponse errorResponseTest = buildErrorResponse(errorCode, errorMessages);
		ErrorResponse errorResponseTest2 = buildErrorResponse(errorCode, errorMessages);
		
		assertEquals(errorResponseTest, errorResponseTest);
		assertEquals(errorResponseTest.hashCode(), errorResponseTest.hashCode());

		assertNotEquals(errorResponseTest, new Object());
		assertNotEquals(errorResponseTest2, null);

		assertEquals(errorResponseTest2, errorResponseTest2);
		assertEquals(errorResponseTest2.hashCode(), errorResponseTest2.hashCode());

		errorResponseTest = buildErrorResponse(202, errorMessages);
		
		List<String> changed = new ArrayList<String>();
		changed.add("str2");
		errorResponseTest = buildErrorResponse(errorCode, changed);
		assertNotEquals(errorResponseTest2, errorResponseTest);
        assertNotEquals(errorResponseTest2.hashCode(), errorResponseTest.hashCode());
        
	}

}
