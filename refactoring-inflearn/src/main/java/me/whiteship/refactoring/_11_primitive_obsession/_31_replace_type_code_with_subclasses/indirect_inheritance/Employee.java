package me.whiteship.refactoring._11_primitive_obsession._31_replace_type_code_with_subclasses.indirect_inheritance;

import java.util.List;

public class Employee {

    private String name;
    private EmployeeType type;

    public Employee(String name, String typeValue) {
        this.name = name;
        this.type = employeeType(typeValue);
    }

    public Employee(String name, EmployeeType type) {
        this.name = name;
        this.type = type;
    }

    private EmployeeType employeeType(String typeValue) {
        switch (typeValue) {
            case "engineer": return new Engineer();
            case "manager": return new Manager();
            case "salesman": return new Salesman();
            default: throw new IllegalArgumentException(typeValue);
        }
    }

    public String capitalizedType() {
        return this.type.capitalizedType();
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", type='" + type.toString() + '\'' +
                '}';
    }
}
