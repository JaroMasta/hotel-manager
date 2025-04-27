package pl.edu.agh.kis.pz1.commands;

import pl.edu.agh.kis.pz1.model.Hotel;
import pl.edu.agh.kis.pz1.model.Room;

import java.util.Scanner;

public abstract class CheckCommand extends Command {
    CheckCommand(Hotel hotel) {
        super(hotel);

    }
    /**
     * Walidacja numeru pokoju wprowadzonego przez użytkownika.
     * Uwaga! Tworzony jest nowy scanner
     * @return Numer pokoju lub -1, jeśli walidacja nie powiodła się.
     */
    protected Integer validateRoomNumber() {
        // Inicjalizacja obiektu Scanner do odczytu wejścia od użytkownika
        Scanner scanner = new Scanner(System.in);
        System.out.print("Podaj numer pokoju: ");

        // Odczytanie danych wejściowych jako String
        String input = scanner.nextLine();

        int roomNumber;
        try {
            // Próba konwersji wejścia na typ int
            roomNumber = Integer.parseInt(input);

            // Sprawdzenie, czy numer pokoju jest dodatni
            if (roomNumber <= 0) {

                System.out.println("Numer pokoju musi być większy niż 0.");
                return -1;
            }
        } catch (NumberFormatException e) {
            // Obsługa błędu w przypadku niepoprawnego formatu wejścia
            System.out.println("Podano niepoprawny numer pokoju.");
            return -1;
        }
        return roomNumber;
    }
    /**
     * Walidacja numeru pokoju wprowadzonego przez użytkownika.
     *
     * @return Numer pokoju lub -1, jeśli walidacja nie powiodła się.
     */
    protected Integer validateRoomNumber(Scanner scanner) {
        System.out.print("Podaj numer pokoju: ");

        // Odczytanie danych wejściowych jako String
        String input = scanner.nextLine();

        int roomNumber;
        try {
            // Próba konwersji wejścia na typ int
            roomNumber = Integer.parseInt(input);

            // Sprawdzenie, czy numer pokoju jest dodatni
            if (roomNumber <= 0) {

                System.out.println("Numer pokoju musi być większy niż 0.");
                return -1;
            }
        } catch (NumberFormatException e) {
            // Obsługa błędu w przypadku niepoprawnego formatu wejścia
            System.out.println("Podano niepoprawny numer pokoju.");
            return -1;
        }
        return roomNumber;
    }


    /**
     * Walidacja pokoju przed zameldowaniem.
     *
     * @param room Obiekt pokoju do sprawdzenia.
     * @return true, jeśli pokój jest dostępny, false w przeciwnym razie.
     */
    public boolean validateRoom(Room room) {
        if (room.hasMainGuest()) {
        return false;
        }
        if (!room.getAdditionalGuests().isEmpty()) {
            return false;
        }
        return true;
    }

}
