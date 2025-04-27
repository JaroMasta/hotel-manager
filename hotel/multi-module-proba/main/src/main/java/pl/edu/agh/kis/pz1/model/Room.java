package pl.edu.agh.kis.pz1.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Reprezentuje pokój w hotelu wraz z jego właściwościami i aktualnym stanem
 */
public class Room {
    private final int number;          // Numer pokoju (np. 101 - pierwsze piętro, pokój 1)
    private final int capacity;        // Maksymalna liczba gości
    private final BigDecimal pricePerNight;  // Cena za dobę
    private String description;        // Opis pokoju
    private Guest mainGuest;           // Główny gość (null jeśli pokój jest pusty)
    private List<Guest> additionalGuests;  // Lista dodatkowych gości
    private LocalDateTime checkInDate; // Data zameldowania
    private int stayDuration;          // Planowany czas pobytu w dniach

    /**
     * Tworzy nowy pokój o zadanych parametrach
     * @param number Numer pokoju
     * @param capacity Pojemność pokoju
     * @param pricePerNight Cena za dobę
     */
    public Room(int number, int capacity, BigDecimal pricePerNight) {
        this.number = number;
        this.capacity = capacity;
        this.pricePerNight = pricePerNight;
        this.additionalGuests = new ArrayList<>();
    }

    /**
     * Sprawdza czy pokój ma glownego goscia
     * @return true jeśli pokój ma zameldowanego gościa, false w przeciwnym razie
     */
    public boolean hasMainGuest() {
        return mainGuest != null;
    }

    /**
     * Oblicza numer piętra na podstawie numeru pokoju
     * @return Numer piętra
     */
    public int getFloor() {
        return number / 100;
    }
    public int getNumber() {
        return number;
    }

    public int getCapacity() {
        return capacity;
    }

    public BigDecimal getPricePerNight() {
        return pricePerNight;
    }

    public List<Guest> getAdditionalGuests() {
        if (additionalGuests == null) {
            additionalGuests = new ArrayList<>();
        }
        return Collections.unmodifiableList(additionalGuests);
    }
    public void setAdditionalGuests(List<Guest> additionalGuests) {
        this.additionalGuests = additionalGuests;
    }

    public void setMainGuest(Guest mainGuest) {
        this.mainGuest = mainGuest;
    }
    public void setCheckInDate(LocalDateTime checkInDate) {
        this.checkInDate = checkInDate;
    }
    public void setStayDuration(int stayDuration) {
        this.stayDuration = stayDuration;
    }

    public LocalDateTime getCheckInDate() {
        return checkInDate;
    }

    public String getDescription() {
        return description;
    }
    public Guest getMainGuest() {
        return mainGuest;
    }
    public int getStayDuration(){
        return stayDuration;
    }

}
