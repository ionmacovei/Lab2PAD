package com.utm.pad.d2c.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by imacovei on 23.11.2016.
 */
@XmlRootElement(name = "Employees")
public class EmployeeList {

    ArrayList<Employee> employeeList;

    public ArrayList<Employee> getEmployeeList() {
        return employeeList;
    }

    @XmlElement(name = "employee")
    public void setEmployeeList(ArrayList<Employee> employeeList) {
        this.employeeList = employeeList;
    }


}
