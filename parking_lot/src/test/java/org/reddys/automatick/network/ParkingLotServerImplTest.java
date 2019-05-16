package org.reddys.automatick.network;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Spy;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class ParkingLotServerImplTest {

    @Mock
    ServerSocket serverSocket;

    @Spy ParkingLotServerImpl parkingLotServer = new ParkingLotServerImpl();

    @Mock private Socket socket;

    @Before
    public void setup () throws IOException {
        initMocks(this);
    }

    @Test
    public void createAndListen() throws IOException{
        doReturn(serverSocket).when(parkingLotServer).getServerSocket(56060);
        when(serverSocket.accept()).thenReturn(socket);
        when (socket.getInputStream()).thenReturn(new ByteArrayInputStream("create_lot 4".getBytes()));
        when (socket.getOutputStream()).thenReturn(new ByteArrayOutputStream());

        parkingLotServer.createAndListen(56060);

        verify(serverSocket, times(1)).accept();
        verify(socket, times(1)).getInputStream();
        verify(socket, times(1)).getOutputStream();

    }

    @Test
    public void buildRequest() throws IOException{
        doReturn(serverSocket).when(parkingLotServer).getServerSocket(56060);
        when(serverSocket.accept()).thenReturn(socket);
        when (socket.getInputStream()).thenReturn(new ByteArrayInputStream("create_lot 4\n".getBytes()));
        when (socket.getOutputStream()).thenReturn(new ByteArrayOutputStream());
        parkingLotServer.createAndListen(56060);
        ParkingLotRequest request = parkingLotServer.buildRequest();
        assertNotNull(request);
        assertEquals ( "create_lot", request.getCommand());
        assertEquals ( "4", request.getArguments()[0]);
    }

    @Test
    public void respond() throws IOException{
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        doReturn(serverSocket).when(parkingLotServer).getServerSocket(56060);
        when(serverSocket.accept()).thenReturn(socket);
        when (socket.getInputStream()).thenReturn(new ByteArrayInputStream("create_lot 4\n".getBytes()));
        when (socket.getOutputStream()).thenReturn(outputStream);
        parkingLotServer.createAndListen(56060);

        parkingLotServer.respond("Test output");

        assertEquals("Test output\n", new String(outputStream.toByteArray()));
    }

    @Test
    public void close() throws IOException{
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ByteArrayInputStream inputStream = new ByteArrayInputStream("create_lot 4\n".getBytes());
        doReturn(serverSocket).when(parkingLotServer).getServerSocket(56060);
        when(serverSocket.accept()).thenReturn(socket);
        when (socket.getInputStream()).thenReturn(inputStream);
        when (socket.getOutputStream()).thenReturn(outputStream);
        parkingLotServer.createAndListen(56060);

        parkingLotServer.close();
        verify(socket, times(1)).close();
        verify(serverSocket, times(1)).close();
    }
}