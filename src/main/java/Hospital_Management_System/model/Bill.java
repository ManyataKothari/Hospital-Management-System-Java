package Hospital_Management_System.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "bills")
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "appointment_id")
    private Appointment appointment;

    private double amount;
    private boolean paid;
    private LocalDate billingDate;
    private String description;

    // Constructors
    public Bill() {}

    public Bill(Patient patient, Appointment appointment, double amount, String description) {
        this.patient = patient;
        this.appointment = appointment;
        this.amount = amount;
        this.description = description;
        this.paid = false;
        this.billingDate = LocalDate.now();
    }

    // Method overloading - calculate bill with or without tax
    public double getTotalAmount() {
        return amount;
    }

    public double getTotalAmount(double taxPercent) {
        return amount + (amount * taxPercent / 100);
    }

    // Method overriding
    @Override
    public String toString() {
        return "Bill{id=" + id + ", patient=" + patient.getName() +
               ", amount=" + amount + ", paid=" + paid + ", date=" + billingDate + "}";
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Patient getPatient() { return patient; }
    public void setPatient(Patient patient) { this.patient = patient; }

    public Appointment getAppointment() { return appointment; }
    public void setAppointment(Appointment appointment) { this.appointment = appointment; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public boolean isPaid() { return paid; }
    public void setPaid(boolean paid) { this.paid = paid; }

    public LocalDate getBillingDate() { return billingDate; }
    public void setBillingDate(LocalDate billingDate) { this.billingDate = billingDate; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}
