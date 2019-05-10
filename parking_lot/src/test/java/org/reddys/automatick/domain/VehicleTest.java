package org.reddys.automatick.domain;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class VehicleTest {

    @Test
    public void getRegistrationNumber() {
        Vehicle vehicle = new Vehicle("test5", "brown");
        Assert.assertEquals("test5", vehicle.getRegistrationNumber());
    }

    @Test
    public void getColor() {
        Vehicle vehicle = new Vehicle("test6", "red");
        Assert.assertEquals("red", vehicle.getColor());
    }
}