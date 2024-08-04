package xyz.sadiulhakim;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class FastDBClient implements AutoCloseable {
    private final Socket client;
    private final PrintWriter writer;
    private final BufferedReader reader;

    public FastDBClient(String host, int port) throws IOException {
        client = new Socket(host, port);
        writer = new PrintWriter(client.getOutputStream(), true);
        reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
    }

    public String sendCommand(String command) throws IOException {
        writer.println(command);
        return reader.readLine();
    }

    @Override
    public void close() throws Exception {
        reader.close();
        writer.close();
        client.close();
    }
}
