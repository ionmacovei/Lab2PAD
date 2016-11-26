package com.utm.pad.d2c.serialisation;

import com.utm.pad.d2c.model.Employee;
import com.utm.pad.d2c.model.EmployeeList;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;

/**
 * Created by imacovei on 23.11.2016.
 */
public class EmployeeXmlSerialisator implements EmployeeSerialisator {

    final private String type = "xml";
    JAXBContext contextObj;
    private String employeeList;

    public EmployeeXmlSerialisator() {
    }

    public EmployeeXmlSerialisator(String employeeList) {
        this.employeeList = employeeList;
    }

    public String getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(String employeeList) {
        this.employeeList = employeeList;
    }

    @Override
    public String serializeObjects(Object objToString) {
        EmployeeList elist = new EmployeeList();
        elist.setEmployeeList((ArrayList<Employee>) objToString);

        try {
            StringWriter sw = new StringWriter();
            JAXBContext contextObj = JAXBContext.newInstance(Employee.class, EmployeeList.class);
            Marshaller marshallerObj = contextObj.createMarshaller();
            marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshallerObj.marshal(elist, sw);
            this.setEmployeeList(sw.toString());
            return sw.toString();
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public ArrayList<Employee> deserializeObjects() {
        try {
            contextObj = JAXBContext.newInstance(Employee.class, EmployeeList.class);
            Unmarshaller jaxbUnmarshaller = contextObj.createUnmarshaller();
            StringReader sr = new StringReader(employeeList);
            EmployeeList employeeList = (EmployeeList) jaxbUnmarshaller.unmarshal(sr);
            return employeeList.getEmployeeList();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Boolean validate() {
        try {
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new File("employee.xsd"));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(new StringReader(this.getEmployeeList())));
        } catch (IOException | SAXException e) {
            e.printStackTrace();
            System.out.println("Exception: " + e.getMessage());
            return false;
        }
        return true;
    }


}
