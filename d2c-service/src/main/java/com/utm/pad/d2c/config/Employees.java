package com.utm.pad.d2c.config;

import com.utm.pad.d2c.model.Employee;
import com.utm.pad.d2c.model.Location;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by imacovei on 18.10.2016.
 */
@XmlRootElement(name = "Employeess")
public class Employees {

    public List<Employee> employeesList;

    public List<Employee> getemployeesList() {
        return employeesList;
    }

    @XmlElement(name = "Employees")
    public void setemployeesList(List<Employee> employeesList) {
        this.employeesList = employeesList;
    }
}
