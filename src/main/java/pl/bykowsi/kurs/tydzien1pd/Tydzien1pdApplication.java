package pl.bykowsi.kurs.tydzien1pd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class Tydzien1pdApplication {

    public static void main(String[] args) {
        SpringApplication.run(Tydzien1pdApplication.class, args);
    }

}
