package pl.edu.agh.kis.pz1;


import org.junit.jupiter.api.Test;
import pl.edu.agh.kis.pz1.model.Hotel;
import pl.edu.agh.kis.pz1.model.Room;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class MainTest {


    /**
     * Test for the construction of Main and the 
     * main method being called
     *
     */
    @Test
    public void shouldCreateMainObject(){
        Main main = new Main();
        assertNotNull(main, "Main method called.");
    }
    @Test
    void testInitializeHotel() {
        // Inicjalizujemy hotel przy użyciu metody initializeHotel()
        Hotel hotel = Main.initializeHotel();

        // Sprawdzamy, czy hotel zawiera odpowiednią liczbę pokoi
        assertEquals(20, hotel.getRooms().size(), "Hotel powinien zawierać 20 pokoi");

        // Sprawdzamy, czy pokój o numerze 101 istnieje
        Room room101 = hotel.getRoom(101);
        assertNotNull(room101, "Pokój o numerze 101 powinien istnieć");
        assertEquals(2, room101.getCapacity(), "Pokój 101 powinien mieć pojemność 2");


        // Sprawdzamy, czy pokój o numerze 201 istnieje
        Room room201 = hotel.getRoom(201);
        assertNotNull(room201, "Pokój o numerze 201 powinien istnieć");
        assertEquals(2, room201.getCapacity(), "Pokój 201 powinien mieć pojemność 2");


        // Sprawdzamy, czy pokój o numerze 501 istnieje
        Room room501 = hotel.getRoom(501);
        assertNotNull(room501, "Pokój o numerze 501 powinien istnieć");
        assertEquals(2, room501.getCapacity(), "Pokój 501 powinien mieć pojemność 2");

    }
}


