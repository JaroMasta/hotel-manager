package pl.edu.agh.kis.pz1.commands;

import pl.edu.agh.kis.pz1.model.Hotel;

/**
 * Klasa `ExitCommand` odpowiedzialna za zakończenie działania programu.
 */
public class ExitCommand extends Command {

    /**
     * Konstruktor inicjalizujący obiekt `ExitCommand` z danym hotelem.
     *
     * @param hotel Obiekt hotelu, z którym będą wykonywane operacje.
     */
    public ExitCommand(Hotel hotel) {
        super(hotel);
    }

    /**
     * Metoda kończąca działanie programu.
     */
    public void execute() {
        System.out.println("Zamykanie programu. Dziękujemy za skorzystanie z systemu hotelowego!");
        System.exit(0); // Zakończenie programu
    }
}
