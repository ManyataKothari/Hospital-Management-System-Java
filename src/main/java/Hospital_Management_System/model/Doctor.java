package Hospital_Management_System.model;

import jakarta.persistence.*;

@Entity
@Table(name = "doctors")
public class Doctor extends Person {

    private String specialization;
    private String qualification;
    private int experience;
    private boolean available;

    // Constructors
    public Doctor() {}

    public Doctor(String name, String phone, String email, String specialization, String qualification, int experience) {
        super(name, phone, email);
        this.specialization = specialization;
        this.qualification = qualification;
        this.experience = experience;
        this.available = true;
    }

    // Method overriding - implementing abstract method from Person
    @Override
    public String getRole() {
        return "DOCTOR";
    }

    // Method overloading - different ways to display info
    public String getInfo() {
        return "Dr. " + getName() + " | " + specialization + " | " + experience + " yrs exp";
    }

    public String getInfo(boolean showQualification) {
        if (showQualification) {
            return getInfo() + " | " + qualification;
        }
        return getInfo();
    }

    // Method overriding
    @Override
    public String toString() {
        return "Doctor{name='" + getName() + "', specialization='" + specialization + "', available=" + available + "}";
    }

    // Getters and Setters
    public String getSpecialization() { return specialization; }
    public void setSpecialization(String specialization) { this.specialization = specialization; }

    public String getQualification() { return qualification; }
    public void setQualification(String qualification) { this.qualification = qualification; }

    public int getExperience() { return experience; }
    public void setExperience(int experience) { this.experience = experience; }

    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }
}
