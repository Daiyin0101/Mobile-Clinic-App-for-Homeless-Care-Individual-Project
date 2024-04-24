package run;

import src.Clinic;
import src.Location;

public class Runnable {

    public static void main(String[] args) {
        // Config our Clinic
        String clinicName = "JellyCat Clinic";
        Location clinicLocation = Location.BELLEVUE;
        Clinic a = new Clinic(clinicName, 20, clinicLocation);

        System.out.println("----------------------------------------------");
        System.out.println("Normal body check result:");
        double systolic = 110.9;
        double diastolic = 79.3;
        double scoreOfBMI = 18.2;
        a.normalBodyCheck(2, systolic, diastolic, scoreOfBMI);

        System.out.println("----------------------------------------------");
        System.out.println("Get the patient's current location:");
        a.getPatientCurrentLocation(20);

        System.out.println("----------------------------------------------");
        System.out.println("Update the patient's current location:");
        a.updatePatientCurrentLocation(clinicLocation, 9);

        System.out.println("----------------------------------------------");
        System.out.println("Diagnose the patient's health status and update new medical record:");
        a.diagnosePatientHealthStatus(5);
        a.diagnosePatientHealthStatus(9);

        System.out.println("----------------------------------------------");
        System.out.println("Find sick patients and get the location:");
        a.findSickPatient(7);

        System.out.println("----------------------------------------------");
        System.out.println("Check if the patient will infect others at the location:");
        a.checkIfPatientWillInfectOthersAtTheLocation(5);

        System.out.println("----------------------------------------------");
        System.out.println("Print the number of infected incidents in all locations from largest to smallest:");
        a.getInfectedIncidentsInAllLocationsReport();

        System.out.println("----------------------------------------------");
        System.out.println("Add local mental service:");
        String localMentalServiceName = "Bellevue Community Mental Health";
        Location localMentalServiceLocation = Location.BELLEVUE;
        a.addMentalService(localMentalServiceLocation, localMentalServiceName);

        System.out.println("----------------------------------------------");
        System.out.println("Get local mental service:");
        a.getLocalMentalService(3);
    }
}