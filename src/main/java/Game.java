public class Game {
    public String question;

    public GuessResult guess(String guessNumber) {
        assertIllegalArgument(guessNumber);
        if(guessNumber.equals(question)){
            return new GuessResult(true, 3, 0);
        }
        else if(getStrikesCount(guessNumber) == 2) {
            if(getBallsCount(guessNumber) == 0 ) {
                return new GuessResult(false, 2, 0);
            }
            else {
                return new GuessResult(false, 2, 1);
            }
        }
        else {
            return new GuessResult(false, 0, 0);
        }
    }

    private void assertIllegalArgument(String guessNumber) {
        if(guessNumber == null) {
            throw new IllegalArgumentException();
        }

        if(guessNumber.length() != 3) {
            throw new IllegalArgumentException();
        }

        for(char number : guessNumber.toCharArray()) {
            if(number < '0' || number > '9') {
                throw new IllegalArgumentException();
            }
        }

        if(isDuplicatedNumber(guessNumber)) {
            throw new IllegalArgumentException();
        }
    }

    private boolean isDuplicatedNumber(String guessNumber) {
        return guessNumber.charAt(0) == guessNumber.charAt(1)
                || guessNumber.charAt(0) == guessNumber.charAt(2)
                || guessNumber.charAt(1) == guessNumber.charAt(2);
    }

    private int getStrikesCount(String guessNumber) {
        int count = 0;
        if(guessNumber.charAt(0) == question.charAt(0)) count++;
        if(guessNumber.charAt(1) == question.charAt(1)) count++;
        if(guessNumber.charAt(2) == question.charAt(2)) count++;
        return count;
    }

    private int getBallsCount(String guessNumber) {
        int count = 0;
        if(guessNumber.charAt(0) == question.charAt(1)
        || guessNumber.charAt(0) == question.charAt(2))
            count++;
        if(guessNumber.charAt(1) == question.charAt(0)
        || guessNumber.charAt(1) == question.charAt(2))
            count++;
        if(guessNumber.charAt(2) == question.charAt(0)
        || guessNumber.charAt(2) == question.charAt(1))
            count++;

        return count;
    }
}
