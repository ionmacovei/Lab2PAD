package com.utm.pad.d2c.transport;

import com.utm.pad.d2c.model.Employee;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

import static org.apache.commons.lang3.SerializationUtils.serialize;

public class TransportListener extends Thread {
    private int serverPort;
    private boolean isStopped;
    private boolean isAccepted;
    ServerSocket serverSocket;

    public boolean isStopped() {
        return isStopped;
    }

    public void setStopped(boolean isStopped) {
        this.isStopped = isStopped;
        if (!isAccepted) {
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public TransportListener(int serverPort) {
        this.serverPort = serverPort;
        isStopped = false;
    }

    @Override
    public void run() {

        try {
            serverSocket = new ServerSocket(serverPort);
            while (!isStopped) {
                Socket socket = serverSocket.accept();  // Blocking call!
                // You can use non-blocking approach
                isAccepted = true;
                Employee[] s = new Employee[getEmployees().size()];
                serialize((Employee[]) getEmployees().toArray(s), socket.getOutputStream());
                socket.close();
                isAccepted = false;
            }
        } catch (SocketException e) {
            System.out.println("[WARNING] ----------------------------------------- \n" +
                    "[WARNING] Waiting time expired... Socket is closed.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private ArrayList<Employee> getEmployees() {
        return new ArrayList<Employee>() {{
            add(new Employee("Laur", "Balaur", "Povesti", 501.0));
            add(new Employee("Fat", "Frumos", "Basme", 502.0));
            add(new Employee("Ileana", "Consinzeana", "Basme", 503.0));
            add(new Employee("Danila", "Prepeleac", "Basme", 304.0));
            add(new Employee("Ivan", "Turbinca", "Povesti", 505.0));
        }};
    }
}
