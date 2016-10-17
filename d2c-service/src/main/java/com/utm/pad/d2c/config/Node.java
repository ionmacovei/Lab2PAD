package com.utm.pad.d2c.config;

import com.utm.pad.d2c.discovery.DiscoveryListener;
import com.utm.pad.d2c.model.Employee;
import com.utm.pad.d2c.model.Location;
import com.utm.pad.d2c.transport.TransportListener;

import java.net.InetSocketAddress;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * Created by imacovei on 15.10.2016.
 */
public class Node implements Runnable {

    private String id;
    private Location nodeLocation;
    private List<Location> locations;
    // private List<Location> onlineConnectionPorts;
    private List<Employee> employees;
    private boolean discover = true;

    public Node(String id, Location nodeLocation, List<Location> locations, List<Employee> employees) {
        this.id = id;
        this.nodeLocation = nodeLocation;
        this.locations = locations;
        this.employees = employees;


    }

    @Override
    public void run() {
        DiscoveryListener discoveryListener = new DiscoveryListener(nodeLocation.getLocation());
        discoveryListener.start();
        TransportListener transportListener = new TransportListener(nodeLocation.getLocation().getPort(), locations, employees);
        transportListener.start();
    }
}
