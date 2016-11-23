package com.utm.pad.d2c.serialisation;

import com.utm.pad.d2c.model.Employee;
import com.utm.pad.d2c.model.EmployeeList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.util.List;

/**
 * Created by imacovei on 23.11.2016.
 */
public class EmployeeXmlSerialisator implements EmployeeSerialisator {

    JAXBContext contextObj;

    @Override
    public String serializeObjects(Object objToString) {


        return null;
    }

    @Override
    public List<Employee> deserializeObjects(String listOfEmploees) {
        try {
            contextObj = JAXBContext.newInstance(Employee.class, EmployeeList.class);
            Unmarshaller jaxbUnmarshaller = contextObj.createUnmarshaller();
            StringReader sr = new StringReader(listOfEmploees);
            EmployeeList employeeList = (EmployeeList) jaxbUnmarshaller.unmarshal(sr);
            return employeeList.getEmployeeList();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }
}
