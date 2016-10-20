package com.utm.pad.d2c.servernode;

import com.utm.pad.d2c.model.Location;
import com.utm.pad.d2c.transport.TransportListener;

/**
 * Created by imacovei on 20.10.2016.
 */
public class Mediator extends ServerNode {
    Mediator(Location location, String name) {
        this.location = location;
        this.name = name;
    }

    @Override
    public void run() {
        new TransportListener(location.getPort(), name).start();

    }
}
