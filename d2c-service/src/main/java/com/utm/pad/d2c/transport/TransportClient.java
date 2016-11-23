package com.utm.pad.d2c.transport;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.utm.pad.d2c.model.Employee;
import com.utm.pad.d2c.model.Location;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.apache.commons.lang3.SerializationUtils.deserialize;
import static org.apache.commons.lang3.SerializationUtils.serialize;

public class TransportClient {

    public static ArrayList<Employee> getEmployees(Location location, String request) throws IOException {
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress(location.getIpAddres(), location.getPort()));
        DataInputStream in = new DataInputStream(socket.getInputStream());
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());

        out.writeUTF(request);
        //  System.out.println(connectorName);
        Employee[] employees = (Employee[]) deserialize(in);
        socket.close();
        return new ArrayList<Employee>(Arrays.asList(employees));

    }

    public static ArrayList<Employee> getEmployeesFrom(Location location, String request) throws IOException {
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress(location.getIpAddres(), location.getPort()));
        DataInputStream in = new DataInputStream(socket.getInputStream());
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());

        out.writeUTF(request);
        //  System.out.println(connectorName);
        String employees = deserialize(in);
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<Employee>> mapType = new TypeReference<List<Employee>>() {
        };
        List<Employee> jsonToEmployeeList = objectMapper.readValue(employees, mapType);
        socket.close();
        return new ArrayList<Employee>(jsonToEmployeeList);

    }
}
