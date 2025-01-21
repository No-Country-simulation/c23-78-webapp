package com.trackmyfix.trackmyfix;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

//@ActiveProfiles("test")
@SpringBootTest(
		webEnvironment = SpringBootTest.WebEnvironment.MOCK,
		classes = TrackmyfixApplicationTests.class)
class TrackmyfixApplicationTests {

	@Test
	void contextLoads() {
	}

}
