package com.utm.pad.d2c.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name = "employee")
public class Employee implements Serializable {
    private static final long serialVersionUID = 1L;
    private String firstName;
    private String lastName;
    private String department;
    private Integer salary;

    public Employee(String firstName, String lastName, String departament, Integer salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = departament;
        this.salary = salary;
    }

    public Employee() {
    }

    public String getFirstName() {
        return firstName;
    }

    @XmlElement(name = "firstName")
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @XmlElement(name = "lastName")
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDepartment() {
        return department;
    }

    @XmlElement(name = "departament")
    public void setDepartment(String department) {
        this.department = department;
    }

    public Integer getSalary() {
        return salary;
    }

    @XmlElement(name = "salary")
    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", department='" + department + '\'' +
                ", salary=" + salary +
                '}';
    }
}
