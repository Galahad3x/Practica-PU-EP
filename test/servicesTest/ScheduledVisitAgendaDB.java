package servicesTest;

import data.HealthCardID;
import exceptions.NullArgumentException;
import exceptions.WrongFormatException;
import services.ScheduledVisitAgenda;

import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

public class ScheduledVisitAgendaDB implements ScheduledVisitAgenda {

    private final Queue<HealthCardID> visits;

    public ScheduledVisitAgendaDB(){
        visits = new LinkedList<>();
    }

    @Override
    public void addVisit(String personalID) throws WrongFormatException, NullArgumentException {
        visits.add(new HealthCardID(personalID));
    }

    @Override
    public HealthCardID getHealthCardID() throws NoSuchElementException {
        if(visits.size() == 0){
            throw new NoSuchElementException();
        }
        return visits.poll();
    }
}
