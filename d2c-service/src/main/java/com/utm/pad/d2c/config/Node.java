package com.utm.pad.d2c.config;

import com.utm.pad.d2c.model.Employee;
import com.utm.pad.d2c.model.Location;

import java.util.List;

/**
 * Created by imacovei on 15.10.2016.
 */
public class Node {

    private String id;
    private Location nodeLocation;
    private List<Integer> conectionPorts;
    private List<Integer> onlineConnectionPorts;
    private List<Employee> employees;
    private boolean discover = true;
    private Integer connections = 0;

    public Node(String id, Location nodeLocation, List<Integer> conectionPorts, List<Employee> employees) {
        this.id = id;
        this.nodeLocation = nodeLocation;
        this.conectionPorts = conectionPorts;
        this.employees = employees;


    }

}
