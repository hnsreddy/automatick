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

import java.io.IOException;
import java.lang.reflect.Field;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public class LeaveRequestHandlerTest {
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
    public void executeCLICommandInvalid() throws IOException {
        LeaveRequestHandler command = new LeaveRequestHandler();
        String args[] = {};
        ParkingLotRequest request = new ParkingLotRequest();
        request.setArguments(args);

        command.executeRequest(request, server);
        verify(server, times(1)).respond("Invalid use of command - leave command requires slot number to be specified");
    }

    @Test
    public void executeCLICommand() throws IOException {
        LeaveRequestHandler command = new LeaveRequestHandler();
        String args[] = {"2"};
        ParkingLotRequest request = new ParkingLotRequest();
        request.setArguments(args);

        command.executeRequest(request, server);
        verify(server, times(1)).respond("Slot number 2 is free");
    }


    @AfterClass
    public static void tearDown() throws IllegalAccessException, NoSuchFieldException {
        Field instance = ParkingLot.class.getDeclaredField("instance");
        instance.setAccessible(true);
        instance.set(null, null);
    }
}