package pl.edu.agh.kis.pz1.map;

import java.util.ArrayList;
import java.util.List;

/**
 * Własna implementacja mapy wykorzystująca dwie równoległe listy do przechowywania
 * kluczy i wartości. Klasa jest generyczna, gdzie K to typ klucza, a V to typ wartości.
 */
public class MyMap<K, V> implements Map<K, V> {
    // Przechowuje klucze mapy
    private final List<K> keys;
    // Przechowuje wartości mapy, indeks wartości odpowiada indeksowi klucza
    private final List<V> values;

    /**
     * Konstruktor inicjalizujący puste listy na klucze i wartości
     */
    public MyMap() {
        keys = new ArrayList<>();
        values = new ArrayList<>();
    }

    /**
     * Dodaje parę klucz-wartość do mapy lub aktualizuje istniejącą wartość
     * @param key Klucz
     * @param value Wartość
     * @return Poprzednia wartość dla danego klucza lub null jeśli klucz jest nowy
     */
    @Override
    public boolean put(K key, V value) {
        int index = keys.indexOf(key);
        if (index >= 0) {
            // Jeśli klucz istnieje, aktualizujemy wartość
            values.set(index, value);
            return true;
        }
        // Jeśli klucz jest nowy, dodajemy nową parę
        keys.add(key);
        values.add(value);
        return false;
    }

    /**
     * Pobiera wartość dla danego klucza
     * @param key Klucz
     * @return Wartość dla klucza lub null jeśli klucz nie istnieje
     */
    @Override
    public V get(K key) {
        int index = keys.indexOf(key);
        return index >= 0 ? values.get(index) : null;
    }

    /**
     * Zwraca listę wszystkich kluczy w mapie
     * @return Nowa lista zawierająca wszystkie klucze
     */
    @Override
    public List<K> keys() {
        return new ArrayList<>(keys);
    }

    /**
     * Usuwa parę klucz-wartość z mapy
     * @param key Klucz do usunięcia
     * @return zwraca true jeśli była taka wartosc
     * false jesli jej nie bylo
     */
    @Override
    public boolean remove(K key) {
        int index = keys.indexOf(key);
        if (index >= 0) {
            keys.remove(index);
            return true;
        }
        return false;
    }
    @Override
    public boolean contains (K key) {
        return keys.contains(key);
    }

    @Override
    public Integer size(){
        return keys.size();
    }
}
