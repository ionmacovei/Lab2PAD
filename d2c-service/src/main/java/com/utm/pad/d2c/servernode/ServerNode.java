package com.utm.pad.d2c.servernode;

import com.utm.pad.d2c.model.Location;

import javax.xml.bind.annotation.XmlElement;

/**
 * Created by imacovei on 20.10.2016.
 */
public abstract class ServerNode implements Runnable {
    @XmlElement(name = "name")
    protected String name;
    @XmlElement(name = "location")
    protected Location location;

    public Location getLocation() {
        return location;
    }
}
