package examples.model;

public class EmployeeInfo {
    private int id;
    private String name;

    public EmployeeInfo(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public String toString() {
        return "EmployeeInfo id: " + id + ", name: " + name;
    }
}
