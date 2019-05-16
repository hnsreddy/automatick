package org.reddys.automatick.network;

import org.reddys.automatick.exception.SocketClosingException;
import org.reddys.automatick.exception.SocketConnectionException;
import org.reddys.automatick.exception.SocketReadingException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ParkingLotServerImpl implements IParkingLotServer{

    private ServerSocket serverSocket;
    private Socket socket;
    private BufferedReader input;
    private PrintWriter output;

    @Override
    public void createAndListen(int port) {
        try {
            serverSocket = getServerSocket(port);
            socket = serverSocket.accept();
            input = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            output = new PrintWriter(socket.getOutputStream(), true);

        } catch (IOException e) {
            throw new SocketConnectionException("Connection error, cannot proceed");
        }
    }

    @Override
    public ParkingLotRequest buildRequest() {
        String fullCommandString;
        try {
            fullCommandString = input.readLine();
            return ParkingLotRequest.parseRequest(fullCommandString);
        }catch (IOException ioe) {
            throw new SocketReadingException("Unable to read command string");
        }
    }

    @Override
    public void respond(String responseString) {
        output.println(responseString);
    }

    @Override
    public void close() {
        try {
            output.close();
            input.close();
            socket.close();
            serverSocket.close();
        } catch (IOException e) {
            throw new SocketClosingException("Error closing connection");
        }
    }

    public ServerSocket getServerSocket (int port) throws IOException{
        return new ServerSocket(port);
    }
}
