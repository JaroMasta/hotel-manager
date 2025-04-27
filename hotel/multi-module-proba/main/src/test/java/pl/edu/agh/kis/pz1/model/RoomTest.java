package pl.edu.agh.kis.pz1.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testy jednostkowe dla klasy Room
 */
public class RoomTest {
    private Room room;
    private Guest mainGuest;

    @BeforeEach
    void setUp() {
        // Przygotowanie testowych obiektów przed każdym testem
        mainGuest = new Guest("Jan", "Kowalski", true);
        room = new Room(101, 2, BigDecimal.valueOf(150.0));
    }

    @Test
    void testRoomCreation() {
        // Sprawdzamy poprawność inicjalizacji obiektu Room
        assertEquals(101, room.getNumber());
        assertEquals(2, room.getCapacity());
        assertEquals(BigDecimal.valueOf(150.0), room.getPricePerNight());
        assertFalse(room.hasMainGuest()); // Pokój powinien być początkowo pusty
    }

    @Test
    void testRoomCreationWithGuests() {
        // Testujemy drugi konstruktor z listą dodatkowych gości
        List<Guest> additionalGuests = new ArrayList<>();
        additionalGuests.add(new Guest("Anna", "Nowak", false));
        additionalGuests.add(new Guest("Piotr", "Zieliński", false));

        Room roomWithGuests = new Room(202, 4, BigDecimal.valueOf(200.0));
        roomWithGuests.setAdditionalGuests(additionalGuests);
        assertEquals(202, roomWithGuests.getNumber());
        assertEquals(4, roomWithGuests.getCapacity());
        assertEquals(2, roomWithGuests.getAdditionalGuests().size());
    }

    @Test
    void testHasMainGuest() {
        // Sprawdzamy czy metoda isOccupied działa poprawnie
        assertFalse(room.hasMainGuest()); // Początkowo pokój jest pusty

        // Zameldowanie głównego gościa
        room.setMainGuest(mainGuest);
        assertTrue(room.hasMainGuest()); // Teraz pokój powinien być zajęty
    }

    @Test
    void testGetFloor() {
        // Sprawdzamy, czy metoda getFloor zwraca poprawny numer piętra
        assertEquals(1, room.getFloor()); // Pokój 101 jest na 1 piętrze

        Room roomOnSecondFloor = new Room(203, 3, BigDecimal.valueOf(120.0));
        assertEquals(2, roomOnSecondFloor.getFloor()); // Pokój 203 jest na 2 piętrze
    }
}
