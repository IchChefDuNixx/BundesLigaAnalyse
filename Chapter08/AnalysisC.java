package Chapter08;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class AnalysisC {
    public static void main(String[] args) throws IOException {
        Bundesliga bundesligaClubs = Bundesliga.loadFromResource();
        int c1GoalsMade = 0;
        int c2GoalsReceived = 0;
        int c20Score = 0;
        int c26GoalsMade = 0;
        int c26GoalsReceived = 0;
        Map<Integer, Integer> goalsAtHome = new TreeMap<>();
        int mostGoals = 0;
        String mostGoalsClub = "";
        int secondMostGoals = 0;
        String secondMostGoalsClub = "";
        int thirdMostGoals = 0;
        String thirdMostGoalsClub = "";
        Map<String, Integer> replaceIdNameHome = new TreeMap<>();
        String vereinsName = "";
        Map<Integer, Integer> goalsAway = new TreeMap<>();
        int lowestGoals0 = 0;
        String lowestGoalsClub0 = "";
        boolean wasUpdated0 = false;
        Map<String, Integer> lowestScores0 = new TreeMap<>();
        Map<String, Integer> replaceIdNameAway = new TreeMap<>();
        String vereinsName2 = "";
        int lowestGoals = 0;
        String lowestGoalsClub = "";
        boolean wasUpdated = false;
        Map<String, Integer> lowestScores = new TreeMap<>();
        for (Spiel game : bundesligaClubs.spiele) {
            // A
            if (game.getHeimID() == 1) {
                c1GoalsMade += game.getToreHeim();
            } else if (game.getGastID() == 1) {
                c1GoalsMade += game.getToreGast();
            }

            // B
            if (game.getHeimID() == 2) {
                c2GoalsReceived += game.getToreGast();
            } else if (game.getGastID() == 2) {
                c2GoalsReceived += game.getToreHeim();
            }

            // C
            if (game.getHeimID() == 20 && game.getToreHeim() > game.getToreGast()) {
                c20Score += 3;
            } else if (game.getHeimID() == 20 && game.getToreHeim() == game.getToreGast()) {
                c20Score++;
            } else if (game.getGastID() == 20 && game.getToreGast() > game.getToreHeim()) {
                c20Score += 3;
            } else if (game.getGastID() == 20 && game.getToreGast() == game.getToreHeim()) {
                c20Score++;
            }

            // D
            if (game.getHeimID() == 26) {
                c26GoalsMade += game.getToreHeim();
                c26GoalsReceived += game.getToreGast();
            } else if (game.getGastID() == 26) {
                c26GoalsMade += game.getToreGast();
                c26GoalsReceived += game.getToreHeim();
            }

            // E
            if (goalsAtHome.containsKey(game.getHeimID())) {
                goalsAtHome.replace(game.getHeimID(), goalsAtHome.get(game.getHeimID()) + game.getToreHeim());
            } else {
                goalsAtHome.put(game.getHeimID(), game.getToreHeim());
            }

            // G
            if (goalsAway.containsKey(game.getGastID())) {
                goalsAway.replace(game.getGastID(), goalsAway.get(game.getGastID()) + game.getToreGast());
            } else {
                goalsAway.put(game.getGastID(), game.getToreGast());
            }
        }

        for (Map.Entry<Integer, Integer> replaceEntry : goalsAtHome.entrySet()) {
            vereinsName = bundesligaClubs.vereine.get(replaceEntry.getKey()).getName();
            replaceIdNameHome.put(vereinsName, replaceEntry.getValue());
        }
        for (Map.Entry<Integer, Integer> replaceEntry2 : goalsAway.entrySet()) {
            vereinsName2 = bundesligaClubs.vereine.get(replaceEntry2.getKey()).getName();
            replaceIdNameAway.put(vereinsName2, replaceEntry2.getValue());
        }
        for (Map.Entry<String, Integer> gahEntry : replaceIdNameHome.entrySet()) {
            if (gahEntry.getValue() > mostGoals) {
                thirdMostGoals = secondMostGoals;
                thirdMostGoalsClub = secondMostGoalsClub;
                secondMostGoals = mostGoals;
                secondMostGoalsClub = mostGoalsClub;
                mostGoals = gahEntry.getValue();
                mostGoalsClub = gahEntry.getKey();
            } else if (gahEntry.getValue() > secondMostGoals) {
                thirdMostGoals = secondMostGoals;
                thirdMostGoalsClub = secondMostGoalsClub;
                secondMostGoals = gahEntry.getValue();
                secondMostGoalsClub = gahEntry.getKey();
            } else if (gahEntry.getValue() > thirdMostGoals) {
                thirdMostGoals = gahEntry.getValue();
                thirdMostGoalsClub = gahEntry.getKey();
            }

            // G
            if (wasUpdated == false) {
                lowestGoals = gahEntry.getValue();
                lowestGoalsClub = gahEntry.getKey();
                wasUpdated = true;
            } else if (gahEntry.getValue() < lowestGoals) {
                lowestGoals = gahEntry.getValue();
                lowestGoalsClub = gahEntry.getKey();
                lowestScores.clear();
                lowestScores.put(gahEntry.getKey(), gahEntry.getValue());
            } else if (gahEntry.getValue() == lowestGoals) {
                lowestScores.put(gahEntry.getKey(), gahEntry.getValue());
            }
        }
        for (Map.Entry<String, Integer> gawEntry : replaceIdNameAway.entrySet()) {
            // F
            if (wasUpdated0 == false) {
                lowestGoals0 = gawEntry.getValue();
                lowestGoalsClub0 = gawEntry.getKey();
                wasUpdated0 = true;
            } else if (gawEntry.getValue() < lowestGoals0) {
                lowestGoals0 = gawEntry.getValue();
                lowestGoalsClub0 = gawEntry.getKey();
                lowestScores0.clear();
                lowestScores0.put(gawEntry.getKey(), gawEntry.getValue());
            } else if (gawEntry.getValue() == lowestGoals0) {
                lowestScores0.put(gawEntry.getKey(), gawEntry.getValue());
            }
        }
        lowestGoalsClub = lowestScores0.keySet().toString();
        lowestGoalsClub = lowestScores.keySet().toString();
        System.out.println("(C1) FC Bayern München Goals Made: " + c1GoalsMade + "\n"
                + "(C2) FC Schalke 04 Goals Received: " + c2GoalsReceived + "\n"
                + "(C3) 1. FC Nürnberg Score: " + c20Score + "\n"
                + "(C4) VfL Bochum Goals (diff): Made " + c26GoalsMade + ", Received " + c26GoalsReceived + " ("
                + (c26GoalsMade - c26GoalsReceived) + ")" + "\n"
                + "(C5) Top Scores at Home: #1 " + mostGoalsClub + " (" + mostGoals + ") - #2 " + secondMostGoalsClub
                + " (" + secondMostGoals + ") - #3 " + thirdMostGoalsClub + " (" + thirdMostGoals + ")" + "\n"
                + "(C6) Lowest Score Away: " + lowestGoalsClub0 + " (" + lowestGoals0 + ")" + "\n"
                + "(C7) Lowest Scores at Home: " + lowestGoalsClub + " (" + lowestGoals + ")");
    }
}