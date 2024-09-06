package org.springframework.samples.petclinic.config;

import io.rollout.rox.server.Rox;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.Autowired;
import javax.annotation.PostConstruct;


import java.util.concurrent.ExecutionException;

@SpringBootApplication
@EnableConfigServer
public class ConfigServerApplication {

    private static final Logger log = LoggerFactory.getLogger(ConfigServerApplication.class);

    @Value("${feature.management.key}")
    private String featureManagementKey;

    @Autowired
    private Flags flags;

    public static void main(String[] args) {
        SpringApplication.run(ConfigServerApplication.class, args);
    }

    @PostConstruct
    public void initializeFeatureFlags() throws ExecutionException, InterruptedException {
        // Register the flags container under a namespace
        Rox.register("default", flags);

        // Setup connection with the feature management environment key
        Rox.setup(featureManagementKey).get();

        // Check and print the value of the 'enableTutorial' flag
        boolean isTutorialEnabled = flags.enableTutorial.isEnabled();
        log.info("enableTutorial value is {}", isTutorialEnabled ? "true" : "false");
    }
}
