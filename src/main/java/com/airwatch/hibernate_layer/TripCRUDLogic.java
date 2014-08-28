package com.airwatch.hibernate_layer;

import com.airwatch.database_classes.Trip;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TripCRUDLogic {
    private static SessionFactory factory;

    public TripCRUDLogic() {
        try {
            factory = new AnnotationConfiguration().configure().addPackage("com.airwatch.database_classes").addAnnotatedClass(Trip.class).buildSessionFactory();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Integer addTrip(String bookerName, String source, String destination, String startDate, String returnDate, int passengers) {
        Session session = factory.openSession();
        Transaction transaction = null;
        Integer tripID = null;
        try {
            transaction = session.beginTransaction();
            Trip trip = new Trip();

            trip.setBookerName(bookerName);
            trip.setDestination(destination);
            trip.setNumPassengers(passengers);
            trip.setReturnDate(returnDate);
            trip.setSource(source);
            trip.setStartDate(startDate);

            tripID = (Integer) session.save(trip);
            transaction.commit();
            if (transaction.wasCommitted()) {
                System.out.println("Trip added! :)");
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
                System.out.println("Rolled back :(");
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return tripID;
    }

    public void listTrips() {
        Session session = factory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            List tripsList = session.createQuery("FROM Trip").list();
            List<Trip> trips = new ArrayList();
            for (Iterator iter = tripsList.iterator(); iter.hasNext(); ) {
                trips.add((Trip) iter.next());
            }
            for (Trip trip : trips) {
                System.out.println(trip.toString());
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
                System.out.println("Rolled back :(");
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void updateTrip(String name, String date, int passengers) {
        Session session = factory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Trip trip = (Trip) session.createQuery("FROM Trip WHERE bookerName = '" + name + "' AND startDate = '" + date + "'").list().get(0);
            trip.setNumPassengers(passengers);
            session.update(trip);
            transaction.commit();
            if (transaction.wasCommitted()) {
                System.out.println("Updated trip :)");
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
                System.out.println("Rolled back :(");
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void deleteTrip(String name, String date) {
        Session session = factory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Trip trip = (Trip) session.createQuery("FROM Trip WHERE bookerName = '" + name + "' AND startDate = '" + date + "'").list().get(0);
            session.delete(trip);
            transaction.commit();
            if (transaction.wasCommitted()) {
                System.out.println(name + "'s trip cancelled.");
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
                System.out.println("Rolled back :(");
            }
            e.printStackTrace();
        }
    }
}
