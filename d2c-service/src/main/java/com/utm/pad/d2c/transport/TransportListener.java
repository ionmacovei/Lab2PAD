package com.utm.pad.d2c.transport;

import com.utm.pad.d2c.model.Employee;
import com.utm.pad.d2c.model.Location;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Iterator;
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

    public TransportListener(Integer serverPort, List<Location> conectionPorts, List<Employee> employees) {
        this.conectionPorts = conectionPorts;
        this.employees = employees;
        this.serverPort = serverPort;
        isStopped = false;
    }

    public TransportListener(Integer serverPort, String name) {
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
                String conectorName = in.readUTF();
                if (conectorName.equalsIgnoreCase("client")) {
                    // getEmployeFromNodes(socket, out);
                    if (conectionPorts.size() >= 1) {
                        conectionPorts.forEach(location -> {
                            try {
                                employees.addAll(TransportClient.getEmployeesFrom(location, String.valueOf(serverPort)));
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        });

                    }
                } else {
                    Employee[] s = new Employee[employees.size()];
                    serialize((Employee[]) employees.toArray(s), out);

                    socket.close();
                    isAccepted = false;
                }
                Employee[] s = new Employee[employees.size()];
                serialize((Employee[]) employees.toArray(s), socket.getOutputStream());

                socket.close();
                isAccepted = false;




               /* else {
                    Integer port = Integer.parseInt(conectorName);
                    deleteMavenFromList(port, conectionPorts);
                    getEmployeFromNodes(socket, out);
                }*/


            }
        } catch (SocketException e) {
            System.out.println("[WARNING] ----------------------------------------- \n" +
                    "[WARNING] Waiting time expired... Socket is closed.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Location> deleteMavenFromList(Integer port, List<Location> locations) {
        Iterator<Location> l = locations.iterator();
        while (l.hasNext()) {
            Location o = l.next();
            if (o.getPort().equals(port))
                l.remove();
        }

        return locations;
    }

   /* private List<Employee> getEmployeFromNodes(Socket socket, DataOutputStream out) {
        try {
            if (conectionPorts.size() >= 1) {
                conectionPorts.forEach(location -> {
                    try {
                        employees.addAll(TransportClient.getEmployeesFrom(location, String.valueOf(serverPort)));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });

            }
            Employee[] s = new Employee[employees.size()];
            serialize((Employee[]) employees.toArray(s), out);

            socket.close();
            isAccepted = false;
        } catch (SocketException e) {
            System.out.println("[WARNING] ----------------------------------------- \n" +
                    "[WARNING] Waiting time expired... Socket is closed.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }*/


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
