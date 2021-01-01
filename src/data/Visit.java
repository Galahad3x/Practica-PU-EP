package data;

import services.ScheduledVisitAgenda;

public class Visit {

    private final HealthCardID patientID;
    private final String time_of_visit;

    public Visit(HealthCardID patientID, String time_of_visit){
        this.patientID = patientID;
        this.time_of_visit = time_of_visit;
    }

    public HealthCardID getPatientID() {
        return patientID;
    }

    public String getTime_of_visit() {
        return time_of_visit;
    }
}
