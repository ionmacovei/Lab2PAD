package com.utm.pad.d2c;

import com.utm.pad.d2c.config.XmlParser;
import com.utm.pad.d2c.model.Employee;
import com.utm.pad.d2c.model.Location;
import com.utm.pad.d2c.servernode.Mediator;
import com.utm.pad.d2c.servernode.Node;
import com.utm.pad.d2c.servernode.ServerNode;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * if exec-maven-plugin installed
 * the app can be started in terminal with:
 * mvn exec:java -Dexec.args="5555"
 */
public class App1 {
    public static void main(String[] args) {

        Location lcationMediator = new Location("127.0.0.1", 9010);
        int dataServerPort = 4444;
        if (args.length > 0) {
            dataServerPort = Integer.parseInt(args[0]);

        }
        Location l1 = new Location("127.0.0.1", 9001, 3);
        Location l2 = new Location("127.0.0.1", 9002, 2);
        Location l3 = new Location("127.0.0.1", 9003, 1);
        Location l4 = new Location("127.0.0.1", 9004, 1);
        Location l5 = new Location("127.0.0.1", 9005, 1);



        // InetSocketAddress serverLocation = new InetSocketAddress("127.0.0.1", dataServerPort);
        System.out.println("[INFO] -----------------------------------------\n" +
                "[INFO] Node is running... on " + dataServerPort);

        //Nod 1
        List<Employee> employees1 = new ArrayList<Employee>() {{
            add(new Employee("Laur", "Balaur", "Povesti", 501.0));
            add(new Employee("Fat", "Frumos", "Basme", 502.0));
        }};
        List<Location> locations1 = new ArrayList<Location>();
        locations1.add(l2);
        locations1.add(l4);
        locations1.add(l5);

        Node A = new Node("A", "224.10.10.5", 10105, l1, locations1, employees1);
        //nodu 2
        List<Employee> employees2 = new ArrayList<Employee>() {{
            add(new Employee("Ileana", "Consinzeana", "Basme", 503.0));
            add(new Employee("Danila", "Prepeleac", "Basme", 304.0));
            add(new Employee("Ivan", "Turbinca", "Povesti", 505.0));
        }};
        List<Location> locations2 = new ArrayList<Location>();
        locations2.add(l1);
        locations2.add(l3);
        Node B = new Node("B", "224.10.10.5", 10105, l2, locations2, employees2);

        // nodu 3
        List<Employee> employees3 = new ArrayList<Employee>() {{
            add(new Employee("Ion", "Macovei", "Basme", 503.0));
        }};
        List<Location> locations3 = new ArrayList<Location>();
        locations3.add(l2);
        Node C = new Node("C", "224.10.10.5", 10105, l3, locations3, employees3);

        // nodu 4
        List<Employee> employees4 = new ArrayList<Employee>() {{
            add(new Employee("Vasea", "vasea", "Basme", 503.0));
        }};
        List<Location> locations4 = new ArrayList<Location>();
        locations4.add(l1);
        Node D = new Node("D", "224.10.10.5", 10105, l4, locations4, employees4);

        // nodu 4
        List<Employee> employees5 = new ArrayList<Employee>() {{
            add(new Employee("Iron", "Men", "Basme", 503.0));
        }};
        List<Location> locations5 = new ArrayList<Location>();
        locations5.add(l1);
        Node E = new Node("E", "224.10.10.5", 10105, l5, locations5, employees5);
        List<Node> nodeList = new ArrayList<Node>();
        nodeList.add(A);
        nodeList.add(B);
        nodeList.add(C);
        nodeList.add(D);
        nodeList.add(E);
        XmlParser.getXml(nodeList);

        File fileWithNodes = new File("config.xml");
        List<Node> nodeList1 = XmlParser.getMesagesFromFile(fileWithNodes);
        List<Location> nodeLocations = new ArrayList<Location>();
        Iterator<Node> iter = nodeList1.iterator();
        while (iter.hasNext()) {
            nodeLocations.add(iter.next().getLocation());
        }


        // ServerNode mediator= new Mediator(lcationMediator,"mediator",nodeLocations);
        nodeList1.forEach(node -> node.run());
        try {
            Thread.sleep(SECONDS.toMillis(4));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Mediator(lcationMediator, "mediator", nodeLocations).run();
        try {
            Thread.sleep(SECONDS.toMillis(100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
