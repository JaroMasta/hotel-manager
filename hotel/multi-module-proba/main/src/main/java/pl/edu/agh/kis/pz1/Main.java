package pl.edu.agh.kis.pz1;

import org.apache.commons.io.LineIterator;
import pl.edu.agh.kis.pz1.commands.*;
import pl.edu.agh.kis.pz1.map.MyMap;
import pl.edu.agh.kis.pz1.model.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Klasa Glowna do wystartowania programu
 */
public class Main {

    public static void main(String[] args ) {
        Hotel hotel = initializeHotel();
        Scanner scanner = new Scanner(System.in);
        CheckinCommand checkinCommand = new CheckinCommand(hotel, scanner);
        List<Guest> mainGuests = GuestInitializer.createMainGuests();
        List<Guest> nonmainGuests = GuestInitializer.createAdditionalGuests();
        Iterator<Guest> iteratorMainGuests = mainGuests.iterator();
        Iterator<Guest> iteratorNonMainGuests = nonmainGuests.iterator();
        for (Integer nr : hotel.getRooms().keys()){
            if (nr%3 != 0 && iteratorMainGuests.hasNext() && iteratorNonMainGuests.hasNext()){
                Guest mainGuest = iteratorMainGuests.next();
                Room room = hotel.getRoom(nr);
                ArrayList<Guest> additionalGuests= new ArrayList<>();
                for (int i = 0; i < room.getCapacity()-1; i++) {
                    if(!iteratorNonMainGuests.hasNext()){ break;}
                    additionalGuests.add(iteratorNonMainGuests.next());
                }
                Random random = new Random();
                int randomNumber = random.nextInt(14) + 1;
                checkinCommand.checkIn(LocalDateTime.now(), mainGuest, additionalGuests, randomNumber, nr);

            }

        }
        System.out.println( "Witaj w systemie kontroli pokojów hotelu" );
        HotelSystem systemInstance = new HotelSystem(hotel, scanner);
        systemInstance.system();
    }
    /**
     * Inicjalizuje hotel z przykładowymi pokojami na różnych piętrach.
     */
    static Hotel initializeHotel() {
        Hotel hotel = new Hotel(new MyMap<>());

        // Dodanie pokoi na 1 piętrze
        hotel.addRoom(101, new Room(101, 2, new BigDecimal("200.00")));
        hotel.addRoom(102, new Room(102, 3, new BigDecimal("250.00")));
        hotel.addRoom(103, new Room(103, 1, new BigDecimal("150.00")));
        hotel.addRoom(104, new Room(104, 2, new BigDecimal("220.00")));

        // Dodanie pokoi na 2 piętrze
        hotel.addRoom(201, new Room(201, 2, new BigDecimal("300.00")));
        hotel.addRoom(202, new Room(202, 4, new BigDecimal("400.00")));
        hotel.addRoom(203, new Room(203, 1, new BigDecimal("180.00")));
        hotel.addRoom(204, new Room(204, 3, new BigDecimal("320.00")));

        // Dodanie pokoi na 3 piętrze
        hotel.addRoom(301, new Room(301, 2, new BigDecimal("280.00")));
        hotel.addRoom(302, new Room(302, 3, new BigDecimal("350.00")));
        hotel.addRoom(303, new Room(303, 2, new BigDecimal("200.00")));
        hotel.addRoom(304, new Room(304, 4, new BigDecimal("450.00")));

        // Dodanie pokoi na 4 piętrze
        hotel.addRoom(401, new Room(401, 1, new BigDecimal("120.00")));
        hotel.addRoom(402, new Room(402, 2, new BigDecimal("270.00")));
        hotel.addRoom(403, new Room(403, 3, new BigDecimal("330.00")));
        hotel.addRoom(404, new Room(404, 5, new BigDecimal("500.00")));

        // Dodanie pokoi na 5 piętrze (dla bardziej luksusowych gości)
        hotel.addRoom(501, new Room(501, 2, new BigDecimal("500.00")));
        hotel.addRoom(502, new Room(502, 3, new BigDecimal("600.00")));
        hotel.addRoom(503, new Room(503, 4, new BigDecimal("750.00")));
        hotel.addRoom(504, new Room(504, 1, new BigDecimal("800.00")));

        return hotel;
    }


}
