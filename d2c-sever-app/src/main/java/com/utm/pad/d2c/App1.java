package com.utm.pad.d2c;

import com.utm.pad.d2c.discovery.DiscoveryListener;
import com.utm.pad.d2c.transport.TransportListener;

import java.net.InetSocketAddress;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * if exec-maven-plugin installed
 * the app can be started in terminal with:
 * mvn exec:java -Dexec.args="5555"
 */
public class App1 {
    public static void main(String[] args) {
        int dataServerPort = 4444;
        if (args.length > 0) {
            dataServerPort = Integer.parseInt(args[0]);
        }

        InetSocketAddress serverLocation = new InetSocketAddress("127.0.0.1", dataServerPort);
        System.out.println("[INFO] -----------------------------------------\n" +
                "[INFO] Server is running... on " + dataServerPort);

        new DiscoveryListener(serverLocation)
                .start();

        TransportListener transportListener = new TransportListener(dataServerPort);
        transportListener.start();

        try {
            Thread.sleep(SECONDS.toMillis(100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        transportListener.setStopped(true);

    }
}
