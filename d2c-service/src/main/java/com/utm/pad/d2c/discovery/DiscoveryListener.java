package com.utm.pad.d2c.discovery;

import com.utm.pad.d2c.model.Location;

import java.io.IOException;
import java.net.*;


import static org.apache.commons.lang3.SerializationUtils.deserialize;
import static org.apache.commons.lang3.SerializationUtils.serialize;

public class DiscoveryListener extends Thread {
    Location dataServerAddress;
    String PROTOCOL_GROUP_ADDRESS;
    Integer PROTOCOL_GROUP_PORT;

    public DiscoveryListener(Location dataServerAddress, String PROTOCOL_GROUP_ADDRESS, Integer PROTOCOL_GROUP_PORT) {
        this.dataServerAddress = dataServerAddress;
        this.PROTOCOL_GROUP_ADDRESS = PROTOCOL_GROUP_ADDRESS;
        this.PROTOCOL_GROUP_PORT = PROTOCOL_GROUP_PORT;
    }

    @Override
    public void run() {
        sendDataServerLocation(receiveClientRequest(), dataServerAddress);
    }

    /**
     * Receives request through UDP multicast and returns client location where
     * "discovery" listener must send information (address and port) about data
     * transport server.
     */
    public Location receiveClientRequest() {
        Location clientLocation = null;
        try {
            MulticastSocket s = new MulticastSocket(PROTOCOL_GROUP_PORT);
            s.joinGroup(InetAddress.getByName(PROTOCOL_GROUP_ADDRESS));

            byte buf[] = new byte[2048];
            DatagramPacket pingPacket = new DatagramPacket(buf, buf.length);
            s.receive(pingPacket);

            clientLocation = deserialize(pingPacket.getData());

            System.out.println("[INFO] -----------------------------------------\n" +
                    "[INFO] Received location request...");

            s.leaveGroup(InetAddress.getByName(PROTOCOL_GROUP_ADDRESS));
            s.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return clientLocation;
    }

    /**
     * Sends data server location using UDP unicast
     *
     * @param clientLocation where information is send
     * @param serverLocation where client app can request data collection
     */
    private void sendDataServerLocation(Location clientLocation, Location serverLocation) {
        try {
            byte[] sendDataServerAddress = serialize(serverLocation);
            DatagramSocket clientSocket = new DatagramSocket();

            DatagramPacket pongPacket = new DatagramPacket(sendDataServerAddress,
                    sendDataServerAddress.length, new InetSocketAddress(clientLocation.getIpAddres(), clientLocation.getPort())
            );
            clientSocket.send(pongPacket);

            clientSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
