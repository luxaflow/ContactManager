/*
Studentnummer:  800009921
Naam:           Lucas Wolfe
Leerlijn:       Object Oriented Programming
Datum:          19/02/2019
*/

package test;

import model.Company;
import model.Contact;
import org.junit.Test;

import java.util.ArrayList;

public class ContactTest {

    @Test
    public void testUpdate() {

        Company contact = new Company("LuxaFlow");
        contact.get();
        contact.setEmail("test@luxaflow.nl");
        contact.update();

    }

    @Test
    public void testDelete() {

        Company contact = new Company("LuxaFlow");
        contact.get();
        contact.delete();

    }

    @Test
    public void testGetAll() {

        Contact contact = new Contact();

        ArrayList<Contact> contacts = contact.getAll();

        contacts.forEach(c -> System.out.println(c.getName()));

    }

    @Test
    public void testSearch() {

        Contact contact = new Contact();

        ArrayList<Contact> contacts = contact.search("S");

        contacts.forEach(c -> System.out.println(c.getName()+ " " + c.getEmail()));

    }
}