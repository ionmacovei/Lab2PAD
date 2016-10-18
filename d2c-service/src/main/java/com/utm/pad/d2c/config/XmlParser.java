package com.utm.pad.d2c.config;

import com.utm.pad.d2c.model.Employee;
import com.utm.pad.d2c.model.Location;
import com.utm.pad.d2c.servernode.Node;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by imacovei on 18.10.2016.
 */
public class XmlParser {

    public static void getXml(List<Node> nodeList) {
        File fileWithNodes = new File("config.xml");
        Nodes nodes = new Nodes();
        nodes.setNodeList(nodeList);
        try {
            JAXBContext contextObj = JAXBContext.newInstance(Nodes.class, Node.class, Employee.class, Location.class, Locations.class);
            Marshaller marshallerObj = contextObj.createMarshaller();
            marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshallerObj.marshal(nodes, fileWithNodes);
        } catch (JAXBException e) {
            e.printStackTrace();
        }


    }

    public static List<Node> getMesagesFromFile(File f) {
        List<Node> nodesList = null;
        Nodes nodes;
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Nodes.class, Node.class, Employee.class, Location.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            nodes = (Nodes) jaxbUnmarshaller.unmarshal(f);
            nodesList = new LinkedList<>();

            if (nodes.getNodeList() != null)
                nodesList.addAll(nodes.getNodeList());
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return nodesList;
    }
}
