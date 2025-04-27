package pl.edu.agh.kis.pz1.commands;

import pl.edu.agh.kis.pz1.map.MyMap;
import pl.edu.agh.kis.pz1.model.Hotel;
import pl.edu.agh.kis.pz1.model.Room;
import pl.edu.agh.kis.pz1.model.Guest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Klasa `ListCommand` służąca do wyświetlania listy wszystkich pokoi wraz z informacjami
 * o zajętości, gościach, datą zameldowania i planowaną datą wymeldowania.
 */
public class ListCommand extends Command {

    /**
     * Konstruktor klasy `ListCommand`.
     *
     * @param hotel Obiekt hotelu zawierający listę pokoi.
     */
    public ListCommand(Hotel hotel) {
        super(hotel);
    }

    /**
     * Metoda wykonująca listowanie wszystkich pokoi z ich szczegółami.
     */
    public void execute() {
        MyMap<Integer, Room> rooms = hotel.getRooms();

        if (rooms.keys().isEmpty()) {
            System.out.println("Brak pokoi w hotelu.");
            return;
        }

        System.out.println("\nLista pokoi:");
        for (Integer nr : rooms.keys()) {
            Room room = rooms.get(nr);

            System.out.println("\nPokój numer: " + nr);
            System.out.println("Pojemność: " + room.getCapacity());
            System.out.println("Cena za dobę: " + room.getPricePerNight() + " PLN");

            if (room.hasMainGuest()) {
                System.out.println("Status: Zajęty");
                Guest mainGuest = room.getMainGuest();
                if (mainGuest != null) {
                    System.out.println("Główny gość: " + mainGuest.getFullName());
                }

                if (!room.getAdditionalGuests().isEmpty()) {
                    System.out.println("Dodatkowi goście:");
                    for (Guest guest : room.getAdditionalGuests()) {
                        System.out.println("- " + guest.getFullName());
                    }
                }

                // Wyświetlanie dat zameldowania i wymeldowania
                LocalDateTime checkInDate = room.getCheckInDate();
                int stayDuration = room.getStayDuration();
                LocalDateTime checkOutDate = checkInDate.plusDays(stayDuration);

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                System.out.println("Data zameldowania: " + checkInDate.format(formatter));
                System.out.println("Planowana data wymeldowania: " + checkOutDate.format(formatter));

            } else {
                System.out.println("Status: Wolny");
            }
        }
    }
}

