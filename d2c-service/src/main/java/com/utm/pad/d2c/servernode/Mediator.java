package com.utm.pad.d2c.servernode;

import com.utm.pad.d2c.dslservices.DslServer;
import com.utm.pad.d2c.model.Employee;
import com.utm.pad.d2c.model.Location;
import com.utm.pad.d2c.transport.TransportClient;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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

                DataInputStream in = new DataInputStream(socket.getInputStream());
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());// Blocking call!
                String request = in.readUTF();

                isAccepted = true;

                employees.addAll(TransportClient.getEmployees(getMavenLoaction(noadeLocations), DslServer.requestTrensfer(request, "mediator")));
                Employee[] s = new Employee[employees.size()];
                serialize((Employee[]) employees.toArray(s), socket.getOutputStream());
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

    public Location getMavenLoaction(List<Location> noadeLocations) {
        if (noadeLocations.size() > 0) {
            noadeLocations.sort((l1, l2) -> l1.getNrRelations() - l2.getNrRelations());
            Collections.reverse(noadeLocations);
            return noadeLocations.get(0);
        } else {
            return null;
        }

    }
}
