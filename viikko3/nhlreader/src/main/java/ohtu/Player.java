
package ohtu;

public class Player implements Comparable<Player> {
    private String name;
    private String nationality;
    private int assists;
    private int goals;
    private int penalties;
    private String team;
    private int games;

    public void setName(String name, String nationality, int assists,
        int goals, int penalties, String team, int games) {
        this.name = name;
        this.nationality = nationality;
        this.assists = assists;
        this.goals = goals;
        this.penalties = penalties;
        this.team = team;
        this.games = games;
    }

    public String getName() {
        return name;
    }

    public String getNationality() {
        return nationality;
    }

    public int getAssists() {
        return assists;
    }

    public int getGoals() {
        return goals;
    }

    public int getPenalities() {
        return penalties;
    }

    public String getTeam() {
        return team;
    }

    public int getGames() {
        return games;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.name);
        for (int i = 0; i < 22 - name.length(); i++) {
            sb.append(" ");
        }
        sb.append(this.team + "   ");
        String ass = Integer.toString(this.assists);
        String go = Integer.toString(goals);
        String total = Integer.toString(goals+assists);
        sb.append(ass);
        for (int i = 0; i < 3 - ass.length(); i++) {
            sb.append(" ");
        }
        sb.append("+");
        for (int i = 0; i < 3 - go.length(); i++) {
            sb.append(" ");
        }
        sb.append(go);
        sb.append(" =");
        for (int i = 0; i < 3 - total.length(); i++) {
            sb.append(" ");
        }
        sb.append(total);
        return sb.toString();
    }
    
    @Override
    public int compareTo(Player b) {
        return (b.getAssists() + b.getGoals()) - (this.getAssists() + this.getGoals());
    }

      
}
