package com.utm.pad.d2c.servernode;

import com.utm.pad.d2c.model.Employee;
import com.utm.pad.d2c.model.Location;
import com.utm.pad.d2c.serialisation.EmployeeJsonSerialisator;
import com.utm.pad.d2c.serialisation.EmployeeXmlSerialisator;
import com.utm.pad.d2c.transport.TransportClient;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang3.SerializationUtils.serialize;

/**
 * Created by imacovei on 20.10.2016.
 */
public class Mediator extends ServerNode {

    ServerSocket serverSocket;
    private List<Location> noadeLocations;
    private boolean isStopped;
    private boolean isAccepted;
    private List<Employee> employees;

    EmployeeJsonSerialisator serialisatorJSON = new EmployeeJsonSerialisator();
    EmployeeXmlSerialisator serialisatorXML = new EmployeeXmlSerialisator();

    public Mediator(Location location, String name, List<Location> noadeLocations) {
        this.location = location;
        this.name = name;
        this.noadeLocations = noadeLocations;

    }


    @Override
    public void run() {
        employees = new ArrayList<Employee>();

        try {
            serverSocket = new ServerSocket(location.getPort());
            while (!isStopped) {
                Socket socket = serverSocket.accept();
                isAccepted = true;

                noadeLocations.forEach(location -> {
                    try {
                        employees.addAll(TransportClient.getEmployeesFrom(location));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                Employee[] s = new Employee[employees.size()];
                serialize(employees.toArray(s), socket.getOutputStream());
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


}
