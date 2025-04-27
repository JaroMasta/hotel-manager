package pl.edu.agh.kis.pz1.stringManagement;
import java.util.List;
public class StringConcatenation {
    private StringConcatenation() {}
    public static String addWords(List<String> words) {
        StringBuilder sb = new StringBuilder();
        for (String word : words) {
            sb.append(word).append(" ");  // Lączenie każdego wyrazu
        }
        return sb.toString().trim();  // Usuwanie ostatniej spacji
    }
}
