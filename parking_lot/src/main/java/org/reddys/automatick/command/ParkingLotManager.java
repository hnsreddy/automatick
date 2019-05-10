package org.reddys.automatick.command;


import org.reddys.automatick.factory.CLICommandFactory;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.Scanner;

public class ParkingLotManager {

    public static void main(String[] args) {
        try {
            ServerSocket listener = new ServerSocket(56060);
            while (true) {
                try {
                    Socket incoming = listener.accept();
                    Scanner commandScanner = new Scanner (incoming.getInputStream());
                    String fullCommandArray[] = commandScanner.nextLine().split(" ");
                    String command = fullCommandArray[0];
                    PrintWriter outputWriter = new PrintWriter(incoming.getOutputStream());
                    if (command.toLowerCase().trim().equals("exit")) {
                        System.exit(0);
                    }
                    String arguments[] = Arrays.copyOfRange(fullCommandArray,1,3);
                    CLICommandFactory.getCommand(command).executeCLICommand(arguments, outputWriter);
                    outputWriter.close();
                }catch (IOException ioe) {
                    ioe.printStackTrace();
                };
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
