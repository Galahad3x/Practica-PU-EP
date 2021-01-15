package services;

import data.HealthCardID;

public interface ScheduledVisitAgenda {

    public void addVisit(String personalID);

    public HealthCardID getHealthCardID();
}
