package pl.edu.agh.kis.pz1.model;
/**
 * Reprezentuje gościa hotelowego wraz z imieniem, nazwiskiem i informacja
 * czy jest glownym gosciem.
 */
public class Guest {
    private final String name;           // Imię gościa
    private final String surname;
    private boolean isMain;
    /**
     * Konstruktor tworzący nowego gościa z podstawowymi, wymaganymi danymi.
     *
     * @param name Imię gościa (nie może być null ani puste)
     * @param surname Nazwisko gościa (nie może być null ani puste)
     * @throws IllegalArgumentException jeśli któryś z parametrów jest nieprawidłowy
     */
    public Guest(String name, String surname, boolean isMain) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Imie nie moze byc puste");
        }
        if (surname == null || surname.trim().isEmpty()) {
            throw new IllegalArgumentException("Nazwisko nie moze byc puste");
        }
        this.name = name.trim();
        this.surname = surname.trim();
        this.isMain = isMain;
    }
    /**
     * @return string imie goscia.
     */
    public String getName() { return name; }
    /**
     * @return string nazwisko goscia.
     */
    public String getSurname() { return surname; }
    /**
     * @return boolean czy gosc jest gosciem glownym.
     */
    public boolean isMain() { return isMain;}
    /**
     * Zwraca pełne imię i nazwisko gościa.
     *
     * @return String w formacie "Imię Nazwisko"
     */
    public String getFullName() {
        return name + " " + surname;
    }


}
