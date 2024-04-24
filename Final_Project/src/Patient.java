package src;

import java.util.ArrayList;

public class Patient {
    static int patientIDCounter = 0;
    private int patientID;

    private ArrayList<MedicalRecord> patientMedicalRecord;
    private Location patientCurrentLocation;

    public Patient(Location _location) {
        patientID = patientIDCounter;
        patientIDCounter++;
        patientCurrentLocation = _location;
        this.patientMedicalRecord = new ArrayList<MedicalRecord>();
        // First time assume the patient is healthy.
        this.patientMedicalRecord.add(new MedicalRecord(HealthCondition.HEALTHY));
    }

    public int getPatientID() {
        return patientID;
    }

    public Location getPatientCurrentLocation() {
        return patientCurrentLocation;
    }

    public void setPatientCurrentLocation(Location _patientCurrentLocation) {
        this.patientCurrentLocation = _patientCurrentLocation;
    }

    public MedicalRecord getLatestPatientMedicalRecord() {
        if (!patientMedicalRecord.isEmpty()) {
            return patientMedicalRecord.get(patientMedicalRecord.size() - 1);
        } else {
            System.out.println("We do not have any the patient's medical record.");
            return null;
        }
    }

    public void setPatientMedicalRecord(ArrayList<MedicalRecord> patientMedicalRecord) {
        this.patientMedicalRecord = patientMedicalRecord;
    }

    public void addMedicalRecord(MedicalRecord record) {
        this.patientMedicalRecord.add(record);
        System.out.println("New medical record added for patient " + patientID + ": " + record.getStatus());
    }

    public ArrayList<MedicalRecord> getPatientMedicalRecord() {
        return patientMedicalRecord;
    }
}
