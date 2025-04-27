package pl.edu.agh.kis.pz1.commands;

import pl.edu.agh.kis.pz1.model.Guest;
import pl.edu.agh.kis.pz1.model.Hotel;
import pl.edu.agh.kis.pz1.model.Room;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;



/**
 * Klasa `CheckinCommand` odpowiedzialna za zameldowanie gości w hotelu.
 */
public class CheckinCommand extends CheckCommand {
    private final Scanner scanner;
    /**
     * Konstruktor inicjalizujący obiekt `CheckinCommand` z danym hotelem.
     *
     * @param hotel Obiekt hotelu, z którym będą wykonywane operacje.
     */
    public CheckinCommand(Hotel hotel, Scanner scanner) {
        super(hotel);
        this.scanner = scanner;

    }
    /**
     * Nadpisanie metody execute prosi użytkownika o wpisanie parametrów.
     */
    @Override
    public void execute() {

        int roomNumber = validateRoomNumber(scanner); //powinno zwrocic -1 w przypadku złego numeru
        //jesli walidacja powiodla sie i hotel zawiera ten pokoj i jest on pusty
        if (roomNumber == -1) {
            System.out.println("Walidacja nie powiodła się, pamiętaj że numer powinien być liczba dodatnią");
            return;
        }
        if (!hotel.getRooms().contains(roomNumber)) {
            System.out.println("Hotel nie zawiera takiego pokoju.");
            return;
        }
        Room room = hotel.getRoom(roomNumber);
        if (validateRoom(room)) {

            // Pobranie daty zameldowania
            LocalDateTime checkInDate = getCheckInDate();

            // Pobranie danych głównego gościa
            Guest mainGuest = getGuest(true);

            // Pobranie listy dodatkowych gości
            ArrayList<Guest> additionalGuests = getAdditionalGuests(room.getCapacity());

            // Pobranie długości pobytu
            int stayDuration = getStayDuration();


            if (!checkIn(checkInDate, mainGuest, additionalGuests, stayDuration, roomNumber)) {
                System.out.println("Wystąpił błąd podczas próby zakwaterowania. Zapoznaj się z komunikatami. \n");
            }

        }

    }
    /**
     * Zameldowanie gościa w pokoju na podaną datę.
     *
     * @param checkInDate      Data zameldowania.
     * @param guest            Główny gość do zameldowania.
     * @param additionalGuests Lista dodatkowych gości.
     * @param stayDuration     Czas pobytu w dniach.
     * @return true, jeśli zameldowanie zakończyło się sukcesem, false w przeciwnym razie.
     */
    public boolean checkIn(LocalDateTime checkInDate, Guest guest, ArrayList<Guest> additionalGuests, int stayDuration, int roomNumber) {

        Room room = this.hotel.getRoom(roomNumber);


        room.setCheckInDate(checkInDate);
        room.setStayDuration(stayDuration);
        room.setMainGuest(guest);
        if (additionalGuests != null) {
            room.setAdditionalGuests(additionalGuests);
        }



        return true;
    }


    /**
     * Walidacja pokoju przed zameldowaniem.
     *
     * @param room Obiekt pokoju do sprawdzenia.
     * @return true, jeśli pokój jest dostępny, false w przeciwnym razie.
     */
    @Override
    public boolean validateRoom(Room room) {
        if (room.hasMainGuest()) {
            System.out.println("Pokój jest zajęty");
            return false;
        }
        if (!room.getAdditionalGuests().isEmpty()) {
            System.out.println("Pokój ma w sobie dodatkowych gości, należy ich usunąć");
            return false;
        }
        return true;
    }

    /**
     * Pobiera datę zameldowania od użytkownika.
     */
    LocalDateTime getCheckInDate() {
        System.out.println("Podaj datę zameldowania (format: yyyy-MM-dd HH:mm) lub naciśnij Enter, aby użyć aktualnej daty:");
        String input = scanner.nextLine();

        if (input.trim().isEmpty()) {
            return LocalDateTime.now();
        }

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            return LocalDateTime.parse(input, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Nieprawidłowy format daty. Użyto bieżącej daty.");
            return LocalDateTime.now();
        }
    }

    /**
     * Pobiera dane gościa.
     */
    private  Guest getGuest(boolean isMain) {
        String surname = "";
        String name = "";
        boolean isNameEmpty = true;
        boolean isSurnameEmpty = true;
        while(isNameEmpty || isSurnameEmpty) {
            System.out.println(isMain ? "Podaj dane głównego gościa:" : "Podaj dane dodatkowego gościa:");
            System.out.print("Imię: ");
            name = scanner.nextLine().trim();
            if (!name.isEmpty()) {
                isNameEmpty = false;

            }
            else{ System.out.println("Imie nie moze byc puste!");}
            System.out.print("Nazwisko: ");
            surname = scanner.nextLine().trim();
            if (!surname.isEmpty()) {
                isSurnameEmpty = false;

            }
            else {System.out.println("Nazwisko nie moze byc puste!");}

        }
        return new Guest(name, surname, isMain);
    }

    /**
     * Pobiera listę dodatkowych gości.
     */
    private ArrayList<Guest> getAdditionalGuests(int capacity) {
        ArrayList<Guest> additionalGuests = new ArrayList<>();
        System.out.println("Czy chcesz dodać dodatkowych gości? (tak/nie)");
        String response = scanner.nextLine().trim().toLowerCase();
        int counter = 0;
        while (response.equals("tak")) {
            counter++;
            Guest guest = getGuest(false);
            additionalGuests.add(guest);
            if(capacity == counter){
                System.out.println("Teraz pokoj jest pelny.");
                break;
            }
            System.out.println("Czy chcesz dodać kolejnego gościa? (tak/nie)");
            response = scanner.nextLine().trim().toLowerCase();
        }
        return additionalGuests;
    }

    /**
     * Pobiera długość pobytu.
     */
    private int getStayDuration() {
        System.out.print("Podaj długość pobytu (w dniach): ");
        while (true) {
            String input = scanner.nextLine();
            try {

                int i = Integer.parseInt(input);
                if (i < 0){
                    throw new NumberFormatException();
                }
                return i;
            } catch (NumberFormatException e) {
                System.out.print("Nieprawidłowa wartość. Podaj liczbę całkowitą dodatnią: ");
            }

        }
    }

}

