package hbys.AdminPanelDAO;

import hbys.AdminModels.Patient1;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatientDAO {
    private Connection connection;

    // Constructor
    public PatientDAO(Connection connection) {
        this.connection = connection;
    }

    // Get all patients
    public List<Patient1> getAllPatients() throws SQLException {
        String query = "SELECT * FROM Patients";
        List<Patient1> patients = new ArrayList<>();

        try (PreparedStatement ps = connection.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                String dateOfBirth = rs.getDate("DateOfBirth") != null
                        ? rs.getDate("DateOfBirth").toString()
                        : "";

                patients.add(new Patient1(
                        rs.getInt("PatientID"),
                        rs.getInt("UserID"),
                        dateOfBirth,
                        rs.getString("Gender"),
                        rs.getString("ContactNumber"),
                        rs.getString("Address"),
                        rs.getString("RegistrationDate")
                ));
            }
        }
        return patients;
    }

    // Add a patient
    public void addPatient(Patient1 patient) throws SQLException {
        String query = "INSERT INTO Patients (UserID, DateOfBirth, Gender, ContactNumber, Address, RegistrationDate) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, patient.getUserID());
            ps.setString(2, patient.getDateOfBirth());
            ps.setString(3, patient.getGender());
            ps.setString(4, patient.getContactNumber());
            ps.setString(5, patient.getAddress());
            ps.setTimestamp(6, Timestamp.valueOf(patient.getRegistrationDate()));
            ps.executeUpdate();
        }
    }

    // Update a patient
    public void updatePatient(Patient1 patient) throws SQLException {
        String query = "UPDATE Patients SET UserID = ?, DateOfBirth = ?, Gender = ?, ContactNumber = ?, Address = ?, RegistrationDate = ? WHERE PatientID = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, patient.getUserID());
            ps.setString(2, patient.getDateOfBirth());
            ps.setString(3, patient.getGender());
            ps.setString(4, patient.getContactNumber());
            ps.setString(5, patient.getAddress());
            ps.setTimestamp(6, Timestamp.valueOf(patient.getRegistrationDate()));
            ps.setInt(7, patient.getPatientID());
            ps.executeUpdate();
        }
    }

    // Delete a patient
    public void deletePatient(int patientID) throws SQLException {
        String query = "DELETE FROM Patients WHERE PatientID = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, patientID);
            ps.executeUpdate();
        }
    }

    // Search for patients
    public List<Patient1> searchPatients(String keyword) throws SQLException {
        String query = "SELECT * FROM Patients WHERE ContactNumber LIKE ? OR Address LIKE ?";
        List<Patient1> patients = new ArrayList<>();

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            String searchKeyword = "%" + keyword + "%";
            ps.setString(1, searchKeyword);
            ps.setString(2, searchKeyword);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String dateOfBirth = rs.getDate("DateOfBirth") != null
                            ? rs.getDate("DateOfBirth").toString()
                            : "";

                    patients.add(new Patient1(
                            rs.getInt("PatientID"),
                            rs.getInt("UserID"),
                            dateOfBirth,
                            rs.getString("Gender"),
                            rs.getString("ContactNumber"),
                            rs.getString("Address"),
                            rs.getString("RegistrationDate")
                    ));
                }
            }
        }

        return patients;
    }
}

