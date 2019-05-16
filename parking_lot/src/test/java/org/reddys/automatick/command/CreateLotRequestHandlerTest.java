package org.reddys.automatick.command;

import org.junit.*;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.reddys.automatick.domain.ParkingLot;
import org.reddys.automatick.domain.Vehicle;
import org.reddys.automatick.exception.InvalidLimitException;
import org.reddys.automatick.exception.ParkingFullException;
import org.reddys.automatick.network.IParkingLotServer;
import org.reddys.automatick.network.ParkingLotRequest;

import java.io.IOException;
import java.io.StringWriter;
import java.lang.reflect.Field;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public class CreateLotRequestHandlerTest {

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
    public void executeCLICommandNoArguments() {
        CreateLotRequestHandler command = new CreateLotRequestHandler();
        String args[] = {};
        ParkingLotRequest request = new ParkingLotRequest();
        request.setArguments(args);
        command.executeRequest(request, server);
        verify(server, times (1)).respond("Invalid use of command - create_parking_lot command requires number of slots to be specified");
    }

    @Test
    public void executeCLICommandInvalidArgument() {
        CreateLotRequestHandler command = new CreateLotRequestHandler();
        String args[] = {"s"};
        ParkingLotRequest request = new ParkingLotRequest();
        request.setArguments(args);
        command.executeRequest(request, server);
        verify(server, times (1)).respond("Invalid argument passed, cannot create a parking lot with specified arguments. Try and be a little more sensible");
    }

    @Test
    public void executeCLICommandParkingLotAlreadyCreated() {
        CreateLotRequestHandler command = new CreateLotRequestHandler();
        String args[] = {"4"};
        ParkingLotRequest request = new ParkingLotRequest();
        request.setArguments(args);
        command.executeRequest(request, server);
        verify(server, times (1)).respond("Parking lot already created");
    }

    @Test
    public void executeCLICommand() throws IOException, IllegalAccessException, NoSuchFieldException {
        Field instance = ParkingLot.class.getDeclaredField("instance");
        instance.setAccessible(true);
        instance.set(null, null);
        CreateLotRequestHandler command = new CreateLotRequestHandler();
        String args[] = {"4"};

        ParkingLotRequest request = new ParkingLotRequest();
        request.setArguments(args);
        command.executeRequest(request, server);
        verify(server, times(1)).respond("Created a parking lot with 4 slots");
    }

    @AfterClass
    public static void tearDown() throws IllegalAccessException, NoSuchFieldException {
        Field instance = ParkingLot.class.getDeclaredField("instance");
        instance.setAccessible(true);
        instance.set(null, null);
    }

}