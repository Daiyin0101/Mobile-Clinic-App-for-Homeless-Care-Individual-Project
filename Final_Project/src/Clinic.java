package src;

import java.util.ArrayList;
import java.util.Random;
import java.util.Collections;

public class Clinic {
    String name;
    ArrayList<Patient> patientsList;
    ArrayList<LocalMentalService> localMentalServicesList;
    ArrayList<InfectedIncidentsRecord> infectedList;
    Location location;

    public Clinic(String _name, int patientCount, Location _location) {
        this.name = _name;
        patientsList = new ArrayList<Patient>();
        localMentalServicesList = new ArrayList<LocalMentalService>();
        infectedList = new ArrayList<InfectedIncidentsRecord>();
        this.location = _location;
        loadPatients(patientCount);
    }

    // Add Patients
    public void loadPatients(int patientCount) {
        for (int index = 1; index <= patientCount; index++) {
            Patient newPatient = new Patient(location);
            patientsList.add(newPatient);
        }
    }

    // Normal body check
    public void normalBodyCheck(int _patientID, double _systolic, double _diastolic, double _scoreOfBMI) {
        for (Patient cur : patientsList) {
            if (cur.getPatientID() == _patientID) {
                // Blood pressure readings include systolic and diastolic pressures. Systolic
                // pressure measures the force of blood against artery walls during heartbeats,
                // while diastolic measures it when the heart rests.
                // The WHO defines a normal BMI as between 18.5 and 24.9
                // and normal blood pressure is a systolic reading under 120 mmHg and a
                // diastolic reading under 80 mmHg.
                if (_systolic < 120 && _diastolic < 80 && _scoreOfBMI < 24.9 && 18.5 < _scoreOfBMI) {
                    System.out.println("The patient " + cur.getPatientID() + " 's medical record looks normal: "
                            + "Systolic: " + _systolic + " mmHg, Diastolic: " + _diastolic + " mmHg, BMI: "
                            + _scoreOfBMI + ".");
                } else {
                    System.out.println("The patient " + cur.getPatientID()
                            + " 's medical record looks abnormal, needs more nutrition.");
                }
            }
        }
    }

    // Get the patient's current location
    public void getPatientCurrentLocation(int _patientID) {
        for (Patient cur : patientsList) {
            if (cur.getPatientID() == _patientID) {
                cur.getPatientCurrentLocation();
                System.out.println("The patient " + cur.getPatientID() + "'s currently location is: "
                        + cur.getPatientCurrentLocation().toString());
                return;
            }
        }
        System.out.println("We can not find the patient in our system.");
    }

    // Update the patient's current location
    public void updatePatientCurrentLocation(Location location, int patientID) {
        for (Patient cur : patientsList) {
            if (cur.getPatientID() == patientID) {
                cur.setPatientCurrentLocation(location);
                System.out.println(
                        "Successfully update the patient's currently location at: "
                                + cur.getPatientCurrentLocation().toString());
                return;
            }
        }
        System.out.println("We can not find the patient in our system.");
    }

    public static int getRandom(int min, int max) {
        Random random = new Random();
        return random.nextInt(max) + min;
    }

    // Diagnose the patient's health status
    public void diagnosePatientHealthStatus(int _patientID) {
        for (Patient cur : patientsList) {
            // Step 1: Find the patient by patientID
            if (cur.getPatientID() == _patientID) {
                MedicalRecord newHealthStatus;

                // Step 2: Generate integer 0-3. 0,1 represent healthy; 2 represents
                // sick; 3 represents sick with infection.
                if (getRandom(0, 4) <= 1) {
                    newHealthStatus = new MedicalRecord(HealthCondition.HEALTHY);
                    System.out.println("The patient " + cur.getPatientID() + " is healthy and the patient location at: "
                            + cur.getPatientCurrentLocation().toString());
                } else if (getRandom(0, 4) == 2) {
                    newHealthStatus = new MedicalRecord(HealthCondition.SICK);
                    System.out.println("The patient " + cur.getPatientID() + " is sick and the patient location at: "
                            + cur.getPatientCurrentLocation().toString());
                } else {
                    newHealthStatus = new MedicalRecord(HealthCondition.SICKWITHINFECTION);
                    System.out.println("The patient " + cur.getPatientID()
                            + " is sick with infection and the patient location at: "
                            + cur.getPatientCurrentLocation().toString());
                }

                // Step 3: Update patient with a new medical record and add it to patient
                // medical record list
                cur.addMedicalRecord(newHealthStatus);
                return;
            }
        }
        System.out.println("We can not find the patient in the system.");
    }

