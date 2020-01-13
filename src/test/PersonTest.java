/*
Studentnummer:  800009921
Naam:           Lucas Wolfe
Leerlijn:       Object Oriented Programming
Datum:          19/02/2019
*/

package test;

import model.Person;
import org.junit.Test;


public class PersonTest {

    @Test
    public void testCreate() {

        Person person = new Person("Test", "Tester");

        person.setEmail("testing@tests.com");
        person.setPhone("0307654321");
        person.setMobile("0687654321");

        person.create();

    }

    @Test
    public void testGet() {

        Person person = new Person("Test", "Tester");

        person.get();


    }
}