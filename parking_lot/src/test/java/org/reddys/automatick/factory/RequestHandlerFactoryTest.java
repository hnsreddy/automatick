package org.reddys.automatick.factory;

import org.junit.Test;
import org.reddys.automatick.command.*;
import org.reddys.automatick.network.ParkingLotRequest;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;

public class RequestHandlerFactoryTest {

   @Test
    public void getParkCommand() {
       ParkingLotRequest request = new ParkingLotRequest();
       request.setCommand("park");
       IRequestHandler command = RequestHandlerFactory.getRequestHandler(request);
       assertThat (command, instanceOf(ParkRequestHandler.class));
    }

    @Test
    public void getLeaveCommand() {
        ParkingLotRequest request = new ParkingLotRequest();
        request.setCommand("leave");
        IRequestHandler command = RequestHandlerFactory.getRequestHandler(request);
        assertThat (command, instanceOf(LeaveRequestHandler.class));
    }

    @Test
    public void getCreateLotCommand() {
        ParkingLotRequest request = new ParkingLotRequest();
        request.setCommand("create_lot");
        IRequestHandler command = RequestHandlerFactory.getRequestHandler(request);
        assertThat (command, instanceOf(CreateLotRequestHandler.class));
    }

    @Test
    public void getColorVsRegsitrationNumberCommand() {
        ParkingLotRequest request = new ParkingLotRequest();
        request.setCommand("registration_numbers_for_cars_with_colour");
        IRequestHandler command = RequestHandlerFactory.getRequestHandler(request);
        assertThat (command, instanceOf(ColorVsRegistrationNumberQueryRequestHandler.class));
    }

    @Test
    public void getColorVsSlotNumberCommand() {
        ParkingLotRequest request = new ParkingLotRequest();
        request.setCommand("slot_numbers_for_cars_with_colour");
        IRequestHandler command = RequestHandlerFactory.getRequestHandler(request);
        assertThat (command, instanceOf(ColorVsSlotNumberQueryRequestHandler.class));
    }

    @Test
    public void getRegistrationNumberVsSlotNumberCommand() {
        ParkingLotRequest request = new ParkingLotRequest();
        request.setCommand("slot_number_for_registration_number");
        IRequestHandler command = RequestHandlerFactory.getRequestHandler(request);
        assertThat (command, instanceOf(RegistrationNumberVsSlotNumberQueryRequestHandler.class));
    }

    @Test
    public void getCommandInvalid() {
        ParkingLotRequest request = new ParkingLotRequest();
        request.setCommand("invalid");
        IRequestHandler command = RequestHandlerFactory.getRequestHandler(request);
        assertNull (command);
    }

}