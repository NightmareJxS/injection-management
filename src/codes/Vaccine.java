package codes;

public class Vaccine {

    protected String id;
    protected String name;

    public Vaccine() {
    }

    public Vaccine(String id) {
        this.id = id;
    }

    public Vaccine(String id, String name) {
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
        return this.id.equals(((Vaccine) obj).getId());
    }

    @Override
    public String toString() {
        return "----------------------------------\n" + "Id : " + this.id + "\nName : " + this.name;
    }

}
