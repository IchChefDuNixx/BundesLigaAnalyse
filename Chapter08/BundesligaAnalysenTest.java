package Chapter08;

// Maven org.apache.commons:commons-lang3:3.3.2
import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.lang3.tuple.Triple;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.*;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BundesligaAnalysenTest {
    /**
     * T1 Wie viele Tore fallen durchschnittlich in jedem Spiel?
     */
    @Test
    void torstatistikenToreProSpiel() throws IOException {
        Bundesliga b = Bundesliga.loadFromResource();

        // 2.6458966565349544
        assertEquals(2.645, 0, 0.001);
    }

    /**
     * T2 Wie viele Tore fallen durchschnittlich in einem Spiel der 1. Liga?
     */
    @Test
    void torstatustikenToreProErstligaspiel() throws IOException {

        // 2.7465277777777777
        assertEquals(2.746, 0, 0.001);
    }

    /**
     * T3 Wie viele Tore fallen durchschnittlich an einem Spieltag der 2. Liga?
     */
    @Test
    void torstatistikenToreProSpieltag2teLiga() throws IOException {

        // 24.96875
        assertEquals(24.968, 0, 0.001);
    }

    /**
     * T4 Stimmt es, dass in den Nachmittagsspielen (15:30:00) im Schnitt mehr Tore
     * fallen, wie in den Abendspielen?
     */
    @Test
    void torstatistikenToreNachmittagsAbends() throws IOException {

        assertTrue(false);
    }

    /**
     * T5 Stimmt es, dass Vereine der 3. Liga zuhause im Schnitt mehr Tore schießen
     * als auswärts?
     */
    @Test
    void torstatistikenToreDaheim() throws IOException {

        assertTrue(false);
    }

    /**
     * V1 Wie viele Tore hat der FC Bayern München (Verein 1) erzielt?
     */
    @Test
    void vereineToreVerein1erzielt() throws IOException {

        assertEquals(88, 0);
    }

    /**
     * V2 Wie viele Tore hat der FC Schalke 04 (Verein 2) erhalten?
     */
    @Test
    void vereineToreVerein2erhalten() throws IOException {

        assertEquals(36, 0);
    }

    /**
     * V3 Wie viele Punkte hat der 1. FC Nürnberg (Verein 20)?
     * Ein Sieg zählt 3 Punkte, ein Unentschieden 1, eine Niederlage 0 Punkte.
     */
    @Test
    void vereineToreVerein20punkte() throws IOException {

        assertEquals(57, 0);
    }

    /**
     * V4 Was ist das Torverhältnis des VfL Bochum (Verein 26)?
     */
    @Test
    void vereineTorverhaeltnis26() throws IOException {

        assertEquals(35, 0); // erzielt
        assertEquals(36, 0); // erhalten
    }

    /**
     * V5 Welche drei Vereine haben die meisten Tore zuhause geschossen, und wie
     * viele?
     */
    @Test
    void vereineMeisteToreZuhause() throws IOException {

        assertEquals(Pair.of("FC Bayern München", 55), null);
        assertEquals(Pair.of("Borussia Dortmund", 39), null);
        assertEquals(Pair.of("1899 Hoffenheim", 35), null);
    }

    /**
     * V6 Welcher Verein hat die wenigsten Tore auswärts geschossen, und wie viele?
     */
    @Test
    void vereineWenigsteToreAuswaerts() throws IOException {

        assertEquals(Pair.of("Rot-Weiß Erfurt", 0), null);
    }

}
