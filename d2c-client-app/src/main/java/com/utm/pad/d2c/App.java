package com.utm.pad.d2c;


import com.utm.pad.d2c.dslservices.DslClient;
import com.utm.pad.d2c.dslservices.procesing.All;
import com.utm.pad.d2c.model.Employee;
import com.utm.pad.d2c.model.Location;
import com.utm.pad.d2c.transport.TransportClient;
import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Can be started in terminal with:
 * mvn exec:java
 */
public class App {
    public static void main(String[] args) {
        System.out.println("[INFO] -----------------------------------------\n" +
                "[INFO] Client is running...");

        try {
            Location lcationMediator = new Location("127.0.0.1", 9010);
            if (lcationMediator != null) {
                showFiltered(TransportClient.getEmployees(lcationMediator, DslClient.getRequestForClient(new All("client"))));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void showFiltered(ArrayList<Employee> list) {
        System.out.println("[Result] -----------------------------------------\n" +
                "Discovered employees: " +
                list.stream().collect(Collectors.toList())
                        //collect(Collectors.groupingBy(Employee::getDepartment))
                        .toString()
        );
    }
}