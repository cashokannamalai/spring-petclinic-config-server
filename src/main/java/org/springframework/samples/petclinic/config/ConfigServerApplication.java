package org.springframework.samples.petclinic.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

import io.rollout.rox.server.Rox;
import java.util.concurrent.ExecutionException;

/**
 * @author Maciej Szarlinski
 */
@EnableConfigServer
@SpringBootApplication
public class ConfigServerApplication {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        // Start the Spring Boot application
        SpringApplication.run(ConfigServerApplication.class, args);

        // Initialize Flags container class
        Flags flags = new Flags();

        // Register the flags container under a namespace
        Rox.register("default", flags);

        // Setup connection with the feature management environment key
        Rox.setup("f28aec05-387d-4b7e-5367-2c4b0658cc63").get();

        // Prints the value of the boolean enableTutorial flag
        System.out.printf("enableTutorial value is %s%n", flags.enableTutorial.isEnabled() ? "true" : "false");
    }
}
