package org.reddys.automatick.command;

import java.io.IOException;
import java.io.Writer;

public interface CLICommand {

    public void executeCLICommand(String args[], Writer outputWriter) throws IOException;
}
