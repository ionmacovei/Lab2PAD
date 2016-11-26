package com.utm.pad.d2c.transport;

import com.utm.pad.d2c.model.Employee;
import com.utm.pad.d2c.model.Location;
import com.utm.pad.d2c.serialisation.EmployeeJsonSerialisator;
import com.utm.pad.d2c.serialisation.EmployeeSerialisator;
import com.utm.pad.d2c.serialisation.EmployeeXmlSerialisator;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.List;

import static org.apache.commons.lang3.SerializationUtils.serialize;

public class TransportListener extends Thread {
    ServerSocket serverSocket;
    EmployeeSerialisator serialisatorJSON = new EmployeeJsonSerialisator();
    EmployeeSerialisator serialisatorXML = new EmployeeXmlSerialisator();
    private String nodeName;
    private int serverPort;
    private boolean isStopped;
    private boolean isAccepted;
    private List<Location> conectionPorts;
    private List<Employee> employees;

    public TransportListener(Integer serverPort, List<Location> conectionPorts, List<Employee> employees) {
        this.conectionPorts = conectionPorts;
        this.employees = employees;
        this.serverPort = serverPort;
        isStopped = false;
    }

    @Override
    public void run() {
        serialisatorJSON.serializeObjects(employees);
        serialisatorXML.serializeObjects(employees);
        try {
            serverSocket = new ServerSocket(serverPort);
            while (!isStopped) {
                Socket socket = serverSocket.accept();
                isAccepted = true;
                if (serverPort < 9002) {
                    serialize(serialisatorXML, socket.getOutputStream());
                } else {
                    serialize(serialisatorJSON, socket.getOutputStream());
                }
                socket.close();
                isAccepted = false;
            }
        } catch (SocketException e) {
            e.printStackTrace();
            System.out.println("[WARNING] ----------------------------------------- \n" +
                    "[WARNING] Waiting time expired... Socket is closed.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
