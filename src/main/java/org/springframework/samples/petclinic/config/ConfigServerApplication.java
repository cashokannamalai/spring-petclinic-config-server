package org.springframework.samples.petclinic.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

import io.rollout.rox.server.Rox;
import java.util.concurrent.ExecutionException;

/**
 * ConfigServerApplication for Spring Boot configuration with Rox feature management.
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

        // Get Rox environment key from system properties or fallback to default
        String roxKey = System.getenv("ROX_KEY");

        if (roxKey == null || roxKey.isEmpty()) {
            System.err.println("Rox key is not set or is empty!");
            System.exit(1);
        }

        // Setup connection with the feature management environment key
        Rox.setup(roxKey).get();

        // Prints the value of the boolean enableTutorial flag
        System.out.printf("Rox key being used: %s%n", roxKey);

        // Optional: Logging message to ensure Rox setup is complete (depending on your SDK version)
        System.out.println("Rox SDK setup completed successfully.");
    }
}
