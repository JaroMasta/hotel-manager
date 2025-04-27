package pl.edu.agh.kis.pz1.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.edu.agh.kis.pz1.map.MyMap;
import pl.edu.agh.kis.pz1.model.Hotel;
import pl.edu.agh.kis.pz1.model.Room;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

public class PricesCommandTest {

    private Hotel hotel;
    private PricesCommand pricesCommand;

    @BeforeEach
    public void setUp() {
        // Tworzymy przykładowe pokoje
        Room room1 = new Room(101, 2, new BigDecimal("100.00"));
        Room room2 = new Room(102, 3, new BigDecimal("150.00"));
        Room room3 = new Room(103, 1, new BigDecimal("80.00"));

        // Dodajemy je do MyMap
        MyMap<Integer, Room> roomsMap = new MyMap<>();
        roomsMap.put(101, room1);
        roomsMap.put(102, room2);
        roomsMap.put(103, room3);

        // Tworzymy obiekt hotelu z mapą pokoi
        hotel = new Hotel(roomsMap);
        pricesCommand = new PricesCommand(hotel);
    }

    /**
     * Test sprawdzający, czy `setPrices()` generuje poprawny string z cenami.
     */
    @Test
    public void testSetPrices() {
        String expected = "Ceny pokoi\n Pokój numer: 101 "   + new BigDecimal("100.00").toString() + " PLN" + '\n' +
                " Pokój numer: 102 "  +  new BigDecimal("150.00").toString()  + " PLN" + '\n' +
                " Pokój numer: 103 " + new BigDecimal("80.00").toString() + " PLN" ;

        String actual = pricesCommand.setPrices();
        assertEquals(expected, actual, "Metoda setPrices() powinna zwrócić poprawne ceny pokoi.");
    }



    /**
     * Test sprawdzający, czy `setPrices()` działa poprawnie przy pustym hotelu.
     */
    @Test
    public void testSetPrices_EmptyHotel() {
        // Pusty hotel
        Hotel emptyHotel = new Hotel(new MyMap<>());
        PricesCommand emptyPricesCommand = new PricesCommand(emptyHotel);

        String expected = "Ceny pokoi";
        String actual = emptyPricesCommand.setPrices();
        assertEquals(expected, actual, "Metoda setPrices() powinna zwrócić tylko nagłówek, gdy hotel jest pusty.");
    }
}