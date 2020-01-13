/*
Studentnummer:  800009921
Naam:           Lucas Wolfe
Leerlijn:       Object Oriented Programming
Datum:          19/02/2019
*/

package test;

import org.junit.Test;
import model.Company;

public class CompanyTest {

    @Test
    public void testCreate() {

        Company company = new Company("LuxaFlow");
        company.setEmail("info@luxaflow.nl");
        company.setMobile("0612345678");
        company.setPhone("+310123456789");

        company.create();
    }

    @Test
    public void testGet() {

        Company company = new Company("LuxaFlow");
        company.get();
        company.getEmail().equals("info@luxaflow.nl");
    }
}