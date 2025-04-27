package pl.edu.agh.kis.pz1.commands;

import org.junit.jupiter.api.BeforeEach;
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

public class CheckoutCommandTest {
    private CheckoutCommand checkoutCommand;
    private Hotel hotel;
    private Room occupiedRoom;

    @BeforeEach
    void setUp() {

        occupiedRoom = new Room(101, 2, new BigDecimal("150.0"));
        occupiedRoom.setCheckInDate(LocalDateTime.now().minusDays(3));
        occupiedRoom.setMainGuest(new Guest("Jan", "Kowalski", true));
        MyMap<Integer, Room> rooms = new MyMap<>();
        rooms.put(101, occupiedRoom);
        hotel = new Hotel(rooms);

    }

    /**
     * Test sprawdzający poprawne wymeldowanie gościa z pokoju.
     */
    @Test
    void testCheckOut_SuccessfulCheckout() {
        // Symulacja wejścia użytkownika: podanie numeru zajętego pokoju
        String input = "101\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Scanner scanner = new Scanner(System.in);
        checkoutCommand = new CheckoutCommand(hotel, scanner);
        boolean result = checkoutCommand.checkOut();
        assertTrue(result, "Wymeldowanie powinno zakończyć się sukcesem.");

        // Sprawdzenie, czy dane pokoju zostały zresetowane po wymeldowaniu
        assertNull(occupiedRoom.getMainGuest(), "Główny gość powinien być ustawiony na null.");
        assertNull(occupiedRoom.getCheckInDate(), "Data zameldowania powinna być ustawiona na null.");
        assertEquals(0, occupiedRoom.getStayDuration(), "Długość pobytu powinna być 0.");
        assertTrue(occupiedRoom.getAdditionalGuests().isEmpty(), "Lista dodatkowych gości powinna być null.");
    }

    /**
     * Test sprawdzający obliczanie opłaty za pobyt podczas wymeldowania.
     */
    @Test
    void testCheckOut_CalculateTotalPrice() {
        // Symulacja wejścia użytkownika: podanie numeru zajętego pokoju
        String input = "101\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Scanner scanner = new Scanner(System.in);
        checkoutCommand = new CheckoutCommand(hotel, scanner);
        // Cena za nocleg i ilość dni
        BigDecimal expectedPrice = new BigDecimal("150.0").multiply(new BigDecimal(4)); // 3 dni + 1 dodatkowy dzień

        checkoutCommand.checkOut();

        // Sprawdzenie, czy cena za pobyt została poprawnie obliczona
        BigDecimal actualPrice = occupiedRoom.getPricePerNight().multiply(new BigDecimal(4));
        assertEquals(expectedPrice, actualPrice, "Cena powinna być poprawnie obliczona na podstawie ilości dni.");
    }

    /**
     * Test sprawdzający, czy wymeldowanie nie powiedzie się, jeśli pokój jest wolny.
     */
    @Test
    void testCheckOut_FailedCheckoutIfRoomIsEmpty() {
        // Ustawienie pokoju jako wolnego
        occupiedRoom.setCheckInDate(null);
        occupiedRoom.setMainGuest(null);

        // Symulacja wejścia użytkownika: podanie numeru pustego pokoju
        String input = "101\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Scanner scanner = new Scanner(System.in);
        checkoutCommand = new CheckoutCommand(hotel, scanner);
        boolean result = checkoutCommand.checkOut();
        assertFalse(result, "Wymeldowanie powinno zakończyć się niepowodzeniem, jeśli pokój jest wolny.");
    }

    /**
     * Test sprawdzający poprawną walidację pokoju z zajętym miejscem.
     */
    @Test
    void testValidateRoom_OccupiedRoom() {
        Scanner scanner = new Scanner(System.in);
        checkoutCommand = new CheckoutCommand(hotel, scanner);
        boolean result = checkoutCommand.validateRoom(occupiedRoom);
        assertTrue(result, "Pokój powinien być zajęty.");
    }

    /**
     * Test sprawdzający walidację pokoju, który jest wolny.
     */
    @Test
    void testValidateRoom_EmptyRoom() {
        // Ustawienie pokoju jako wolnego
        occupiedRoom.setCheckInDate(null);
        occupiedRoom.setMainGuest(null);
        Scanner scanner = new Scanner(System.in);
        checkoutCommand = new CheckoutCommand(hotel, scanner);
        boolean result = checkoutCommand.validateRoom(occupiedRoom);
        assertFalse(result, "Walidacja powinna zwrócić false dla wolnego pokoju.");
    }

}
