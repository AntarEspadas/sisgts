package mx.uam.ayd.proyecto;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assumptions.*;

@SpringBootTest
class ProyectoApplicationTests {

	@BeforeAll
	static void setUp(){
		assumeFalse(java.awt.GraphicsEnvironment.isHeadless());
		System.setProperty("java.awt.headless", "false");
	}

	@SuppressWarnings("all")
	@Test
	void contextLoads() {
	}

}
