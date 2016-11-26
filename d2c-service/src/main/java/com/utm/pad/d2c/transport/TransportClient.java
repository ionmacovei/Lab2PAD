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
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;

import static org.apache.commons.lang3.SerializationUtils.deserialize;

public class TransportClient {
    /**
     * se foloseste pentru a primi date  intrun anumit format,prelucrate conform  specificatiilor din request
     *
     * @param location
     *
     * @param request
     * @return
     * @throws IOException
     */
    public static EmployeeSerialisator getEmployees(Location location, Request request) throws IOException, ClassNotFoundException {
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress(location.getIpAddres(), location.getPort()));
        DataInputStream in = new DataInputStream(socket.getInputStream());
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        out.writeUTF(DslClient.getRequestForClient(request));
        String s = in.readUTF();
        EmployeeSerialisator emps = DslServer.getDatafromString(s);
        socket.close();
        return emps;

    }

    /**
     * se foloseste pentru a optine date de la locatia din parametru
     *
     * @param location
     * @return
     * @throws IOException
     */
    public static ArrayList<Employee> getEmployeesFrom(Location location) throws IOException {
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress(location.getIpAddres(), location.getPort()));
        EmployeeSerialisator serializedEmployee = deserialize(socket.getInputStream());
        System.out.println(serializedEmployee.getEmployeeList() + "\n");

        return serializedEmployee.deserializeObjects();

    }
}
