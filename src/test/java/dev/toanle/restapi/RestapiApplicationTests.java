package dev.toanle.restapi;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test") // Chỉ định profile "test"
class RestapiApplicationTests {

	@Test
	void contextLoads() {
		// Thực hiện kiểm thử mà không cần Flyway
	}

}
