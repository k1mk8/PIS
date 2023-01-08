package com.pisproject.lawtextdb.unit;

import com.pisproject.lawtextdb.LawTextDBApplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class LawTextDBApplicationTests {

	@Test
	void contextLoads() {
		LawTextDBApplication app = new LawTextDBApplication();
		assertNotEquals(new Object(), app);
	}

}
