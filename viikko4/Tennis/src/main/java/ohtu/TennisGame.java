package ohtu;

public class TennisGame {

    private int score_player1 = 0;
    private int score_player2 = 0;
    private String player1Name;
    private String player2Name;
    private String[] scores = {"Love", "Fifteen", "Thirty", "Forty"};

    public TennisGame(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public void wonPoint(String playerName) {
        if ("player1".equals(playerName)) {
            score_player1 += 1;
        } else {
            score_player2 += 1;
        }
    }

    public String getScore() {

        if (score_player1 == score_player2) {
            return evenScore();
        } else if (score_player1 >= 4 || score_player2 >= 4) {
            return bigScore();
        }

        return scores[score_player1] + "-" + scores[score_player2];
    }

    private String evenScore() {

        if (score_player1 <= 3) {
            return scores[score_player1] + "-All";
        }
        return "Deuce";
    }

    private String bigScore() {

        if (Math.abs(score_player1 - score_player2) == 1) {
            return "Advantage " + getLeadingPlayer();
        }
        
        return "Win for " + getLeadingPlayer();

    }

    private String getLeadingPlayer() {

        if (score_player1 - score_player2 > 0) {
            return player1Name;
        }
        return player2Name;
    }
}
