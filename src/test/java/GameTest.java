import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    Game game;
    @BeforeEach
    void setUp() {
        game = new Game();
    }

    @Test
    void createGame() {
        assertNotNull(game);
    }
    
    private void assertIllegalArgument(String guessNumber) {
        try {
            game.guess(guessNumber);
            fail();
        } catch (IllegalArgumentException e){

        }
    }

    @Test
    void throwIllegalArgumentExceptionInvalidInput() {
        assertIllegalArgument(null);
        assertIllegalArgument("12");
        assertIllegalArgument("1234");
        assertIllegalArgument("12s");
        assertIllegalArgument("121");
    }

    private void generateQuestion(String questionNumber) {
        game.question = questionNumber;
    }

    @Test
    void returnSolvedResultIfMatchedNumber() {
        generateQuestion("123");
        assertMatchedNumber(game.guess("123"), true, 3, 0);
    }

    @Test
    void returnSolvedResultIfUnMatchedNumber() {
        generateQuestion("123");
        assertMatchedNumber(game.guess("456"), false, 0, 0);
    }

    @Test
    void returnSolvedResultIf2Strkies0Ball() {
        generateQuestion("123");
        assertMatchedNumber(game.guess("125"), false, 2, 0);
    }
    @Test
    void returnSolvedResultIf0Ball2Strkies() {
        generateQuestion("123");
        assertMatchedNumber(game.guess("523"), false, 2, 0);
    }
    @Test
    void returnSolvedResultIf1Strkie0Ball1Strkie() {
        generateQuestion("123");
        assertMatchedNumber(game.guess("143"), false, 2, 0);
    }

    private void assertMatchedNumber(GuessResult result, boolean solved, int strikes, int balls) {
        assertThat(result).isNotNull();
        assertThat(result.isSolved()).isEqualTo(solved);
        assertThat(result.getStrikes()).isEqualTo(strikes);
        assertThat(result.getBalls()).isEqualTo(balls);
    }
}