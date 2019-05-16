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

public class RegistrationNumberVsSlotNumberQueryRequestHandlerTest {

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
        RegistrationNumberVsSlotNumberQueryRequestHandler command = new RegistrationNumberVsSlotNumberQueryRequestHandler();
        String args[] = {};

        ParkingLotRequest request = new ParkingLotRequest();
        request.setArguments(args);

        command.executeRequest(request, server);
        verify(server, times(1)).respond("Invalid use of command - slot_number_for_registration_number command requires the registration number to be specified");
    }

    @Test
    public void executeCLICommandValidRegistrationNumber() throws IOException {
        RegistrationNumberVsSlotNumberQueryRequestHandler command = new RegistrationNumberVsSlotNumberQueryRequestHandler();
        String args[] = {"test3"};
        ParkingLotRequest request = new ParkingLotRequest();
        request.setArguments(args);

        command.executeRequest(request, server);
        verify(server, times(1)).respond("2");
    }

    @Test
    public void executeCLICommandNonExistentRegistrationNumber() throws IOException {
        RegistrationNumberVsSlotNumberQueryRequestHandler command = new RegistrationNumberVsSlotNumberQueryRequestHandler();
        String args[] = {"test8"};
        ParkingLotRequest request = new ParkingLotRequest();
        request.setArguments(args);

        command.executeRequest(request, server);
        verify(server, times(1)).respond("Not found");
    }

    @AfterClass
    public static void tearDown() throws IllegalAccessException, NoSuchFieldException {
        Field instance = ParkingLot.class.getDeclaredField("instance");
        instance.setAccessible(true);
        instance.set(null, null);
    }
}