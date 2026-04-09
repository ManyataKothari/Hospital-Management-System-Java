package Hospital_Management_System.model;

import jakarta.persistence.*;

@Entity
@Table(name = "patients")
public class Patient extends Person {

    private int age;
    private String gender;
    private String address;
    private String bloodGroup;

    // Constructors
    public Patient() {}

    public Patient(String name, String phone, String email, int age, String gender, String address, String bloodGroup) {
        super(name, phone, email);
        this.age = age;
        this.gender = gender;
        this.address = address;
        this.bloodGroup = bloodGroup;
    }

    // Method overriding - implementing abstract method from Person
    @Override
    public String getRole() {
        return "PATIENT";
    }

    // Method overloading - different ways to display info
    public String getInfo() {
        return "Patient: " + getName() + " | Age: " + age + " | Blood Group: " + bloodGroup;
    }

    public String getInfo(boolean showAddress) {
        if (showAddress) {
            return getInfo() + " | Address: " + address;
        }
        return getInfo();
    }

    // Method overriding
    @Override
    public String toString() {
        return "Patient{name='" + getName() + "', age=" + age + ", gender='" + gender + "'}";
    }

    // Getters and Setters
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getBloodGroup() { return bloodGroup; }
    public void setBloodGroup(String bloodGroup) { this.bloodGroup = bloodGroup; }
}
