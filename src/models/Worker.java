package models;

public class Worker {
    public static int workersCurrId = 0;
    private int id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String lavozim;
    private String createdTime;

    public Worker(int id, String firstName, String middleName, String lastName,  String lavozim, String created_at) {
        this.id = id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.lavozim = lavozim;
        this.createdTime = created_at;
        workersCurrId++;
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

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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
                ", created_at='" + createdTime + '\'' +
                '}';
    }
}
