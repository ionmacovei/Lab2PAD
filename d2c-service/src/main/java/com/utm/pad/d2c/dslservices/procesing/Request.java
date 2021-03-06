package com.utm.pad.d2c.dslservices.procesing;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.utm.pad.d2c.model.Employee;

import java.util.List;

/**
 * Created by imacovei on 07.11.2016.
 */
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Filter.class, name = "filtru"),
        @JsonSubTypes.Type(value = All.class, name = "all"),
        @JsonSubTypes.Type(value = Sort.class, name = "sortare")})
public interface Request {
    List<Employee> getData(List<Employee> employees);

    String getName();

    void setName(String name);
}
