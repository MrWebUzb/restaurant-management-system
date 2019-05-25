package models;

public class Worker {
    private int id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String lavozim;

    public Worker(int id, String firstName, String middleName, String lastName,  String lavozim) {
        this.id = id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.lavozim = lavozim;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLavozim() {
        return lavozim;
    }

    public void setLavozim(String lavozim) {
        this.lavozim = lavozim;
    }

    @Override
    public String toString() {
        return "Worker{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", lavozim='" + lavozim + '\'' +
                '}';
    }
}
