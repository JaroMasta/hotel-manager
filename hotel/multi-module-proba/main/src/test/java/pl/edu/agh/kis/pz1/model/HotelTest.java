package pl.edu.agh.kis.pz1.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.edu.agh.kis.pz1.map.MyMap;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class HotelTest {
    Hotel hotel;
    @BeforeEach
    public void setUp() {
        MyMap<Integer, Room> rooms = new MyMap<>();
        hotel = new Hotel(rooms);

        // Dodanie pokoi do hotelu
        Room room1 = new Room(101, 2, new BigDecimal("100"));
        hotel.getRooms().put(101, room1);

        Room room2 = new Room(102, 2, new BigDecimal("150"));
        hotel.getRooms().put(102, room2);

        Room room3 = new Room(103, 1, new BigDecimal("80"));
        hotel.getRooms().put(103, room3);
    }
    @Test
    void checkSize(){
        assertEquals(3, hotel.size());
    }
    @Test
    void checkgetRooms(){

            MyMap<Integer, Room> rooms = hotel.getRooms();
            assertNotNull(rooms);
            assertEquals(3, rooms.size());
            assertEquals(rooms.get(101), rooms.get(101));
            assertEquals(rooms.get(102), rooms.get(102));
            assertEquals(rooms.get(103), rooms.get(103));
        assertNotNull(hotel.getRooms());

    }
}