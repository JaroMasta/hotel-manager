package pl.edu.agh.kis.pz1.model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

    /**
     * Testy jednostkowe dla klasy Guest
     */
    public class GuestTest {
        private Guest guest;

        @BeforeEach
        void setUp() {
            // Przygotowanie testowego obiektu przed każdym testem
            guest = new Guest("Jan", "Kowalski", true);
        }

        @Test
        void testGuestCreationValid() {
            // Testujemy poprawne stworzenie obiektu Guest
            assertNotNull(guest);
            assertEquals("Jan", guest.getName());
            assertEquals("Kowalski", guest.getSurname());
            assertTrue(guest.isMain());
            assertEquals("Jan Kowalski", guest.getFullName());
        }

        @Test
        void testInvalidName() {
            // Testujemy przypadek, gdy name jest null
            assertThrows(IllegalArgumentException.class, () -> new Guest(null, "Kowalski", true));

            // Testujemy przypadek, gdy name jest pusty
            assertThrows(IllegalArgumentException.class, () -> new Guest("", "Kowalski", true));

            // Testujemy przypadek, gdy name jest tylko białymi znakami
            assertThrows(IllegalArgumentException.class, () -> new Guest("   ", "Kowalski", true));
        }

        @Test
        void testInvalidSurname() {
            // Testujemy przypadek, gdy surname jest null
            assertThrows(IllegalArgumentException.class, () -> new Guest("Jan", null, true));

            // Testujemy przypadek, gdy surname jest pusty
            assertThrows(IllegalArgumentException.class, () -> new Guest("Jan", "", true));

            // Testujemy przypadek, gdy surname jest tylko białymi znakami
            assertThrows(IllegalArgumentException.class, () -> new Guest("Jan", "   ", true));
        }

        @Test
        void testGetFullName() {
            // Sprawdzamy czy pełne imię i nazwisko jest poprawne
            assertEquals("Jan Kowalski", guest.getFullName());
        }

        @Test
        void testIsMain() {
            // Testujemy, czy metoda isMain() zwraca poprawną wartość
            assertTrue(guest.isMain()); // Główny gość
            Guest secondGuest = new Guest("Anna", "Nowak", false);
            assertFalse(secondGuest.isMain()); // Drugi gość nie jest głównym
        }
    }