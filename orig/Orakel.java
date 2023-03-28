package orig;

import java.util.ArrayList;
import java.util.Random;

/**
 * Diese Klasse erzeugt die Komponenten des Glückstickets.
 * Diese werden als String zurückgegeben
 *
 * @author Andreas Wassmer
 * @version 1.0
 */

public class Orakel {
    private Random random;
    private ArrayList<String> spells;
    private ArrayList<String> symbols;

    public Orakel() {
        random = new Random();
        spells = new ArrayList<>();
        symbols = new ArrayList<>();
        initSpells();
        initSymbols();
    }

    public static void main(String[] args) {
        Orakel orakel = new Orakel();
        System.out.println(orakel.getLuckySpell());
        System.out.println("Ihr Glückssymbol ist: " + orakel.getLuckySymbol());
        System.out.println("Ihre Glückszahl ist : " + orakel.getLuckyNumber());
    }

    private void initSpells() {
        spells.add("Nutze den Tag.");
        spells.add("Nimm es leicht.");
        spells.add("Lächle und die Welt lächelt zurück.");
        spells.add("Nach dem Regen scheint die Sonne.");
    }

    private void initSymbols() {
        symbols.add("Kleeblatt");
        symbols.add("Kaminfeger");
        symbols.add("Hufeisen");
        symbols.add("Schweinchen");
    }
    public String getLuckyNumber() {
        return String.valueOf(random.nextInt(30) + 1);
    }

    public String getLuckySymbol() {
        int numOfSymbols = symbols.size();
        return symbols.get(random.nextInt(numOfSymbols));
    }

    public String getLuckySpell() {
        int numOfSpells = spells.size();
        return spells.get(random.nextInt(numOfSpells));
    }
}
