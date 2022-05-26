package Chapter08;

// Maven org.simpleflatmapper:sfm-csv:8.2.3
import org.simpleflatmapper.csv.CsvParser;

import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Bundesliga {
    public Map<Integer, Verein> vereine;
    public List<Spiel> spiele;

    private Bundesliga(Map<Integer, Verein> vereine, List<Spiel> spiele) {
        this.vereine = vereine;
        this.spiele = spiele;
    }

    public static Bundesliga loadFromResource() throws IOException {

        ClassLoader classLoader = Bundesliga.class.getClassLoader();

        Reader reader2 = new InputStreamReader(classLoader.getResourceAsStream("Chapter08/bundesliga_Spiel.csv"));
        List<Spiel> spiele = CsvParser
                .separator(';')
                .mapTo(Spiel.class)
                .stream(reader2)
                .collect(Collectors.toList());

        Reader reader1 = new InputStreamReader(classLoader.getResourceAsStream("Chapter08/bundesliga_Verein.csv"));
        Map<Integer, Verein> vereine = CsvParser
                .separator(';')
                .mapTo(Verein.class)
                .stream(reader1)
                .collect(Collectors.toMap(Verein::getV_ID, Function.identity()));

        return new Bundesliga(vereine, spiele);
    }
}