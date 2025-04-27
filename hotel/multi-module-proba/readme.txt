Struktura projektu:
multi-module-proba/
├── parent/
│   └── pom.xml           # Plik konfiguracyjny Maven (parent)
├── main/
│   ├── src/
│   │   └── pl.edu.agh.kis.pz1
│   │       ├── Main.java
│   │       ├── model/
│   │       ├── commands/
│   │       └── utils/
│   └── pom.xml           # Moduł główny
├── utilities/
│   └── src/
│       └── pl.edu.agh.kis.pz1.utils
└── pom.xml               # Plik konfiguracyjny Maven (projekt)


Funkcje systemu
Program wspiera następujące komendy:

prices: Wyświetla listę wszystkich pokoi wraz z cenami za dobę.
view: Wyświetla szczegółowe informacje o danym pokoju (numer pokoju, status zajętości, informacje o gościu).
checkin: Umożliwia rejestrację gościa w wybranym pokoju, wprowadzając dane gości, datę zameldowania oraz długość pobytu.
checkout: Wymeldowuje gości z pokoju i oblicza koszt pobytu na podstawie liczby dni.
list: Wyświetla listę wszystkich pokoi wraz z informacjami o zajętości i zameldowanych gościach.
exit: Zamyka program.
Instrukcja uruchomienia
Sklonuj repozytorium projektu:
git clone <adres_repozytorium>
cd multi-module-proba
Zbuduj projekt za pomocą Maven:
mvn clean install
Uruchom aplikację:
java -jar main/target/main-1.0.jar
Wykonanie testów jednostkowych:
mvn test
Uruchomienie analizy SonarQube (opcjonalnie):
mvn sonar:sonar
Przykład działania programu
Po uruchomieniu programu użytkownik zobaczy tekstowy interfejs, który poprosi o wprowadzenie komendy. Przykład interakcji:
Podaj komendę: checkin
Podaj numer pokoju: 101
Podaj imię głównego gościa: Jan
Podaj nazwisko głównego gościa: Kowalski
Podaj długość pobytu (dni): 5
Gość został pomyślnie zameldowany w pokoju 101.

Podaj komendę: list
Pokój 101: zajęty, Gość: Jan Kowalski, Dodatkowi goście: brak, Data zameldowania: 2024-11-10, Planowana data wymeldowania: 2024-11-15

Podaj komendę: checkout
Podaj numer pokoju: 101
Pokój 101 został wymeldowany. Koszt pobytu: 1500 PLN.

Podaj komendę: exit