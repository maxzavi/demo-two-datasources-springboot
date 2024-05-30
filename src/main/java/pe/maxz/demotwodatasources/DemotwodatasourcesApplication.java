package pe.maxz.demotwodatasources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import pe.maxz.demotwodatasources.repository.MySQLRepository;
import pe.maxz.demotwodatasources.repository.OracleRepository;

@SpringBootApplication
public class DemotwodatasourcesApplication implements CommandLineRunner{

	@Autowired
	private OracleRepository oracleRepository;

	@Autowired
	private MySQLRepository mySQLRepository;

	public static void main(String[] args) {
		SpringApplication.run(DemotwodatasourcesApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		oracleRepository.test();
		mySQLRepository.test();
	}
}
