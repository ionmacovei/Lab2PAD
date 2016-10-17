package com.utm.pad.d2c;

import com.utm.pad.d2c.discovery.DiscoveryListener;
import com.utm.pad.d2c.model.Employee;
import com.utm.pad.d2c.model.Location;
import com.utm.pad.d2c.transport.TransportListener;
import com.utm.pad.d2c.config.*;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * if exec-maven-plugin installed
 * the app can be started in terminal with:
 * mvn exec:java -Dexec.args="5555"
 */
public class App1 {
    public static void main(String[] args) {
        int dataServerPort = 4444;
        if (args.length > 0) {
            dataServerPort = Integer.parseInt(args[0]);

        }
        Location l1 = new Location("127.0.0.1", 9001);
        Location l2 = new Location("127.0.0.1", 9002);
        // InetSocketAddress serverLocation = new InetSocketAddress("127.0.0.1", dataServerPort);
        System.out.println("[INFO] -----------------------------------------\n" +
                "[INFO] Node is running... on " + dataServerPort);
        List<Employee> employees1 = new ArrayList<Employee>() {{
            add(new Employee("Laur", "Balaur", "Povesti", 501.0));
            add(new Employee("Fat", "Frumos", "Basme", 502.0));
        }};
        List<Location> locations1 = new ArrayList<Location>();
        locations1.add(l2);

        new Node("A", l1, locations1, employees1).run();

        List<Employee> employees2 = new ArrayList<Employee>() {{
            add(new Employee("Ileana", "Consinzeana", "Basme", 503.0));
            add(new Employee("Danila", "Prepeleac", "Basme", 304.0));
            add(new Employee("Ivan", "Turbinca", "Povesti", 505.0));
        }};
        List<Location> locations2 = new ArrayList<Location>();
        locations2.add(l1);
        new Node("A", l2, locations2, employees2).run();


       /* DiscoveryListener discoveryListener= new DiscoveryListener(new InetSocketAddress("127.0.0.1", dataServerPort));
        discoveryListener.start();

        TransportListener transportListener = new TransportListener(dataServerPort);
        transportListener.start();
*/
        try {
            Thread.sleep(SECONDS.toMillis(100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // transportListener.setStopped(true);

    }
}
