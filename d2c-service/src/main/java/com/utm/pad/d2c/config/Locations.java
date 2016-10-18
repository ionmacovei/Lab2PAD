package com.utm.pad.d2c.config;

import com.utm.pad.d2c.model.Location;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by imacovei on 18.10.2016.
 */
@XmlRootElement(name = "Locations")
public class Locations {

    List<Location> locationsList;

    public List<Location> getlocationsList() {
        return locationsList;
    }

    @XmlElement(name = "Location")
    public void setlocationsList(List<Location> locationsList) {
        this.locationsList = locationsList;
    }
}
