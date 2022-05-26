package Chapter08;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class AnalysisG {
  public static void main(String[] args) throws IOException {
    System.out.println("Hier ein Ausschnitt der Daten der Bundesliga Saison 2017/18");

    Bundesliga bundesligaInstanz = Bundesliga.loadFromResource();
    double totalGoals = 0;
    int totalMatches = 0;
    double goalAvg = 0;
    for (Spiel game : bundesligaInstanz.spiele) {
      totalMatches++;
      totalGoals += game.getToreHeim() + game.getToreGast();
    }
    goalAvg = totalGoals / totalMatches;
    System.out.println("(G1) Matches: " + totalMatches + ", Goals: " + totalGoals + ", Avg: " + goalAvg);

    int firstLeagueTotalMatches = 0;
    double firstLeagueTotalGoals = 0;
    double firstLeagueGoalAvg = 0;
    List<String> vereinsNamen = new LinkedList<>();
    Map<Integer, String> firstLeagueVereine = new TreeMap<>();
    Map<Integer, String> secondLeagueVereine = new TreeMap<>();
    Map<Integer, String> thirdLeagueVereine = new TreeMap<>();
    int counter = 0;
    for (Verein team : bundesligaInstanz.vereine.values()) {
      counter++;
      vereinsNamen.add(team.getName());
      if (team.getLiga() == 1) {
        firstLeagueVereine.put(counter, team.getName());
      } else if (team.getLiga() == 2) {
        secondLeagueVereine.put(counter, team.getName());
      } else {
        thirdLeagueVereine.put(counter, team.getName());
      }
    }
    for (Spiel FLgame : bundesligaInstanz.spiele) {
      if (firstLeagueVereine.containsKey(FLgame.getHeimID()) == true
          && firstLeagueVereine.containsKey(FLgame.getGastID()) == true) {
        firstLeagueTotalMatches++;
        firstLeagueTotalGoals += FLgame.getToreHeim() + FLgame.getToreGast();
      }
    }
    firstLeagueGoalAvg = firstLeagueTotalGoals / firstLeagueTotalMatches;
    System.out.println("(G2) 1st League Matches: " + firstLeagueTotalMatches + ", Goals: " + firstLeagueTotalGoals
        + ", Avg: " + firstLeagueGoalAvg);

    int secondLeagueTotalMatches = 0;
    double secondLeagueTotalGoals = 0;
    double secondLeagueDailyAvg = 0;
    List<Integer> matchDays = new LinkedList<>();
    List<Integer> firstLeagueMatchDays = new LinkedList<>();
    List<Integer> secondLeagueMatchDays = new LinkedList<>();
    List<Integer> thirdLeagueMatchDays = new LinkedList<>();
    for (Spiel game : bundesligaInstanz.spiele) {
      if (!matchDays.contains(game.getSpieltag())) {
        matchDays.add(game.getSpieltag());
      }
      if (firstLeagueVereine.containsKey(game.getHeimID()) && firstLeagueVereine.containsKey(game.getGastID())) {
        if (!firstLeagueMatchDays.contains(game.getSpieltag())) {
          firstLeagueMatchDays.add(game.getSpieltag());
        }
      } else if (secondLeagueVereine.containsKey(game.getHeimID())
          && secondLeagueVereine.containsKey(game.getGastID())) {
        if (!secondLeagueMatchDays.contains(game.getSpieltag())) {
          secondLeagueMatchDays.add(game.getSpieltag());
        }
      } else {
        if (!thirdLeagueMatchDays.contains(game.getSpieltag())) {
          thirdLeagueMatchDays.add(game.getSpieltag());
        }
      }
      if (secondLeagueVereine.containsKey(game.getHeimID())
          && secondLeagueVereine.containsKey(game.getGastID())) {
        secondLeagueTotalMatches++;
        secondLeagueTotalGoals += game.getToreHeim() + game.getToreGast();
      }
    }
    // int totalMatchDays = matchDays.size();
    // int firstLeagueTotalMatchdays = firstLeagueMatchDays.size();
    int secondLeagueTotalMatchDays = secondLeagueMatchDays.size();
    // int thirdLeagueTotalMatchDays = thirdLeagueMatchDays.size();
    secondLeagueDailyAvg = secondLeagueTotalGoals / secondLeagueTotalMatchDays;
    System.out.println("(G3) 2nd League Matches: " + secondLeagueTotalMatches + ", Goals: " + secondLeagueTotalGoals
        + ", Daily Avg: " + secondLeagueDailyAvg);

    double afternoonGoals = 0;
    int afternoonCounter = 0;
    double eveningGoals = 0;
    int eveningCounter = 0;
    String tempString = "";
    int tempInt = 0;
    for (Spiel game : bundesligaInstanz.spiele) {
      if (game.getUhrzeit().matches("15:30:00")) {
        afternoonCounter++;
        afternoonGoals += game.getToreHeim() + game.getToreGast();
      } else {
        tempString = game.getUhrzeit().replaceAll(":", "");
        tempInt = Integer.parseInt(tempString);
        if (tempInt > 153000) {
          eveningCounter++;
          eveningGoals += game.getToreHeim() + game.getToreGast();
        }
      }
    }
    System.out.println("(G4) Afternoon Goals: " + afternoonGoals + ", Evening Goals: " + eveningGoals + ", Avg: ("
        + afternoonGoals / afternoonCounter + " - " + eveningGoals / eveningCounter + ")");

    int thirdLeagueTotalMatches = 0;
    double thirdLeagueGoalsHome = 0;
    double thirdLeagueGoalsAway = 0;
    Map<Integer, Integer> thirdLeagueMatchesHome = new TreeMap<>();
    Map<Integer, Integer> thirdLeagueMatchesAway = new TreeMap<>();
    Map<Integer, Integer> clubHomeGoals = new TreeMap<>();
    Map<Integer, Integer> clubAwayGoals = new TreeMap<>();
    int thirdLeagueOffset = firstLeagueVereine.size() + secondLeagueVereine.size() + 1;
    for (Spiel game : bundesligaInstanz.spiele) {
      if (thirdLeagueVereine.containsKey(game.getHeimID()) && thirdLeagueVereine.containsKey(game.getGastID())) {
        thirdLeagueTotalMatches++;
        if (clubHomeGoals.containsKey(game.getHeimID())) {
          clubHomeGoals.replace(game.getHeimID(), clubHomeGoals.get(game.getHeimID()) + game.getToreHeim());
          thirdLeagueMatchesHome.replace(game.getHeimID(), thirdLeagueMatchesHome.get(game.getHeimID()) + 1);
        } else {
          clubHomeGoals.put(game.getHeimID(), game.getToreHeim());
          thirdLeagueMatchesHome.put(game.getHeimID(), 1);
        }
        if (clubAwayGoals.containsKey(game.getGastID())) {
          clubAwayGoals.replace(game.getGastID(), clubAwayGoals.get(game.getGastID()) + game.getToreGast());
          thirdLeagueMatchesAway.replace(game.getGastID(), thirdLeagueMatchesAway.get(game.getGastID()) + 1);
        } else {
          clubAwayGoals.put(game.getGastID(), game.getToreGast());
          thirdLeagueMatchesAway.put(game.getGastID(), 1);
        }
      }
    }

    List<Integer[]> homeAwayGoals = new LinkedList<>();
    for (int i = 0; i < thirdLeagueVereine.size(); i++) {
      homeAwayGoals.add(new Integer[3]);
    }
    for (Map.Entry<Integer, String> mapEntry : thirdLeagueVereine.entrySet()) {
      homeAwayGoals.set(mapEntry.getKey() - thirdLeagueOffset,
          new Integer[] { mapEntry.getKey(), clubHomeGoals.get(mapEntry.getKey()),
              clubAwayGoals.get(mapEntry.getKey()) });
    }
    for (Integer[] intArray : homeAwayGoals) {
      thirdLeagueGoalsHome += intArray[1];
      thirdLeagueGoalsAway += intArray[2];
    }
    int moreGoalsHomeThanAway = 0;
    int moreGoalsAwayThanHome = 0;
    int sameGoalsHomeAway = 0;
    for (Integer[] intArray2 : homeAwayGoals) {
      double avgGoalHome = ((double) intArray2[1]) / (thirdLeagueMatchesHome.get(intArray2[0]));
      double avgGoalAway = ((double) intArray2[2]) / (thirdLeagueMatchesAway.get(intArray2[0]));
      if (avgGoalHome > avgGoalAway) {
        moreGoalsHomeThanAway++;
      } else if (avgGoalHome < avgGoalAway) {
        moreGoalsAwayThanHome++;
      } else if (avgGoalHome == avgGoalAway) {
        sameGoalsHomeAway++;
      } else {
        System.out.println("error in more/same goals");
      }
    }

    System.out.println("(G5) 3rd League Matches: " + thirdLeagueTotalMatches + ", Goals Home: " + thirdLeagueGoalsHome
        + ", Goals Away: " + thirdLeagueGoalsAway + ", Clubs with more Goals at Home: " + moreGoalsHomeThanAway
        + ", Clubs with more Goals Away: " + moreGoalsAwayThanHome + ", Clubs with the same amount: "
        + sameGoalsHomeAway);

  }
}
