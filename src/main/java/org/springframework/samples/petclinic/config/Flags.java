package org.springframework.samples.petclinic.config;

import io.rollout.rox.server.FeatureFlag;

public class Flags {

    @FeatureFlag
    public FeatureFlag enableTutorial;
}
