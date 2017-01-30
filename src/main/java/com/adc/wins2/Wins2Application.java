package com.adc.wins2;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Wins2Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Wins2Application.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {
		System.out.println();
		System.out.println("██╗    ██╗██╗███╗   ██╗███████╗    ██████╗	");
		System.out.println("██║    ██║██║████╗  ██║██╔════╝    ╚════██╗	");
		System.out.println("██║ █╗ ██║██║██╔██╗ ██║███████╗     █████╔╝	");
		System.out.println("██║███╗██║██║██║╚██╗██║╚════██║    ██╔═══╝	");
		System.out.println("╚███╔███╔╝██║██║ ╚████║███████║    ███████╗	");
		System.out.println(" ╚══╝╚══╝ ╚═╝╚═╝  ╚═══╝╚══════╝    ╚══════╝	");
		System.out.println();
	}
}
