package com.utm.pad.d2c.dslservices.procesing;

import com.utm.pad.d2c.model.Employee;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by imacovei on 07.11.2016.
 */
public class Filter {
    private String name;
    private Integer filterValue;
    private String comparatorSign;


    public Filter() {

    }

    public Filter(String name, Integer filterValue, String comparatorSign) {
        this.filterValue = filterValue;
        this.comparatorSign = comparatorSign;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getFilterValue() {
        return filterValue;
    }

    public void setFilterValue(Integer filterValue) {
        this.filterValue = filterValue;
    }

    public String getComparatorSign() {
        return comparatorSign;
    }

    public void setComparatorSign(String comparatorSign) {
        this.comparatorSign = comparatorSign;
    }


    public List<Employee> getData(List<Employee> employees) {
        if (comparatorSign.equalsIgnoreCase("<")) {
            List<Employee> filtredList = employees.stream()
                    .filter(e -> e.getSalary() < filterValue)
                    .sorted(Comparator.comparing(Employee::getLastName))
                    .collect(Collectors.toList());
            return filtredList;


        } else if (comparatorSign.equalsIgnoreCase(">")) {
            List<Employee> filtredList = employees.stream()
                    .filter(e -> e.getSalary() > filterValue)
                    .sorted(Comparator.comparing(Employee::getLastName))
                    .collect(Collectors.toList());
            return filtredList;

        } else if (comparatorSign.equalsIgnoreCase("=")) {
            List<Employee> filtredList = employees.stream()
                    .filter(e -> e.getSalary().equals(filterValue))
                    .sorted(Comparator.comparing(Employee::getLastName))
                    .collect(Collectors.toList());
            return filtredList;


        }
        return null;
    }
}
