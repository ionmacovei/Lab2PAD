package com.utm.pad.d2c;


import com.utm.pad.d2c.dslservices.DslClient;
import com.utm.pad.d2c.dslservices.procesing.All;
import com.utm.pad.d2c.dslservices.procesing.Request;
import com.utm.pad.d2c.dslservices.procesing.Sort;
import com.utm.pad.d2c.model.Employee;
import com.utm.pad.d2c.model.Location;
import com.utm.pad.d2c.serialisation.EmployeeJsonSerialisator;
import com.utm.pad.d2c.serialisation.EmployeeSerialisator;
import com.utm.pad.d2c.serialisation.EmployeeXmlSerialisator;
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
        EmployeeSerialisator ser = new EmployeeXmlSerialisator();
        EmployeeSerialisator jser = new EmployeeJsonSerialisator();
        Request r = new Sort("Sort", "lastName", "desc", jser);

        try {
            Location lcationMediator = new Location("127.0.0.1", 9010);
            if (lcationMediator != null) {
                showData(TransportClient.getEmployees(lcationMediator, r));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void showData(EmployeeSerialisator emps) {
        if (emps.validate()) {
            emps.deserializeObjects().forEach(employee -> System.out.println(employee.toString()));
        } else {
            System.out.println("Date invalide!!!");
        }
    }
}