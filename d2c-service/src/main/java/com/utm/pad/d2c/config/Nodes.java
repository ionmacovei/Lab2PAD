package com.utm.pad.d2c.config;

import com.utm.pad.d2c.servernode.Node;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by imacovei on 18.10.2016.
 */
@XmlRootElement(name = "ServerNodes")
public class Nodes {

    List<Node> nodeList;

    public List<Node> getNodeList() {
        return nodeList;
    }

    @XmlElement(name = "Node")
    public void setNodeList(List<Node> nodeList) {
        this.nodeList = nodeList;
    }


}
