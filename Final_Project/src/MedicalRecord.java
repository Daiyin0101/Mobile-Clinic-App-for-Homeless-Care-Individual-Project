package src;

public class MedicalRecord {
    static int visitCounter = 0;
    int visitCount;
    HealthCondition healthCondition;
    int patientID;
    double systolic;
    double diastolic;
    double scoreOfBMI;

    public MedicalRecord(double _systolic, double _diastolic, double _scoreOfBMI) {
        visitCount = visitCounter;
        visitCounter++;

        this.systolic = _systolic;
        this.diastolic = _diastolic;
        this.scoreOfBMI = _scoreOfBMI;
    }

    public MedicalRecord(HealthCondition _healthCondition) {
        this.healthCondition = _healthCondition;
        visitCount = visitCounter;
        visitCounter++;
    }

    public HealthCondition getStatus() {
        return healthCondition;
    }

    public double getSystolic() {
        return systolic;
    }

    public double getDiastolic() {
        return diastolic;
    }

    public double getScoreOfBMI() {
        return scoreOfBMI;
    }
}
