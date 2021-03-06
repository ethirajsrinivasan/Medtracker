package com.ethigeek.medtracker;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.ethigeek.medtracker.domain.*;

/**
 * Created by e0146812 on 3/26/2017.
 */
// Runs all unit tests.
@RunWith(Suite.class)
@Suite.SuiteClasses({
        AppointmentTest.class,
        CategoryTest.class,
        HealthBioTest.class,
        ICEContactTest.class,
        MeasurementTest.class,
        MedicineTest.class,
        PersonalBioTest.class,
        ReminderTest.class,
        ConsumptionTest.class})
public class UnitTestSuite {
}
