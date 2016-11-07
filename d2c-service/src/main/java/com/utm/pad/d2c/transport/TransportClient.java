package com.utm.pad.d2c.transport;

import com.utm.pad.d2c.model.Employee;
import com.utm.pad.d2c.model.Location;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;

import static org.apache.commons.lang3.SerializationUtils.deserialize;
import static org.apache.commons.lang3.SerializationUtils.serialize;

public class TransportClient {

    public static ArrayList<Employee> getEmployeesFrom(Location location, String connectorName) throws IOException {
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress(location.getIpAddres(), location.getPort()));
        DataInputStream in = new DataInputStream(socket.getInputStream());
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());

        out.writeUTF(connectorName);
        //  System.out.println(connectorName);
        Employee[] employees = (Employee[]) deserialize(in);
        socket.close();
        return new ArrayList<Employee>(Arrays.asList(employees));

    }
}
