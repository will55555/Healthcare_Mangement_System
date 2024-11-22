package org.WTT.entity;

public class Patient {
    private int PatientId;
    private String firstN; //first name
    private String lastN;//lastname
    private String patientDob;//admission date
    private String admissionDate;//admission date
    private String email;

    public Patient() {
    }

    public Patient(String firstN, int patientId, String lastN, String patientDob, String admissionDate, String email) {
        this.firstN = firstN;
        PatientId = patientId;
        this.lastN = lastN;
        this.patientDob = patientDob;
        this.admissionDate = admissionDate;
        this.email = email;
    }

    public String getPatientDob() {
        return patientDob;
    }

    public void setPatientDob(String patientDob) {
        this.patientDob = patientDob;
    }

    public int getPatientId() {
        return PatientId;
    }

    public void setPatientId(int patientId) {
        PatientId = patientId;
    }

    public String getFirstN() {
        return firstN;
    }

    public void setFirstN(String firstN) {
        this.firstN = firstN;
    }

    public String getLastN() {
        return lastN;
    }

    public void setLastN(String lastN) {
        this.lastN = lastN;
    }

    public String getAdmissionDate() {
        return admissionDate;
    }

    public void setAdmissionDate(String admissionDate) {
        this.admissionDate = admissionDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
