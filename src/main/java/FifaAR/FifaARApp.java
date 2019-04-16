package FifaAR;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class FifaARApp {

    public static void main(String[] args) {
        SpringApplication.run(FifaARApp.class, args);
    }
}