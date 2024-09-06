package org.springframework.samples.petclinic.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import io.rollout.rox.server.Rox;
import io.rollout.rox.server.RoxClient;
import io.rollout.rox.server.RoxFlag;
import io.rollout.rox.server.RoxFlags;

@SpringBootApplication
@EnableConfigServer
public class ConfigServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfigServerApplication.class, args);

        try {
            // Initialize Rox client
            RoxClient roxClient = Rox.setup("7d77f2ce-ada9-4276-564c-ac004dc6c37e").get();

            // Access flags
            RoxFlags flags = roxClient.getFlags();

            // Check and print the value of the enableTutorial flag
            boolean enableTutorial = flags.get("enableTutorial").isEnabled();
            System.out.printf("enableTutorial value is %s%n", enableTutorial ? "true" : "false");

        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
