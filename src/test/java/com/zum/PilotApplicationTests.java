package com.zum;

import com.zum.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebMvcTest
public class PilotApplicationTests {

	@Test
	public void contextLoads() {
		User user = new User();
		user.toString();
	}

}
