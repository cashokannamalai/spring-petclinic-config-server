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
        String roxKey = System.getProperty("rox.key", "64595968-4a49-4f49-7ebd-b08938ad1d2e");

        // Setup connection with the feature management environment key
        Rox.setup(roxKey).get();

        // Prints the value of the boolean enableTutorial flag
        System.out.printf("enableTutorial value is %s%n", flags.enableTutorial.isEnabled() ? "true" : "false");

        // Debugging Rox SDK setup
        if (Rox.isSetup()) {
            System.out.println("Rox SDK successfully connected.");
        } else {
            System.out.println("Rox SDK connection failed.");
        }
    }
}
