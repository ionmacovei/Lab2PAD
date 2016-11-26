package com.utm.pad.d2c.servernode;

import com.utm.pad.d2c.dslservices.DslClient;
import com.utm.pad.d2c.dslservices.DslServer;
import com.utm.pad.d2c.dslservices.procesing.Request;
import com.utm.pad.d2c.model.Employee;
import com.utm.pad.d2c.model.Location;
import com.utm.pad.d2c.serialisation.EmployeeSerialisator;
import com.utm.pad.d2c.transport.TransportClient;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by imacovei on 20.10.2016.
 */
public class Mediator extends ServerNode {

    ServerSocket serverSocket;
    EmployeeSerialisator serialisator;
    private List<Location> noadeLocations;
    private boolean isStopped;
    private boolean isAccepted;
    private List<Employee> employees;
    private DataInputStream inputStream = null;
    private DataOutputStream outputStream = null;

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
                inputStream = new DataInputStream(socket.getInputStream());
                outputStream = new DataOutputStream(socket.getOutputStream());

                Request r = DslServer.getRequestfromString(inputStream.readUTF());//deserialize(socket.getInputStream());
                noadeLocations.forEach(location -> {
                    try {
                        employees.addAll(TransportClient.getEmployeesFrom(location));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                employees = r.getData(employees);
                serialisator = r.getSerialisatorType();
                serialisator.serializeObjects(employees);
                outputStream.writeUTF(DslClient.getRequestForClient(serialisator));


                isAccepted = false;
                socket.close();
            }
        } catch (SocketException e) {
            e.printStackTrace();
            System.out.println("[WARNING] ----------------------------------------- \n" +
                    "[WARNING] Waiting time expired... Socket is closed.");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
