package pl.edu.agh.kis.pz1.model;

import pl.edu.agh.kis.pz1.commands.Command;
import pl.edu.agh.kis.pz1.commands.*;
import pl.edu.agh.kis.pz1.map.MyMap;

import java.util.Scanner;

/** System do zarzadzania hotelem
 */
public class HotelSystem {
    private Hotel hotel;
    private final MyMap<String, Command> commands = new MyMap<String, Command>();
    Scanner sc;

    /** Konstruktor inicjalizujący komendy i wkładający je do MyMap
     *
     * @param hotel
     */
    public HotelSystem(Hotel hotel, Scanner scanner) {
        sc = scanner;
        this.hotel = hotel;
        commands.put("exit", new ExitCommand(this.hotel));
        commands.put("list", new ListCommand(this.hotel));
        commands.put("checkout", new CheckoutCommand(this.hotel, sc));
        commands.put("checkin", new CheckinCommand(this.hotel, sc));
        commands.put("view", new ViewCommand(this.hotel, sc));
        commands.put("prices", new PricesCommand(this.hotel));


    }

    /** Klasa do dopasowywania wejścia użytkownika do komendy
     */
    public void system(){

        while(true){
            try{

                System.out.print("Podaj komende: ");
                String command = sc.nextLine().trim().toLowerCase();
                Command commandObj = commands.get(command);
                if(commandObj == null){
                    System.out.println("Komenda: " + command + " jest niepoprawna");
                }
                else{
                    commandObj.execute();
                }
            }
            catch(Exception e){
                System.out.println(e.getMessage());
            }
        }
    }


}
