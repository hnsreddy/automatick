package org.reddys.automatick.command;

import org.junit.*;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.reddys.automatick.domain.ParkingLot;
import org.reddys.automatick.domain.Vehicle;
import org.reddys.automatick.exception.InvalidLimitException;
import org.reddys.automatick.exception.ParkingFullException;
import org.reddys.automatick.network.IParkingLotServer;
import org.reddys.automatick.network.ParkingLotRequest;

import java.io.IOException;
import java.io.StringWriter;
import java.lang.reflect.Field;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public class ParkRequestHandlerTest {

    @Mock
    IParkingLotServer server;

    @BeforeClass
    public static void setup() throws ParkingFullException, InvalidLimitException {
        ParkingLot.getInstance().setLimit(4);
        ParkingLot.getInstance().park(new Vehicle("test1", "white"));
        ParkingLot.getInstance().park(new Vehicle("test2", "red"));
        ParkingLot.getInstance().park(new Vehicle("test3", "black"));
    }

    @Before
    public void setupMocks () {
        initMocks(this);
    }

    @Test
    public void executeCLICommandNoArguments() {
        ParkRequestHandler command = new ParkRequestHandler();
        String[] args = {};
        ParkingLotRequest request = new ParkingLotRequest();
        request.setArguments(args);

        command.executeRequest(request, server);
        verify(server, times(1)).respond("Invalid use of command - Park command requires vehicle registration number and vehicle color in the same order");
    }

    @Test
    public void executeCLICommandOneParameter() {
        ParkRequestHandler command = new ParkRequestHandler();
        String[] args = {"test1"};
        ParkingLotRequest request = new ParkingLotRequest();
        request.setArguments(args);

        command.executeRequest(request, server);
        verify(server, times(1)).respond("Invalid use of command - Park command requires vehicle registration number and vehicle color in the same order");
    }

    @Test
    public void executeCLICommandParkingFull() throws ParkingFullException, InvalidLimitException, IllegalAccessException, NoSuchFieldException  {
        Field instance = ParkingLot.class.getDeclaredField("instance");
        instance.setAccessible(true);
        instance.set(null, null);

        ParkingLot.getInstance().setLimit(3);
        ParkingLot.getInstance().park(new Vehicle("test1", "white"));
        ParkingLot.getInstance().park(new Vehicle("test2", "red"));
        ParkingLot.getInstance().park(new Vehicle("test3", "black"));

        ParkRequestHandler command = new ParkRequestHandler();
        String[] args = {"test1", "white"};

        ParkingLotRequest request = new ParkingLotRequest();
        request.setArguments(args);

        command.executeRequest(request, server);
        verify(server, times(1)).respond("Sorry, parking lot is full");
    }

    @Test
    public void executeCLICommand() throws IOException, IllegalAccessException, NoSuchFieldException, InvalidLimitException {
        Field instance = ParkingLot.class.getDeclaredField("instance");
        instance.setAccessible(true);
        instance.set(null, null);

        ParkingLot.getInstance().setLimit(3);

        ParkRequestHandler command = new ParkRequestHandler();
        String[] args = {"test1", "white"};
        ParkingLotRequest request = new ParkingLotRequest();
        request.setArguments(args);

        command.executeRequest(request, server);
        verify(server, times(1)).respond("Allocated slot number:0");
    }


    @AfterClass
    public static void tearDown() throws IllegalAccessException, NoSuchFieldException {
        Field instance = ParkingLot.class.getDeclaredField("instance");
        instance.setAccessible(true);
        instance.set(null, null);
    }
}