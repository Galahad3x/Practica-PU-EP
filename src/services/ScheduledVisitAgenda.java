package services;

import data.*;

import java.util.Queue;

public class ScheduledVisitAgenda {

    private final Queue<Visit> visits;

    public ScheduledVisitAgenda(Queue<Visit> visits) {
        this.visits = visits;
    }

    public void addVisit(Visit visit){
        this.visits.add(visit);
    }

    public HealthCardID getHealthCardID(){
        return visits.remove().getPatientID();
    }
}
