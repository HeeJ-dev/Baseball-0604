public class Game {
    public String question;

    public GuessResult guess(String guessNumber) {
        assertIllegalArgument(guessNumber);
        if(guessNumber.equals(question)){
            return new GuessResult(true, 3, 0);
        }
        return new GuessResult(false, getStrikesCount(guessNumber), getBallsCount(guessNumber));
    }

    private int getStrikesCount(String guessNumber) {
        int strikeCount = 0;
        for(int i=0; i<3; i++) {
            if(guessNumber.charAt(i) == question.charAt(i))
                strikeCount++;
        }
        return strikeCount;
    }

    private int getBallsCount(String guessNumber) {
        int totalContainCount = getTotalContainCount(guessNumber);
        return totalContainCount - getStrikesCount(guessNumber);
    }

    private int getTotalContainCount(String guessNumber) {
        int totalContainCount = 0;
        for(int i=0; i<3; i++) {
            if(question.contains(String.valueOf(guessNumber.charAt(i)))){
                totalContainCount++;
            }
        }
        return totalContainCount;
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
}
