package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {

    private static final Logger LOG = LoggerFactory.getLogger(EchoServer.class.getName());

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream output = socket.getOutputStream();
                     BufferedReader input = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    output.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    String msg = input.readLine();
                    if (msg.contains("msg=Exit ")) {
                        server.close();
                    }
                    if (msg.contains("msg=Hello ")) {
                        output.write("Hello\r\n\r\n".getBytes());
                    } else {
                        msg = msg.split("msg=")[1].split(" ")[0] + "\r\n\r\n";
                        output.write(msg.getBytes());
                    }
                    output.flush();
                } catch (IOException ex) {
                    LOG.error("Error in client socket: ", ex);
                }
            }
        } catch (IOException ex) {
            LOG.error("Error in server socket: ", ex);
        }
    }
}

