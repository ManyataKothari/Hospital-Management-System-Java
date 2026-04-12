package Hospital_Management_System.controller;

import Hospital_Management_System.model.Appointment;
import Hospital_Management_System.model.Doctor;
import Hospital_Management_System.model.Patient;
import Hospital_Management_System.service.AppointmentService;
import Hospital_Management_System.service.DoctorService;
import Hospital_Management_System.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Controller
@RequestMapping("/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private DoctorService doctorService;

    @GetMapping
    public String listAppointments(Model model) {
        model.addAttribute("appointments", appointmentService.getAllAppointments());
        return "appointments/list";
    }

    // Show add form - optionally pre-select a patient
    @GetMapping("/add")
    public String showAddForm(
            @RequestParam(required = false) Long patientId,
            Model model) {
        model.addAttribute("doctors", doctorService.getAllDoctors());
        model.addAttribute("patients", patientService.getAllPatients());
        // If patientId passed, pre-select that patient
        if (patientId != null) {
            patientService.getPatientById(patientId).ifPresent(p ->
                model.addAttribute("selectedPatient", p));
        }
        return "appointments/add";
    }

    @PostMapping("/add")
    public String addAppointment(
            @RequestParam Long patientId,
            @RequestParam Long doctorId,
            @RequestParam String appointmentDate,
            @RequestParam String appointmentTime,
            @RequestParam(required = false) String notes) {

        Patient patient = patientService.getPatientById(patientId).orElse(null);
        Doctor doctor = doctorService.getDoctorById(doctorId).orElse(null);

        Appointment appointment = new Appointment();
        appointment.setPatient(patient);
        appointment.setDoctor(doctor);
        appointment.setAppointmentDate(LocalDate.parse(appointmentDate));
        appointment.setAppointmentTime(LocalTime.parse(appointmentTime));
        appointment.setStatus("SCHEDULED");
        appointment.setNotes(notes);

        appointmentService.bookAppointment(appointment);

        // Redirect back to patient history if came from there
        return "redirect:/appointments";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        appointmentService.getAppointmentById(id).ifPresent(a ->
            model.addAttribute("appointment", a));
        model.addAttribute("patients", patientService.getAllPatients());
        model.addAttribute("doctors", doctorService.getAllDoctors());
        return "appointments/edit";
    }

    @PostMapping("/edit/{id}")
    public String updateAppointment(
            @PathVariable Long id,
            @RequestParam Long patientId,
            @RequestParam Long doctorId,
            @RequestParam String appointmentDate,
            @RequestParam String appointmentTime,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String notes) {

        Patient patient = patientService.getPatientById(patientId).orElse(null);
        Doctor doctor = doctorService.getDoctorById(doctorId).orElse(null);

        Appointment appointment = new Appointment();
        appointment.setId(id);
        appointment.setPatient(patient);
        appointment.setDoctor(doctor);
        appointment.setAppointmentDate(LocalDate.parse(appointmentDate));
        appointment.setAppointmentTime(LocalTime.parse(appointmentTime));
        appointment.setStatus(status != null ? status : "SCHEDULED");
        appointment.setNotes(notes);

        appointmentService.updateAppointment(appointment);
        return "redirect:/appointments";
    }

    @GetMapping("/cancel/{id}")
    public String cancelAppointment(@PathVariable Long id) {
        appointmentService.cancelAppointment(id);
        return "redirect:/appointments";
    }

    @GetMapping("/delete/{id}")
    public String deleteAppointment(@PathVariable Long id) {
        appointmentService.deleteAppointment(id);
        return "redirect:/appointments";
    }
}