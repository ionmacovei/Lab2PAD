package com.utm.pad.d2c.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.net.InetSocketAddress;

@XmlRootElement(name = "Location")
public class Location implements Serializable {
    static final long serialVersionUID = 7588980448693010399L;
    private InetSocketAddress location;
    @XmlElement(name = "ipAddres")
    private String ipAddres;
    @XmlElement(name = "port")
    private Integer port;
    private Integer nrRelations;

    public Location() {
    }

   /* public Location(InetSocketAddress location) {
        this.location = location;
    }

    public Location(InetSocketAddress location, Integer nrRelations) {
        this.location = location;
        this.nrRelations = nrRelations;
    }*/

    public Location(String ipAddress, int port, Integer nrRelations) {

        this.ipAddres = ipAddress;
        this.port = port;
        this.location = new InetSocketAddress(this.ipAddres, this.port);
        this.nrRelations = nrRelations;
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
                "nrRelations" + this.getNrRelations() +
                '}';
    }
}
