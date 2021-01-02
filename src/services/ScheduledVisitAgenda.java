package services;

import data.*;

import java.util.Queue;

public interface ScheduledVisitAgenda {

    public void addVisit(String personalID);

    public HealthCardID getHealthCardID();
}
