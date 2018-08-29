package com.miaoshasha.base;

import com.miaoshasha.base.service.user.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BaseServiceApplicationTests {

	@Autowired
	private UserService userService;

	@Test
	public void testCache() {
		ExecutorService executorService = Executors.newFixedThreadPool(10);
		for (int i = 0; i < 100; i++) {
			executorService.execute(()->{
				userService.findById(1L);
			});
		}
	}



}
