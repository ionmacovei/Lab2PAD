package com.utm.pad.d2c.transport;

import com.utm.pad.d2c.dslservices.DslClient;
import com.utm.pad.d2c.dslservices.DslServer;
import com.utm.pad.d2c.dslservices.procesing.Request;
import com.utm.pad.d2c.model.Employee;
import com.utm.pad.d2c.model.Location;
import com.utm.pad.d2c.serialisation.EmployeeSerialisator;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.List;

import static org.apache.commons.lang3.SerializationUtils.serialize;

public class TransportListener extends Thread {
    ServerSocket serverSocket;
    private String nodeName;
    private int serverPort;
    private boolean isStopped;
    private boolean isAccepted;
    private List<Location> conectionPorts;
    private List<Employee> employees;
    EmployeeSerialisator empSerialisator;

    public TransportListener(Integer serverPort, List<Location> conectionPorts, List<Employee> employees) {
        this.conectionPorts = conectionPorts;
        this.employees = employees;
        this.serverPort = serverPort;
        isStopped = false;
    }

    public TransportListener(Integer serverPort, String name, Location mavenLocation) {
        this.serverPort = serverPort;
        this.nodeName = name;

    }

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

    @Override
    public void run() {

        try {
            serverSocket = new ServerSocket(serverPort);
            while (!isStopped) {
                Socket socket = serverSocket.accept();
                DataInputStream in = new DataInputStream(socket.getInputStream());
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                isAccepted = true;
                String request = in.readUTF();
                // System.out.println(request);
                Request req = DslServer.getRequestfromString(request);

                if (req.getName().equalsIgnoreCase("mediator")) {


                    if (conectionPorts.size() >= 1) {
                        conectionPorts.forEach(location -> {
                            try {
                                employees.addAll(TransportClient.getEmployeesFrom(location, DslServer.requestTrensfer(request, "maven")));
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        });
                        List<Employee> prosesedEmployeesList = req.getData(employees);

                        Employee[] s = new Employee[prosesedEmployeesList.size()];

                        serialize((Employee[]) prosesedEmployeesList.toArray(s), out);

                        socket.close();
                        isAccepted = false;
                    }
                } else if (req.getName().equalsIgnoreCase("maven")) {

                    Employee[] s = new Employee[employees.size()];
                    serialize(DslClient.getRequestForClient(employees), socket.getOutputStream());

                    socket.close();
                    isAccepted = false;
                }

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
