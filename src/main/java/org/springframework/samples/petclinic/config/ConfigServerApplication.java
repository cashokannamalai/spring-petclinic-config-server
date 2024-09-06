package org.springframework.samples.petclinic.config;

import io.rollout.rox.server.Rox;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.beans.factory.annotation.Value;

import java.util.concurrent.ExecutionException;

@EnableConfigServer
@SpringBootApplication
public class ConfigServerApplication {

    private static final Logger log = LoggerFactory.getLogger(ConfigServerApplication.class);

    @Value("${feature.management.key}")
    private String featureManagementKey;

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        SpringApplication.run(ConfigServerApplication.class, args);
        ConfigServerApplication app = new ConfigServerApplication();
        app.initializeFeatureFlags();
    }

    private void initializeFeatureFlags() throws ExecutionException, InterruptedException {
        Flags flags = new Flags();

        // Register the flags container under a namespace
        Rox.register("default", flags);

        // Setup connection with the feature management environment key
        Rox.setup(featureManagementKey).get();

        // Check and print the value of the 'enableTutorial' flag
        boolean isTutorialEnabled = flags.enableTutorial.isEnabled();
        log.info("enableTutorial value is {}", isTutorialEnabled ? "true" : "false");
    }
}
