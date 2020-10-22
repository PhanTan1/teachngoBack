package be.teachngo.repository;

import be.teachngo.data.Address;
import be.teachngo.data.Administrator;
import be.teachngo.data.Country;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest()
@ActiveProfiles("test")
public class AdministratorRepositoryIntegrationTest {

    @Autowired
    private AdministratorRepository administratorRepository;

    @Test
    public void whenFindByLogin_thenReturnAdministrator() {
        // given
        Administrator admin = new Administrator();
        admin.setEmail("admin@teachngo.com");
        admin.setFirstName("Admin");
        admin.setLastName("_");
        admin.setGender("NP");
        admin.setPassword("Password");
        admin.setLogin("admin");
        admin.setPhone("+32485055204");

        Address address = new Address();
        address.setBoxNumber("12");
        address.setCountry(Country.BELGIUM);
        address.setPostalCode("1070");
        address.setStreet("Borgtstraat");
        address.setStreetNumber("12A");
        admin.setAdress(address);
        administratorRepository.save(admin);

        // when
        Administrator found = administratorRepository.findByLogin(admin.getLogin());

        // then
        assertEquals(found.getLogin(),admin.getLogin());

        assertEquals(found.getAdress().getStreet(), admin.getAdress().getStreet());

        admin = found;
        admin.getAdress().setStreet("New Street");

        administratorRepository.save(admin);
        found = administratorRepository.findByLogin(admin.getLogin());
        assertEquals(found.getAdress().getStreet(), admin.getAdress().getStreet());
    }

}
