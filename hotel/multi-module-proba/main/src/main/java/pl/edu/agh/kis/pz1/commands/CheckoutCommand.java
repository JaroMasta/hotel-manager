package pl.edu.agh.kis.pz1.commands;

import pl.edu.agh.kis.pz1.model.Hotel;
import pl.edu.agh.kis.pz1.model.Room;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

/**
 * Klasa `CheckoutCommand` odpowiedzialna za wymeldowanie gości w hotelu.
 */
public class CheckoutCommand extends CheckCommand {
    private final Scanner scanner;
    /**
     * Konstruktor inicjalizujący obiekt `CheckinCommand` z danym hotelem.
     *
     * @param hotel Obiekt hotelu, z którym będą wykonywane operacje.
     */
    public CheckoutCommand(Hotel hotel, Scanner scanner) {
        super(hotel);
        this.scanner = scanner;
    }

    /** Nadpisanie metody execute */
    @Override
    public void execute() {
        checkOut();

    }
    public boolean checkOut() {
        int roomNumber = validateRoomNumber(this.scanner); //powinno zwrocic -1 w przypadku złego numeru
        //jesli walidacja powiodla sie i hotel zawiera ten pokoj i jest on pusty
        if (roomNumber == -1) {
            System.out.println("Walidacja nie powiodła się, pamiętaj że numer powinien być liczba dodatnią");
            return false;
        }
        if (!hotel.getRooms().contains(roomNumber)) {
            System.out.println("Hotel nie zawiera takiego pokoju.");
            return false;
        }
        Room room = this.hotel.getRoom(roomNumber);
        if (!validateRoom(room)){
            return false;
        }
        BigDecimal pricePerNight = room.getPricePerNight();
        LocalDateTime startTime = room.getCheckInDate();
        long daysElapsed = ChronoUnit.DAYS.between(startTime, LocalDateTime.now()) + 1;
        BigDecimal price = pricePerNight.multiply(new BigDecimal(daysElapsed));
        room.setCheckInDate(null);
        room.setStayDuration(0);
        room.setMainGuest(null);
        room.setAdditionalGuests(null);
        System.out.println("Pokój został zwolniony, cena za pobyt to: " + price.toPlainString() + "PLN");
        return true;
    }
    /**
     * Walidacja pokoju przed zameldowaniem.
     *
     * @param room Obiekt pokoju do sprawdzenia.
     * @return true, jeśli pokój ma w sobie gosci, false w przeciwnym razie.
     */
    @Override
    public boolean validateRoom(Room room) {
        if (!room.hasMainGuest()) {
            System.out.println("Pokój jest wolny");
            return false;
        }
        if ((!room.getAdditionalGuests().isEmpty()) && (!room.hasMainGuest())) {
            System.out.println("Pokój nie ma głównego gościa.");
        }
        return true;
    }

}
