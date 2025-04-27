package pl.edu.agh.kis.pz1.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GuestInitializerTest {
    private List<Guest> mainGuests;
    private List<Guest> additionalGuests;

    @BeforeEach
    void setUp() {
        mainGuests = GuestInitializer.createMainGuests();
        additionalGuests = GuestInitializer.createAdditionalGuests();
    }
    @Test
    void testMainGuestsHaveValidNames() {
        // Sprawdzenie, czy główni goście mają poprawne imiona i nazwiska
        for (Guest guest : mainGuests) {
            assertNotNull(guest.getName(), "Imię gościa nie powinno być null");
            assertNotNull(guest.getSurname(), "Nazwisko gościa nie powinno być null");
            assertFalse(guest.getName().isEmpty(), "Imię gościa nie powinno być puste");
            assertFalse(guest.getSurname().isEmpty(), "Nazwisko gościa nie powinno być puste");
        }
    }
    @Test
    void testAdditionalGuestsHaveValidNames() {
        // Sprawdzenie, czy dodatkowi goście mają poprawne imiona i nazwiska
        for (Guest guest : additionalGuests) {
            assertNotNull(guest.getName(), "Imię gościa nie powinno być null");
            assertNotNull(guest.getSurname(), "Nazwisko gościa nie powinno być null");
            assertFalse(guest.getName().isEmpty(), "Imię gościa nie powinno być puste");
            assertFalse(guest.getSurname().isEmpty(), "Nazwisko gościa nie powinno być puste");
        }
    }

    @Test
    void testMainGuestsAreUnique() {
        // Sprawdzenie, czy główni goście są unikalni
        assertEquals(mainGuests.stream().map(Guest::getFullName).distinct().count(),
                mainGuests.size(),
                "Lista głównych gości powinna zawierać unikalne imiona i nazwiska");
    }

    @Test
    void testAdditionalGuestsAreUnique() {
        // Sprawdzenie, czy dodatkowi goście są unikalni
        assertEquals(additionalGuests.stream().map(Guest::getFullName).distinct().count(),
                additionalGuests.size(),
                "Lista dodatkowych gości powinna zawierać unikalne imiona i nazwiska");
    }
}