/*
Studentnummer:  800009921
Naam:           Lucas Wolfe
Leerlijn:       Object Oriented Programming
Datum:          19/02/2019
*/

package test;

import model.User;
import org.junit.Test;

public class UserTest {

    @Test
    public void testCreate() {

        User user = new User();

        user.setFirstName("Test");
        user.setLastName("Tester");
        user.setEmail("test@tester.test");
        user.setPhone("0362345678");
        user.setMobile("0612345678");

        user.setUsername("test.tester");

        user.create();

    }

    @Test
    public void testUpdate() {

        User user = new User();

        user.setUsername("test.tester");

        user.get();

        System.out.println(user.getEmail());
        System.out.println(user.getFirstName());

    }

    @Test
    public void testDelete() {

        User user = new User();

        user.setUsername("test.tester");

        user.get();

        user.delete();

    }

    @Test
    public void testGet() {

        User user  = new User();

        user.setUsername("test.tester");

        user.get();

    }
}