public enum Suit {

    HEARTS("Hearts"),
    SPADES("Spades"),
    DIAMONDS("Diamonds"),
    CLUBS("Clubs");

    private final String suitCode;

    private Suit(String suitCode) {
        this.suitCode = suitCode;
    }

    public String printSuitCode() {
        return this.suitCode;
    }
}
