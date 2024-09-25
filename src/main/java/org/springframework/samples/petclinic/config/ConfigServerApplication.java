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
		Flags flags = new Flags();

		// Register the flags container under a namespace
		Rox.register("default", flags);

		// Setup connection with the feature management environment key
		Rox.setup("aa61857a-0ca1-4e9d-4616-55e416873ce4").get();

		// Check and print the value of the 'enableTutorial' flag
		boolean isTutorialEnabled = flags.enableTutorial.isEnabled();
		log.info("enableTutorial value is {}", isTutorialEnabled ? "true" : "false");

	}
}
