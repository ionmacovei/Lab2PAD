package com.utm.pad.d2c.servernode;

import com.utm.pad.d2c.discovery.DiscoveryListener;
import com.utm.pad.d2c.model.Employee;
import com.utm.pad.d2c.model.Location;
import com.utm.pad.d2c.transport.TransportListener;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by imacovei on 15.10.2016.
 */
@XmlRootElement(name = "Node")
@XmlAccessorType(XmlAccessType.FIELD)
public class Node extends ServerNode {
    @XmlElement(name = "multicastAddres")
    private String multicastAddres;
    @XmlElement(name = "multicastPort")
    private Integer multicastPort;
    /*@XmlElement(name = "location")
    private Location nodeLocation;*/
    @XmlElement(name = "relation")
    private List<Location> locations;
    @XmlElement(name = "employees")
    private List<Employee> employees;
    public Node() {

    }

    public Node(String name, String multicastAddres, Integer multicastPort, Location nodeLocation, List<Location> locations, List<Employee> employees) {
        this.name = name;
        this.location = nodeLocation;
        this.locations = locations;
        this.employees = employees;
        this.multicastAddres = multicastAddres;
        this.multicastPort = multicastPort;


    }

    public String getMulticastAddres() {
        return multicastAddres;
    }

    public void setMulticastAddres(String multicastAddres) {
        this.multicastAddres = multicastAddres;
    }

    public Integer getMulticastPort() {
        return multicastPort;
    }

    public void setMulticastPort(Integer multicastPort) {
        this.multicastPort = multicastPort;
    }

    @Override
    public void run() {
        DiscoveryListener discoveryListener = new DiscoveryListener(location, multicastAddres, multicastPort);
        discoveryListener.start();
        TransportListener transportListener = new TransportListener(location.getPort(), locations, employees);
        transportListener.start();
    }
}
