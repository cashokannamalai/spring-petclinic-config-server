package org.springframework.samples.petclinic.config;

import io.rollout.configuration.RoxContainer;
import io.rollout.flags.RoxFlag;

public class Flags implements RoxContainer {
    public RoxFlag enableTutorial = new RoxFlag();
}
