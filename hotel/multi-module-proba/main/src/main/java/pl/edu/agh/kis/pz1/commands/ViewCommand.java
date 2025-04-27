package pl.edu.agh.kis.pz1.commands;

import pl.edu.agh.kis.pz1.model.Guest;
import pl.edu.agh.kis.pz1.model.Hotel;
import pl.edu.agh.kis.pz1.model.Room;

import java.util.Scanner;

/**
 * Komenda wypisujaca wszystkie informajce na temat pokoju
 */
public class ViewCommand extends Command {
    private Scanner scanner;
    /**
     * Konstruktor inicjujący obiekt View z danym hotelem.
     *
     * @param hotel Obiekt hotelu, z którego będą pobierane informacje.
     */
    public ViewCommand(Hotel hotel, Scanner scanner){
        super(hotel);
        this.scanner = scanner;
    }
    /**
     * Metoda sluzaca do walidacji wpisanego rekordu i wypisania
     * wszystkich informacji na temat pokoju.
     */
    public void execute() {
        System.out.print("Podaj numer pokoju: ");
        int roomNumber;

        try {
            roomNumber = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Podano niepoprawny numer pokoju. Spróbuj ponownie.");
            return;
        }

        Room room = hotel.getRooms().get(roomNumber);

        if (room == null) {
            System.out.println("Pokój o podanym numerze nie istnieje.");
        } else {
            System.out.println("\nInformacje o pokoju:");
            System.out.println("Numer pokoju: " + roomNumber);
            System.out.println("Pojemność: " + room.getCapacity());
            System.out.println("Cena za dobę: " + room.getPricePerNight());

            if (room.hasMainGuest()) {
                System.out.println("\nGłówny gość:");
                Guest mainGuest = room.getMainGuest();
                System.out.println("Imię i nazwisko: " + mainGuest.getFullName());
                if (room.getAdditionalGuests() != null) {
                    for (Guest guest : room.getAdditionalGuests()) {
                        System.out.println("Imię i nazwisko dodatkowego gościa: " + guest.getFullName());
                    }
                }
            } else {
                System.out.println("Pokój jest wolny.");
            }
        }
    }

}
