package pl.edu.agh.kis.pz1.map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
class MyMapTest {
    private MyMap<String, Integer> myMap;
    @BeforeEach
    void setUp() {
        myMap = new MyMap<>();

    }
    @Test
    void put() {
        // Dodawanie elementów
        assertFalse(myMap.put("Alice", 25));
        assertEquals(25, myMap.get("Alice"));
        assertTrue(myMap.put("Alice", 30));
        assertEquals(30, myMap.get("Alice"));
    }

    @Test
    void get() {
        myMap.put("Alice", 25);
        assertEquals(25, myMap.get("Alice"));
        assertNull(myMap.get("Tomek"));
    }

    @Test
    void keys() {
        myMap.put("Alice", 25);
        myMap.put("Tomek", 30);
        List<String> keys = myMap.keys();
        assertEquals(2, keys.size());
        assertTrue(keys.contains("Alice"));
        assertFalse(keys.contains("Bob"));
    }

    @Test
    void remove() {
        myMap.put("Alice", 25);
        myMap.put("Tomek", 30);
        assertTrue(myMap.remove("Alice"));
        assertFalse(myMap.remove("Alice"));
        assertTrue(myMap.remove("Tomek"));
        assertFalse(myMap.remove("Tomek"));


    }

    @Test
    void contains() {
        myMap.put("Alice", 25);
        assertTrue(myMap.contains("Alice"));
        assertFalse(myMap.contains("Tomek"));
    }

    @Test
    void size() {
        assertEquals( 0 , myMap.size());
        myMap.put("Alice", 25);
        assertEquals(1, myMap.size());
        myMap.put("Bob", 25);
        assertEquals(2, myMap.size());
    }
}