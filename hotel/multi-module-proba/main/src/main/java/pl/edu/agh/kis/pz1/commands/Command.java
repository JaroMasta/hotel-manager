package pl.edu.agh.kis.pz1.commands;

import pl.edu.agh.kis.pz1.model.Hotel;

/**
 * Klasa abstrakcyjna po ktorej dziedzicza wszystkie inne komendy
 */
public abstract class Command {
    protected Hotel hotel;
    /** Konstruktor
     * @param hotel podaj hotel
     */
    protected Command(Hotel hotel) {
        this.hotel = hotel;
    }
    public abstract void execute();

}
