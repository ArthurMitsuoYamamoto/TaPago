package br.com.fiap.tapago;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
@Controller
public class TapagoApplication {

	public static void main(String[] args) {
		SpringApplication.run(TapagoApplication.class, args);
	}

	@RequestMapping
	public String home(){
		return "Tá Pago";
	}

}
