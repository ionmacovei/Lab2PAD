package com.utm.pad.d2c.model;

import java.io.Serializable;
import java.net.InetSocketAddress;

public class Location implements Serializable {
    static final long serialVersionUID = 7588980448693010399L;
    private InetSocketAddress location;
    private Integer nrRelations;

    public Location() {
    }

    public Location(InetSocketAddress location) {
        this.location = location;
    }

    public Location(InetSocketAddress location, Integer nrRelations) {
        this.location = location;
        this.nrRelations=nrRelations;
    }

    public Location(String ipAddress, int port) {
        location = new InetSocketAddress(ipAddress, port);
    }

    public InetSocketAddress getLocation() {
        return location;
    }

    public void setLocation(InetSocketAddress location) {
        this.location = location;
    }

    public Integer getNrRelations() {
        return nrRelations;
    }

    public void setNrRelations(Integer nrRelations) {
        this.nrRelations = nrRelations;
    }


    @Override
    public String toString() {
        return "Location{" +
                "ip address=" + location.getHostString() + ", " +
                "port=" + location.getPort() +
                '}';
    }
}
