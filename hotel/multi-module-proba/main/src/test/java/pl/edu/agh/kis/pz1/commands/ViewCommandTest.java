package pl.edu.agh.kis.pz1.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import pl.edu.agh.kis.pz1.map.MyMap;
import pl.edu.agh.kis.pz1.model.Guest;
import pl.edu.agh.kis.pz1.model.Hotel;
import pl.edu.agh.kis.pz1.model.Room;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Klasa testująca dla ViewCommand, która sprawdza poprawność
 * wyświetlania informacji o pokojach na podstawie numeru podanego przez użytkownika.
 */
public class ViewCommandTest {

    private Hotel hotel;
    private ByteArrayOutputStream outputStream;
    CheckinCommand checkinCommand;

    /**
     * Metoda przygotowująca przykładowe dane testowe przed każdym testem.
     * Tworzy instancję Hotel z przykładowymi pokojami i ustawia przechwytywanie wyjścia konsoli.
     */
    @BeforeEach
    public void setUp() {
        // Inicjalizacja obiektu Hotel z przykładowymi pokojami
        MyMap<Integer, Room> rooms = new MyMap<>();
        Room room101 = new Room(101, 2, new BigDecimal("150.50"));
        Room room102 = new Room(102, 3, new BigDecimal("200.00"));

        // Dodanie głównego gościa do pokoju 102 wraz z dodatkowymi gośćmi
        Guest mainGuest = new Guest("Jan", "Kowalski", true);
        List<Guest> additionalGuests = new ArrayList<>();
        additionalGuests.add(new Guest("Anna", "Nowak", false));
        room102.setMainGuest(mainGuest);
        room102.setAdditionalGuests(additionalGuests);
        rooms.put(101, room101);
        rooms.put(102, room102);
        hotel = new Hotel(rooms);
        // Ustawienie przechwytywania wyjścia na konsolę
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

    }

    /**
     * Test sprawdzający, czy ViewCommand poprawnie wyświetla informacje o istniejącym pokoju.
     * Sprawdza, czy wszystkie informacje o pokoju i gościach są poprawnie wyświetlane.
     */
    @Test
    public void testExecute_RoomExists() {
        // Symulacja wejścia użytkownika: podanie numeru pokoju 102
        String input = "102\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Scanner scanner = new Scanner(System.in);
        ViewCommand command = new ViewCommand(hotel, scanner);
        command.execute();

        // Pobranie wyjścia konsoli
        String output = outputStream.toString();

        // Sprawdzenie, czy wyjście zawiera poprawne informacje o pokoju i gościach
        assertTrue(output.contains("Informacje o pokoju:"), "Brak nagłówka z informacjami o pokoju.");
        assertTrue(output.contains("Numer pokoju: 102"), "Niepoprawny numer pokoju.");
        assertTrue(output.contains("Pojemność: 3"), "Niepoprawna pojemność pokoju.");
        assertTrue(output.contains("Cena za dobę: 200.00"), "Niepoprawna cena za dobę.");
        assertTrue(output.contains("Główny gość:"), "Brak informacji o głównym gościu.");
        assertTrue(output.contains("Imię i nazwisko: Jan Kowalski"), "Niepoprawne dane głównego gościa.");
        assertTrue(output.contains("Imię i nazwisko dodatkowego gościa: Anna Nowak"), "Brak danych dodatkowego gościa.");
    }

    /**
     * Test sprawdzający, czy ViewCommand wyświetla odpowiedni komunikat,
     * gdy użytkownik wprowadzi numer nieistniejącego pokoju.
     */
    @Test
    public void testExecute_RoomDoesNotExist() {
        String input = "999\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Scanner scanner = new Scanner(System.in);
        ViewCommand command = new ViewCommand(hotel, scanner);
        command.execute();

        // Pobranie wyjścia konsoli
        String output = outputStream.toString();

        // Sprawdzenie, czy wyświetlany jest komunikat o braku pokoju
        assertTrue(output.contains("Pokój o podanym numerze nie istnieje."),
                "Brak komunikatu o nieistniejącym pokoju.");
    }

    /**
     * Test sprawdzający, czy ViewCommand wyświetla odpowiedni komunikat,
     * gdy użytkownik wprowadzi niepoprawny format numeru pokoju.
     */
    @Test
    public void testExecute_InvalidInput() {
        // Symulacja wejścia użytkownika: podanie niepoprawnej wartości (tekst zamiast liczby)
        String input = "abc\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Scanner scanner = new Scanner(System.in);
        ViewCommand command = new ViewCommand(hotel, scanner);
        command.execute();

        // Pobranie wyjścia konsoli
        String output = outputStream.toString();

        // Sprawdzenie, czy pojawia się komunikat o błędnym numerze
        assertTrue(output.contains("Podano niepoprawny numer pokoju. Spróbuj ponownie."),
                "Brak komunikatu o niepoprawnym numerze.");
    }
}
