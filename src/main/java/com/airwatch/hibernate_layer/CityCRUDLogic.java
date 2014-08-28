package com.airwatch.hibernate_layer;

import com.airwatch.database_classes.City;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CityCRUDLogic {
    private static SessionFactory factory;
    int a;

    public CityCRUDLogic() {
        try {
            factory = new AnnotationConfiguration().configure().addPackage("com.airwatch.database_classes").addAnnotatedClass(City.class).buildSessionFactory();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Integer addCity(String name, String countryCode, String district, long population) {
        Session session = factory.openSession();
        Transaction transaction = null;
        Integer cityID = null;
        try {
            transaction = session.beginTransaction();
            City city = new City();
            city.setCityName(name);
            city.setCountryCode(countryCode);
            city.setDistrict(district);
            city.setPopulation(population);
            cityID = (Integer) session.save(city);
            transaction.commit();
            if (transaction.wasCommitted()) {
                System.out.println("Added city to database");
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
                System.out.println("Rolled back");
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return cityID;
    }

    public void listCities(String name) {
        Session session = factory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            List citiesList = session.createQuery("FROM City WHERE district='" + name + "'").list();
            List<City> cities = new ArrayList();
            for (Iterator iter = citiesList.iterator(); iter.hasNext(); ) {
                cities.add((City) iter.next());
            }
            for (City city : cities) {
                System.out.println(city.toString() + "\n=======");
            }
            transaction.commit();
            if (transaction.wasCommitted()) {
                System.out.println("All cities printed successfully");
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
                System.out.println("Rolled back");
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void updateCity(String name, long population) {
        Session session = factory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            City city = (City) session.createQuery("FROM City WHERE cityName ='" + name + "'").list().get(0);
            System.out.println(city);
            city.setPopulation(population);
            session.update(city);
            transaction.commit();
            if (transaction.wasCommitted()) {
                System.out.println("Updated city successfully");
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
                System.out.println("Rolled back");
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void deleteCity(Integer id) {
        Session session = factory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            City city = (City) session.get(City.class, id);
            session.delete(city);
            transaction.commit();
            if (transaction.wasCommitted()) {
                System.out.println("Deleted city successfully");
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
                System.out.println("Rolled back");
            }
            e.printStackTrace();
        }
    }
}
