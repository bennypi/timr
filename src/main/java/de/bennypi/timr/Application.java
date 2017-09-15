package de.bennypi.timr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.net.URI;

/**
 * Main class.
 *
 */
@SpringBootApplication
public class Application {

    /**
     * Main method.
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
    	SpringApplication.run(Application.class, args);
    }
}