    // Find sick patient and get the location
    public void findSickPatient(int _patientID) {
        for (Patient cur : patientsList) {
            if (cur.getPatientID() == _patientID) {
                if (getRandom(0, 4) >= 2) {
                    System.out.println("The patient " + cur.getPatientID() + " is sick and the patient location at: "
                            + cur.getPatientCurrentLocation().toString());
                } else {
                    System.out.println("The patient " + cur.getPatientID() + " is healthy and the patient location at: "
                            + cur.getPatientCurrentLocation().toString());
                }
            }
        }
    }

    // Check if the patient will infect others at the location
    public void checkIfPatientWillInfectOthersAtTheLocation(int _patientID) {
        Location target = null;
        for (Patient cur : patientsList) {
            // Step 1: Check if the patient is sick with infection and get the location
            if (cur.getPatientID() == _patientID) {

                if (cur.getLatestPatientMedicalRecord() == null) {
                    System.out.println("Can not use check if infected function.");
                    return;
                }

                if (cur.getLatestPatientMedicalRecord().healthCondition == HealthCondition.SICKWITHINFECTION) {
                    target = cur.getPatientCurrentLocation();
                    break;
                } else {
                    System.out.println("The patient " + cur.getPatientID() + " wll not infect others at: "
                            + cur.getPatientCurrentLocation() + ".");
                    return;
                }
            }
        }

        if (target == null) {
            System.out.println("We can not find the patient in our system.");
            return;
        }

        // Step 2: Find if there is other patients that are not sick with infection at
        // target location
        for (Patient cur : patientsList) {

            if (cur.getLatestPatientMedicalRecord() == null) {
                System.out.println("Can not use check if infected function.");
                return;
            }

            if ((cur.getLatestPatientMedicalRecord().healthCondition == HealthCondition.SICK
                    || cur.getLatestPatientMedicalRecord().healthCondition == HealthCondition.HEALTHY)
                    && target == cur.getPatientCurrentLocation()) {
                System.out.println("Others wll be infected by the patient who is sick with infection at: "
                        + cur.getPatientCurrentLocation() + ".");
                return;
            }
        }
        System.out.println("No risk, the patient who is sick with infection can not infect others anymore.");
    }

    // Print the number of infected incidents in all locations
    public void getInfectedIncidentsInAllLocationsReport() {
        ArrayList<InfectedIncidentsRecord> infectedList = new ArrayList<InfectedIncidentsRecord>();
        infectedList.add(new InfectedIncidentsRecord(Location.BELLEVUE));
        infectedList.add(new InfectedIncidentsRecord(Location.REDMOND));
        infectedList.add(new InfectedIncidentsRecord(Location.KIRKLAND));
        infectedList.add(new InfectedIncidentsRecord(Location.SEATTLE));

        // Step 1: Find the number of infected incidents(which patient is sick with
        // infection) in all locations
        for (Patient cur : patientsList) {
            if (cur.getLatestPatientMedicalRecord().healthCondition == HealthCondition.SICKWITHINFECTION) {
                for (int i = 0; i < infectedList.size(); i++) {
                    InfectedIncidentsRecord record = infectedList.get(i);
                    if (record.getInfectedIncidentsLocation() == cur.getPatientCurrentLocation()) {
                        record.incrementInfectedIncidentscount();
                    }
                }
            }
        }

        // Step 2 : Sort the number of locations from largest to smallest.
        Collections.sort(infectedList);
        for (InfectedIncidentsRecord cur : infectedList) {
            System.out.println(cur.getInfectedIncidentsLocation().toString() + " : " + cur.getInfectedIncidentscount());
        }
    }

    // Get local mental service
    public void getLocalMentalService(int _patientId) {
        Patient target = null;
        for (Patient cur : patientsList) {
            if (cur.getPatientID() == _patientId) {
                target = cur;
            }
        }

        if (target == null) {
            System.out.println("We can not find the patient in our system.");
        }

        for (LocalMentalService cur : localMentalServicesList) {
            if (cur.getLocalMentalServiceLocation() == target.getPatientCurrentLocation()) {
                System.out.println(
                        "You can get local mental service at: " + cur.getName());
                return;
            }
        }
        System.out.println("We can not find the local mental service in our system.");
    }

    // Add local mental service
    public void addMentalService(Location _location, String _name) {
        for (LocalMentalService cur : localMentalServicesList) {
            if (cur.getName() == _name) {
                System.out
                        .println("The local mental health center is already in the system. We can not add it anymore.");
                return;
            }
        }

        System.out.println("We add the new local mental health center in the system now.");
        localMentalServicesList.add(new LocalMentalService(_location, _name));
    }
}