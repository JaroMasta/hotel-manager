package pl.edu.agh.kis.pz1.model;

import pl.edu.agh.kis.pz1.map.MyMap;

import java.math.BigDecimal;

/**
 * Klasa reprezentujaca instancje hotelu
 */
public class Hotel {
    private MyMap<Integer,Room> rooms;
     public Hotel(MyMap<Integer, Room> rooms) {
        this.rooms = rooms;
    }
    public Integer size(){
        return rooms.size();
    }
    public MyMap<Integer,Room> getRooms() {
        return rooms;
    }

    public Room getRoom(Integer id) {
         return rooms.get(id);
    }
    public void setRoom(Integer id, Room room) {
        rooms.put(id, room);
    }
    public void addRoom(Integer id, Room room) {
         rooms.put(id, room);
    }
}
