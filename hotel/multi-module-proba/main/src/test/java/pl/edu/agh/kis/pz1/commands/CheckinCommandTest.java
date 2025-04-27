package pl.edu.agh.kis.pz1.commands;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.edu.agh.kis.pz1.model.Guest;
import pl.edu.agh.kis.pz1.model.Hotel;
import pl.edu.agh.kis.pz1.model.Room;
import pl.edu.agh.kis.pz1.map.MyMap;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;

import static org.mockito.Mockito.mock;
import static org.junit.jupiter.api.Assertions.*;

class CheckinCommandTest {
    private Hotel hotel;
    private CheckinCommand checkinCommand;
    private ByteArrayOutputStream outputStream;
    private final InputStream originalSystemIn = System.in;
    private final PrintStream originalSystemOut = System.out;

    @AfterEach
    void tearDown() {
        System.setIn(originalSystemIn);
        System.setOut(originalSystemOut);
    }
    @BeforeEach
    void setUp() {

        MyMap<Integer, Room> rooms = new MyMap<>();
        Room room101 = new Room(101, 2, new BigDecimal("150.50"));
        Room room102 = new Room(102, 3, new BigDecimal("200.00"));
        rooms.put(101, room101);
        rooms.put(102, room102);
        hotel = new Hotel(rooms);


        // Ustawienie przechwytywania wyjścia na konsolę
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    void testCheckInWithValidRoom() {
        Guest mainGuest = new Guest("Jan", "Kowalski", true);
        ArrayList<Guest> additionalGuests = new ArrayList<>();
        additionalGuests.add(new Guest("Anna", "Nowak", false));


        // Symulacja wejścia użytkownika: podanie numeru pokoju 102
        String input = "102\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Scanner scanner = new Scanner(System.in);
        checkinCommand = new CheckinCommand(hotel, scanner);

        boolean result = checkinCommand.checkIn(LocalDateTime.now(), mainGuest, additionalGuests, 3, 102);
        assertTrue(result, "Zameldowanie powinno zakończyć się sukcesem.");
        Room room = hotel.getRoom(102);
        assertNotNull(room.getMainGuest(), "Główny gość powinien być ustawiony.");
        assertEquals(1, room.getAdditionalGuests().size(), "Powinna być jedna dodatkowa osoba.");
    }

    @Test
    void testCheckInWithInvalidRoomNumber() {
        // Symulacja wejścia użytkownika: podanie numeru pokoju -5
        String input = "-5\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Scanner scanner = new Scanner(System.in);
        checkinCommand = new CheckinCommand(hotel, scanner);

        assertEquals(checkinCommand.validateRoomNumber(), -1,"Numer pokoju jest niepoprawny operacja nie powinna sie powiesc.");
        // Pobranie wyjścia konsoli
        String output = outputStream.toString();
        // Sprawdzenie, czy wyjście zawiera poprawne informacje o tym, że nr pokoju jest niepoprawny
        assertTrue(output.contains("Numer pokoju musi być większy niż 0."), "Brak informacji o tym że pokój jest zajęty.");
    }
    @Test
    void testGetCheckInDate() {

        String input = "2024-11-12 12:00\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Scanner scanner = new Scanner(System.in);
        checkinCommand = new CheckinCommand(hotel, scanner);
        LocalDateTime result = checkinCommand.getCheckInDate();
        LocalDateTime expectedDate = LocalDateTime.parse("2024-11-12 12:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        assertEquals(expectedDate, result);
    }
    @Test
    void testExecuteMethodWithValidValuesAndNowData() {
        // Symulacja wejscia uzytkownika z niepoprawna data
        String input = "102\ninvalid\nJan\nKowalski\nnie\n10\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Scanner scanner = new Scanner(System.in);
        checkinCommand = new CheckinCommand(hotel, scanner);

        checkinCommand.execute();

        String output = outputStream.toString();


        assertTrue(output.contains("Podaj długość pobytu (w dniach):"), "Brak prośby o podanie długości pobytu.");
        assertEquals("Jan Kowalski", hotel.getRoom(102).getMainGuest().getFullName(), "Pełne imię głównego gościa jest niepoprawne");
        assertTrue(hotel.getRoom(102).getAdditionalGuests().isEmpty(), "Pokój nie powinien mieć dodatkowych gości");
        assertTrue(hotel.getRoom(102).hasMainGuest(), "Pokój powinien miec głownego goscia!");
        assertNotNull(hotel.getRoom(102).getCheckInDate());
        assertEquals(hotel.getRoom(102).getCheckInDate().withMinute(0).withSecond(0).withNano(0), LocalDateTime.now().withMinute(0).withSecond(0).withNano(0));
    }


    @Test
    void testCheckValidateRoom() {
        Scanner scanner = new Scanner(System.in);
        checkinCommand = new CheckinCommand(hotel, scanner);
        assertTrue(checkinCommand.validateRoom(hotel.getRoom(102)));
    }
    @Test
    void testCheckValidateRoomOccupiedRoom() {
        Guest mainGuest = new Guest("Jan", "Kowalski", true);
        ArrayList<Guest> additionalGuests = new ArrayList<>();
        additionalGuests.add(new Guest("Anna", "Nowak", false));


        // Symulacja wejścia użytkownika: podanie numeru pokoju 102
        String input = "102\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Scanner scanner = new Scanner(System.in);
        checkinCommand = new CheckinCommand(hotel, scanner);
        checkinCommand.checkIn(LocalDateTime.now(), mainGuest, additionalGuests, 3, 102);
        assertFalse(checkinCommand.validateRoom(hotel.getRoom(102)));
    }


}
