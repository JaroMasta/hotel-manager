package pl.edu.agh.kis.pz1.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.edu.agh.kis.pz1.map.MyMap;
import pl.edu.agh.kis.pz1.model.Guest;
import pl.edu.agh.kis.pz1.model.Hotel;
import pl.edu.agh.kis.pz1.model.Room;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ListCommandTest {
    private Hotel hotel;
    private ListCommand listCommand;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        // Inicjalizacja hotelu i listCommand przed każdym testem
        hotel = new Hotel(new MyMap<>());
        listCommand = new ListCommand(hotel);
        System.setOut(new PrintStream(outContent));
    }

    @Test
    void testListWhenNoRooms() {
        // Test, gdy hotel nie ma żadnych pokoi
        listCommand.execute();
        String output = outContent.toString();
        assertTrue(output.contains("Brak pokoi w hotelu."));
    }

    @Test
    void testListWhenRoomIsFree() {
        // Dodanie pokoju, który jest wolny
        Room freeRoom = new Room(101, 2, new BigDecimal("200.00"));
        hotel.addRoom(101, freeRoom);

        listCommand.execute();
        String output = outContent.toString();

        assertTrue(output.contains("Pokój numer: 101"));
        assertTrue(output.contains("Pojemność: 2"));
        assertTrue(output.contains("Cena za dobę: 200.00 PLN"));
        assertTrue(output.contains("Status: Wolny"));
    }

    @Test
    void testListWhenRoomIsOccupied() {
        // Dodanie pokoju, który jest zajęty
        Room occupiedRoom = new Room(102, 3, new BigDecimal("300.00"));
        Guest mainGuest = new Guest("Jan", "Kowalski", true);
        ArrayList<Guest> additionalGuests = new ArrayList<>();
        additionalGuests.add(new Guest("Anna", "Nowak", false));

        occupiedRoom.setMainGuest(mainGuest);
        occupiedRoom.setCheckInDate(LocalDateTime.now().minusDays(2));
        occupiedRoom.setStayDuration(3);
        occupiedRoom.setAdditionalGuests(additionalGuests);

        hotel.addRoom(102, occupiedRoom);

        listCommand.execute();
        String output = outContent.toString();

        assertTrue(output.contains("Pokój numer: 102"));
        assertTrue(output.contains("Pojemność: 3"));
        assertTrue(output.contains("Cena za dobę: 300.00 PLN"));
        assertTrue(output.contains("Status: Zajęty"));
        assertTrue(output.contains("Główny gość: Jan Kowalski"));
        assertTrue(output.contains("Dodatkowi goście:"));
        assertTrue(output.contains("Anna Nowak"));
    }
}
