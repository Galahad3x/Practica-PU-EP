package services;

import data.HealthCardID;
import exceptions.NullArgumentException;
import exceptions.WrongFormatException;

import java.util.NoSuchElementException;

public interface ScheduledVisitAgenda {

    void addVisit(String personalID) throws WrongFormatException, NullArgumentException;

    HealthCardID getHealthCardID() throws NoSuchElementException;
}
