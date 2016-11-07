package com.utm.pad.d2c.dslservices.procesing;

import com.utm.pad.d2c.model.Employee;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by imacovei on 07.11.2016.
 */
public class Sort implements Request {
    private String name;
    private String nameAtribut;
    private String typeSort;

    public Sort() {
    }

    public Sort(String name, String nameAtribut, String typeSort) {
        this.name = name;
        this.nameAtribut = nameAtribut;
        this.typeSort = typeSort;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameAtribut() {
        return nameAtribut;
    }

    public void setNameAtribut(String nameAtribut) {
        this.nameAtribut = nameAtribut;
    }

    public String getTypeSort() {
        return typeSort;
    }

    public void setTypeSort(String typeSort) {
        this.typeSort = typeSort;
    }

    @Override
    public List<Employee> getData(List<Employee> employees) {
        sortList(employees, nameAtribut);
        if (typeSort.equalsIgnoreCase("desc")) {
            Collections.reverse(employees);
        }
        return employees;
    }

    public static <T> void sortList(List<T> list, final String propertyName) {

        if (list.size() > 0) {
            Collections.sort(list, new Comparator<T>() {
                @Override
                public int compare(final T object1, final T object2) {
                    String property1 = (String) getSpecifiedFieldValue(propertyName, object1);
                    String property2 = (String) getSpecifiedFieldValue(propertyName, object2);
                    return property1.compareToIgnoreCase(property2);
                }
            });
        }
    }

    public static Object getSpecifiedFieldValue(String property, Object obj) {

        Object result = null;

        try {
            Class<?> objectClass = obj.getClass();
            Field objectField = getDeclaredField(property, objectClass);
            if (objectField != null) {
                objectField.setAccessible(true);
                result = objectField.get(obj);
            }
        } catch (Exception e) {
        }
        return result;
    }

    public static Field getDeclaredField(String fieldName, Class<?> type) {

        Field result = null;
        try {
            result = type.getDeclaredField(fieldName);
        } catch (Exception e) {
        }

        if (result == null) {
            Class<?> superclass = type.getSuperclass();
            if (superclass != null && !superclass.getName().equals("java.lang.Object")) {
                return getDeclaredField(fieldName, type.getSuperclass());
            }
        }
        return result;
    }
}
