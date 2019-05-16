package org.reddys.automatick.command;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;
import org.reddys.automatick.domain.ParkingLot;
import org.reddys.automatick.domain.Vehicle;
import org.reddys.automatick.exception.InvalidLimitException;
import org.reddys.automatick.exception.ParkingFullException;
import org.reddys.automatick.network.IParkingLotServer;
import org.reddys.automatick.network.ParkingLotRequest;

import java.lang.reflect.Field;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public class ColorVsSlotNumberQueryRequestHandlerTest {

    @Mock
    IParkingLotServer server;

    @BeforeClass
    public static void setup() throws ParkingFullException, InvalidLimitException {
        ParkingLot.getInstance().setLimit(4);
        ParkingLot.getInstance().park(new Vehicle("test1", "white"));
        ParkingLot.getInstance().park(new Vehicle("test2", "red"));
        ParkingLot.getInstance().park(new Vehicle("test3", "black"));
        ParkingLot.getInstance().park(new Vehicle("test4", "white"));
    }

    @Before
    public void setupMocks () {
        initMocks(this);
    }


    @Test
    public void executeCLICommandOneResult() {
        ColorVsSlotNumberQueryRequestHandler command = new ColorVsSlotNumberQueryRequestHandler();
        String args[] = {"red"};
        ParkingLotRequest request = new ParkingLotRequest();
        request.setArguments(args);
        command.executeRequest(request, server);
        verify(server, times (1)).respond("1");
    }

    @Test
    public void executeCLICommandMultipleResults() {
        ColorVsSlotNumberQueryRequestHandler command = new ColorVsSlotNumberQueryRequestHandler();
        String args[] = {"white"};
        ParkingLotRequest request = new ParkingLotRequest();
        request.setArguments(args);
        command.executeRequest(request, server);
        verify(server, times (1)).respond("0");
        verify(server, times (1)).respond(",");
        verify(server, times (1)).respond("3");
    }

    @Test
    public void executeCLICommandInvalid() {
        ColorVsSlotNumberQueryRequestHandler command = new ColorVsSlotNumberQueryRequestHandler();
        String args[] = {};
        ParkingLotRequest request = new ParkingLotRequest();
        request.setArguments(args);
        command.executeRequest(request, server);
        verify(server, times (1)).respond("Invalid use of command - slot_numbers_for_cars_with_colour command requires the color to be specified");
    }

    @Test
    public void executeCLICommandNoResult() {
        ColorVsSlotNumberQueryRequestHandler command = new ColorVsSlotNumberQueryRequestHandler();
        String args[] = {"brown"};
        ParkingLotRequest request = new ParkingLotRequest();
        request.setArguments(args);
        command.executeRequest(request, server);
        verify(server, times (1)).respond("No vehicles found");
    }

    @AfterClass
    public static void tearDown() throws IllegalAccessException, NoSuchFieldException {
        Field instance = ParkingLot.class.getDeclaredField("instance");
        instance.setAccessible(true);
        instance.set(null, null);
    }
}