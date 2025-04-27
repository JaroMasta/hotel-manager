package pl.edu.agh.kis.pz1.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import pl.edu.agh.kis.pz1.map.MyMap;
import pl.edu.agh.kis.pz1.model.Guest;
import pl.edu.agh.kis.pz1.model.Hotel;
import pl.edu.agh.kis.pz1.model.Room;

import java.io.ByteArrayInputStream;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
public class CheckCommandTest {
    private TestCheckCommand testCheckCommand;
    private Hotel hotel;
    // Klasa pomocnicza dziedzicząca po CheckCommand, aby umożliwić testowanie metod
    static class TestCheckCommand extends CheckCommand {
        public TestCheckCommand(Hotel hotel) {
            super(hotel);
        }

        @Override
        public void execute() {
            // Implementacja wymagana przez klasę abstrakcyjną
        }
    }
    @BeforeEach
    void setUp() {
        // Inicjalizacja obiektu hotelu z przykładowymi danymi

        Room room1 = new Room(101, 2, new BigDecimal("150.0"));
        Room room2 = new Room(102, 3, new BigDecimal("200.0"));
        MyMap<Integer,Room> rooms = new MyMap<>();
        rooms.put(101, room1);
        rooms.put(102, room2);
        hotel = new Hotel(rooms);
        testCheckCommand = new TestCheckCommand(hotel);
    }

    /**
     * Test sprawdzający poprawną walidację numeru pokoju.
     */
    @Test
    void testValidateRoomNumber_ValidInput() {
        // Symulacja wejścia użytkownika: podanie poprawnego numeru pokoju
        String input = "102\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Scanner scanner = new Scanner(System.in);
        Integer roomNumber = testCheckCommand.validateRoomNumber(scanner);
        assertEquals(102, roomNumber, "Numer pokoju powinien być poprawny.");
    }

    /**
     * Test sprawdzający walidację numeru pokoju, gdy podano niepoprawny format.
     */
    @Test
    void testValidateRoomNumber_InvalidFormat() {
        // Symulacja wejścia użytkownika: podanie niepoprawnego numeru pokoju (tekst)
        String input = "abc\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Integer roomNumber = testCheckCommand.validateRoomNumber();
        assertEquals(-1, roomNumber, "Numer pokoju powinien być niepoprawny.");
    }

    /**
     * Test sprawdzający walidację numeru pokoju, gdy podano liczbę ujemną.
     */
    @Test
    void testValidateRoomNumber_NegativeNumber() {
        // Symulacja wejścia użytkownika: podanie ujemnego numeru pokoju
        String input = "-5\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Integer roomNumber = testCheckCommand.validateRoomNumber();
        assertEquals(-1, roomNumber, "Numer pokoju nie może być ujemny.");
    }

    /**
     * Test sprawdzający poprawną walidację dostępności pokoju.
     */
    @Test
    void testValidateRoom_AvailableRoom() {
        Room room = hotel.getRoom(101);
        boolean result = testCheckCommand.validateRoom(room);
        assertTrue(result, "Pokój powinien być dostępny.");
    }

    /**
     * Test sprawdzający walidację pokoju, gdy pokój jest już zajęty.
     */
    @Test
    void testValidateRoom_OccupiedRoom() {
        Room room = hotel.getRoom(101);
        room.setCheckInDate(LocalDateTime.now());
        room.setMainGuest(new Guest("Jan", "Kowalski", true));
        boolean result = testCheckCommand.validateRoom(room);
        assertFalse(result, "Pokój nie powinien być dostępny, ponieważ jest zajęty.");
    }

    /**
     * Test sprawdzający walidację pokoju, gdy są już zameldowani dodatkowi goście.
     */
    @Test
    void testValidateRoom_WithAdditionalGuests() {
        Room room = hotel.getRoom(102);
        ArrayList<Guest> additionalGuests = new ArrayList<>();
        additionalGuests.add(new Guest("Anna", "Nowak", false));
        room.setAdditionalGuests(additionalGuests);

        boolean result = testCheckCommand.validateRoom(room);
        assertFalse(result, "Pokój nie powinien być dostępny, ponieważ ma dodatkowych gości.");
    }
}
