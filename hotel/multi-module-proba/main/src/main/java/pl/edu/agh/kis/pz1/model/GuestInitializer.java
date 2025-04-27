package pl.edu.agh.kis.pz1.model;

import java.util.ArrayList;
import java.util.List;
/**
 * Klasa pomocnicza do inicjalizacji listy przykładowych gości hotelowych.
 */
public class GuestInitializer {
    private GuestInitializer() {}
    /**
     * Metoda tworząca listę głównych gości.
     *
     * @return lista 20 głównych gości
     */
    public static List<Guest> createMainGuests() {
        List<Guest> mainGuests = new ArrayList<>();
        mainGuests.add(new Guest("Jan", "Kowalski", true));
        mainGuests.add(new Guest("Anna", "Nowak", true));
        mainGuests.add(new Guest("Piotr", "Wiśniewski", true));
        mainGuests.add(new Guest("Katarzyna", "Wójcik", true));
        mainGuests.add(new Guest("Marek", "Kamiński", true));
        mainGuests.add(new Guest("Agnieszka", "Lewandowska", true));
        mainGuests.add(new Guest("Tomasz", "Zieliński", true));
        mainGuests.add(new Guest("Ewa", "Szymańska", true));
        mainGuests.add(new Guest("Grzegorz", "Woźniak", true));
        mainGuests.add(new Guest("Magdalena", "Dąbrowska", true));
        mainGuests.add(new Guest("Adam", "Kozłowski", true));
        mainGuests.add(new Guest("Joanna", "Jankowska", true));
        mainGuests.add(new Guest("Marcin", "Pawlak", true));
        mainGuests.add(new Guest("Monika", "Krawczyk", true));
        mainGuests.add(new Guest("Paweł", "Król", true));
        mainGuests.add(new Guest("Dorota", "Wieczorek", true));
        mainGuests.add(new Guest("Robert", "Wróbel", true));
        mainGuests.add(new Guest("Aleksandra", "Ostrowska", true));
        mainGuests.add(new Guest("Krzysztof", "Pietrzak", true));
        mainGuests.add(new Guest("Beata", "Zawadzka", true));
        return mainGuests;
    }

    /**
     * Metoda tworząca listę gości niebędących głównymi.
     *
     * @return lista 20 dodatkowych gości
     */
    public static List<Guest> createAdditionalGuests() {
        List<Guest> additionalGuests = new ArrayList<>();
        additionalGuests.add(new Guest("Patryk", "Czarnecki", false));
        additionalGuests.add(new Guest("Julia", "Wysocka", false));
        additionalGuests.add(new Guest("Wojciech", "Adamski", false));
        additionalGuests.add(new Guest("Sylwia", "Chmielewska", false));
        additionalGuests.add(new Guest("Sebastian", "Górski", false));
        additionalGuests.add(new Guest("Karolina", "Nowicka", false));
        additionalGuests.add(new Guest("Michał", "Lis", false));
        additionalGuests.add(new Guest("Izabela", "Piątek", false));
        additionalGuests.add(new Guest("Bartosz", "Maj", false));
        additionalGuests.add(new Guest("Renata", "Michalska", false));
        additionalGuests.add(new Guest("Lukasz", "Sikora", false));
        additionalGuests.add(new Guest("Natalia", "Błaszczyk", false));
        additionalGuests.add(new Guest("Dariusz", "Malinowski", false));
        additionalGuests.add(new Guest("Martyna", "Olejniczak", false));
        additionalGuests.add(new Guest("Jacek", "Urbański", false));
        additionalGuests.add(new Guest("Elżbieta", "Tomczak", false));
        additionalGuests.add(new Guest("Mateusz", "Bąk", false));
        additionalGuests.add(new Guest("Aneta", "Maciejewska", false));
        additionalGuests.add(new Guest("Damian", "Cieślak", false));
        additionalGuests.add(new Guest("Paulina", "Michalak", false));
        return additionalGuests;
    }
}
