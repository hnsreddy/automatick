package org.reddys.automatick.command;

import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
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

@RunWith(MockitoJUnitRunner.class)
public class ColorVsRegistrationNumberQueryRequestHandlerTest {

    @Mock IParkingLotServer server;

    @BeforeClass
    public static void beforeAllTest() throws ParkingFullException, InvalidLimitException{
        ParkingLot.getInstance().setLimit(4);
        ParkingLot.getInstance().park(new Vehicle("test1", "white"));
        ParkingLot.getInstance().park(new Vehicle("test2", "red"));
        ParkingLot.getInstance().park(new Vehicle("test3", "black"));
        ParkingLot.getInstance().park(new Vehicle("test4", "white"));
    }

    @Before
    public void setup () {
        initMocks(this);
    }
    @Test
    public void executeCLICommand() {
        ColorVsRegistrationNumberQueryRequestHandler command = new ColorVsRegistrationNumberQueryRequestHandler();
        String args[] = {"white"};
        ParkingLotRequest request = new ParkingLotRequest();
        request.setArguments(args);

        command.executeRequest(request, server);
        verify(server, times(1)).respond("test1");
        verify(server, times(1)).respond(",");
        verify(server, times(1)).respond("test4");
    }

    @Test
    public void executeCLICommandNoResults() {
        ColorVsRegistrationNumberQueryRequestHandler command = new ColorVsRegistrationNumberQueryRequestHandler();
        String args[] = {"brown"};
        ParkingLotRequest request = new ParkingLotRequest();
        request.setArguments(args);

        command.executeRequest(request, server);
        verify(server, times(1)).respond("No vehicles found");
    }

    @Test
    public void executeCLICommandInvalid() {
        ColorVsRegistrationNumberQueryRequestHandler command = new ColorVsRegistrationNumberQueryRequestHandler();
        String args[] = {};
        ParkingLotRequest request = new ParkingLotRequest();
        request.setArguments(args);
        command.executeRequest(request, server);
        verify(server, times(1)).respond("Invalid use of command - registration_numbers_for_cars_with_colour command requires the color to be specified");
    }

    @AfterClass
    public static void tearDown() throws IllegalAccessException, NoSuchFieldException{
        Field instance = ParkingLot.class.getDeclaredField("instance");
        instance.setAccessible(true);
        instance.set(null, null);
    }
}