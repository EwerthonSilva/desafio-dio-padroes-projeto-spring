package me.dio.desafiodiopadraoprojeto.desafiodiopadraoprojeto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class DesafioDioPadraoProjetoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DesafioDioPadraoProjetoApplication.class, args);
	}

}
