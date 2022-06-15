package codes;

public class Student {

    protected String id;
    protected String name;

    public Student() {
    }

    public Student(String id) {
        this.id = id;
    }

    public Student(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        return this.id.equals(((Student) obj).getId());
    }

    @Override
    public String toString() {
        return "----------------------------------\n" + "Id : " + this.id + "\nName : " + this.name;
    }

}
