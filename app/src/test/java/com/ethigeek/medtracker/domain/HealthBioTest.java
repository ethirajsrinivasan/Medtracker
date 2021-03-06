package com.ethigeek.medtracker.domain;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

/**
 * Created by Ashish Katre on 3/20/2017.
 */

/**
 * Class for Healthbio test
 */
public class HealthBioTest {

    HealthBio healthBio;

    /**
     * Method to test getters and setters
     */
    @Test
    public void testHealthBio() {

        healthBio = new HealthBio();
        healthBio.setId(10);
        healthBio.setCondition("Peanut");
        healthBio.setConditionType("Allergy");
        healthBio.setStartDate("1990-01-01");

        assertEquals(healthBio.getId(), 10);
        assertEquals(healthBio.getCondition(), "Peanut");
        assertEquals(healthBio.getConditionType(), "Allergy");
        assertEquals(healthBio.getStartDate(), "1990-01-01");
    }

    /**
     * Method to test getters and setters
     */
    @Test
    public void testHealthBioFull() {

        healthBio = new HealthBio("Peanut", "Allergy", "1990-01-01");

        assertNotNull(healthBio);
        assertEquals(healthBio.getCondition(), "Peanut");
        assertEquals(healthBio.getConditionType(), "Allergy");
        assertEquals(healthBio.getStartDate(), "1990-01-01");
    }
}
