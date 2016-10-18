package com.utm.pad.d2c.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name = "Location")
@XmlAccessorType(XmlAccessType.FIELD)
public class Location implements Serializable {
    static final long serialVersionUID = 7588980448693010399L;
    @XmlElement(name = "ipAddres")
    private String ipAddres;
    @XmlElement(name = "port")
    private Integer port;
    private Integer nrRelations;

    public Location() {
    }

    public Location(String ipAddress, int port, Integer nrRelations) {

        this.ipAddres = ipAddress;
        this.port = port;
        this.nrRelations = nrRelations;
    }

    public Location(String ipAddress, int port) {
        this.ipAddres = ipAddress;
        this.port = port;

    }

    public String getIpAddres() {
        return ipAddres;
    }

    public void setIpAddres(String ipAddres) {
        this.ipAddres = ipAddres;
    }

    public Integer getPort() {
        return port;
    }

   /* public Location(InetSocketAddress location) {
        this.location = location;
    }

    public Location(InetSocketAddress location, Integer nrRelations) {
        this.location = location;
        this.nrRelations = nrRelations;
    }*/

    public void setPort(Integer port) {
        this.port = port;
    }

   /*   public InetSocketAddress getLocation() {
        return location;
    }*/

/*
    public void setLocation(InetSocketAddress location) {
        this.location = location;
    }
*/

    public Integer getNrRelations() {
        return nrRelations;
    }

    public void setNrRelations(Integer nrRelations) {
        this.nrRelations = nrRelations;
    }


    @Override
    public String toString() {
        return "Location{" +
                "ip address=" + this.ipAddres + ", " +
                "port= " + this.port + ", " +
                "nrRelations= " + this.getNrRelations() +
                '}';
    }
}
