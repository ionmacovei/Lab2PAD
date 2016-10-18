package com.utm.pad.d2c.servernode;

import com.utm.pad.d2c.discovery.DiscoveryListener;
import com.utm.pad.d2c.model.Employee;
import com.utm.pad.d2c.model.Location;
import com.utm.pad.d2c.transport.TransportListener;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.net.InetSocketAddress;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * Created by imacovei on 15.10.2016.
 */
@XmlRootElement(name = "Node")
@XmlAccessorType(XmlAccessType.FIELD)
public class Node implements Runnable {
    @XmlElement(name = "name")
    private String id;
    @XmlElement(name = "location")
    private Location nodeLocation;
    @XmlElement(name = "locations")
    private List<Location> locations;
    @XmlElement(name = "employees")
    private List<Employee> employees;

    public Node() {

    }
    public Node(String id, Location nodeLocation, List<Location> locations, List<Employee> employees) {
        this.id = id;
        this.nodeLocation = nodeLocation;
        this.locations = locations;
        this.employees = employees;


    }

    @Override
    public void run() {
        DiscoveryListener discoveryListener = new DiscoveryListener(nodeLocation);
        discoveryListener.start();
        TransportListener transportListener = new TransportListener(nodeLocation.getPort(), locations, employees);
        transportListener.start();
    }
}
