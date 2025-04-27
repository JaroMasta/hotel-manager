package pl.edu.agh.kis.pz1.commands;

import pl.edu.agh.kis.pz1.model.Hotel;
import pl.edu.agh.kis.pz1.stringManagement.StringConcatenation;
import pl.edu.agh.kis.pz1.map.MyMap;
import pl.edu.agh.kis.pz1.model.Room;
import java.util.ArrayList;
import java.util.List;

/**
 * Komenda pokazujaca ceny pokoi
 */
public class PricesCommand extends Command {
    String prices;
    /** Nadpisanie metody execute */
    @Override
    public void execute() {
        this.prices = setPrices();
        System.out.println(prices);
    }
    public PricesCommand(Hotel hotel) {
        super(hotel);
    }
    public String setPrices() {
        MyMap<Integer, Room> rooms = hotel.getRooms();
        List<String> words;
        words = new ArrayList<>();
        words.add("Ceny pokoi\n");
        for (Integer key : rooms.keys()){
            words.add("Pokój numer: " + key + " " + rooms.get(key).getPricePerNight().toString() + " PLN" + "\n");
        }
        prices = StringConcatenation.addWords(words);
        return prices;
    }


}
