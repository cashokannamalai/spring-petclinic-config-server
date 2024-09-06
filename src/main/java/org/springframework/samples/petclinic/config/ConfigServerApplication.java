package org.springframework.samples.petclinic.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import io.rollout.rox.server.Rox;
import java.util.concurrent.ExecutionException;

@SpringBootApplication
@EnableConfigServer
public class ConfigServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfigServerApplication.class, args);

        try {
            // Initialize Flags container class
            Flags flags = new Flags();

            // Register the flags container under a namespace
            Rox.register("default", flags);

            // Setup connection with the feature management environment key
            Rox.setup("7d77f2ce-ada9-4276-564c-ac004dc6c37e").get();

            // Prints the value of the boolean enableTutorial flag
            System.out.printf("enableTutorial value is %s%n", flags.enableTutorial.isEnabled() ? "true" : "false");

        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
