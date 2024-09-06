/*
 * Copyright 2002-2021 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.samples.petclinic.config;


import io.rollout.rox.server.Rox;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

import java.util.concurrent.ExecutionException;


/**
 * @author Maciej Szarlinski
 */
@EnableConfigServer
@SpringBootApplication
public class ConfigServerApplication {
    private static final Logger log = LoggerFactory.getLogger(ConfigServerApplication.class);
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        SpringApplication.run(ConfigServerApplication.class, args);
        // Access the environment variable
        String featureManagementKey = System.getenv("FEATURE_MANAGEMENT_KEY");
        // Set up the flags and connect using the environment variable
        Flags flags = new Flags();
        Rox.register("default", flags);
        if (featureManagementKey != null) {
            Rox.setup(featureManagementKey).get();
        } else {
            log.error("FEATURE_MANAGEMENT_KEY is not set.");
        }
        boolean isTutorialEnabled = flags.enableTutorial.isEnabled();
        log.info("enableTutorial value is {}", isTutorialEnabled ? "true" : "false");
    }
}
