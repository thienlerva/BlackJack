import java.util.Scanner;

public class BlackJack {

    public static void main(String[] args) {
        System.out.println("Welcome to Blackjack!");

        // Create our playing deck
        Deck playingDec = new Deck();
        playingDec.createFullDeck();
        playingDec.shuffle();

        //Create a deck for the player
        Deck playerDeck = new Deck();
        Deck dealerDeck = new Deck();

        double playerMoney = 100.00;

        Scanner userInput = new Scanner(System.in);

        // Game Loop
        while(playerMoney > 0) {
            //Play on
            //Take the players bet
            System.out.println("You have $" + playerMoney + ", how much would you like to bet?");
            double playerBet = userInput.nextDouble();
            if (playerBet > playerMoney) {
                throw new IllegalArgumentException("You cannot bet more than you have.");

            }

            boolean endRound = false;

            // Start dealing
            // Player gets two cards
            playerDeck.draw(playingDec);
            playerDeck.draw(playingDec);

            //Dealer gets two cards
            dealerDeck.draw(playingDec);
            dealerDeck.draw(playingDec);

            while(true) {
                System.out.println("Your hand:");
                System.out.println(playerDeck.toString());
                System.out.println("Your hand is valued at: " + playerDeck.cardsValue());

                //Displayer dealer hand
                System.out.println("Dealer hand: " + dealerDeck.getCard(0).toString() + " and [Hidden]");

                //What does the player want to do
                System.out.println("Would you like to (1)Hit or (2) Stand?");
                int response = userInput.nextInt();

                //They hit
                if (response == 1) {
                    playerDeck.draw(playingDec);
                    System.out.println("You draw a: " + playerDeck.getCard(playerDeck.deckSize()-1).toString());

                    //but if > 21
                    if (playerDeck.cardsValue() > 21) {
                        System.out.println("Bust. Currently valued at: " + playerDeck.cardsValue());
                        playerMoney -= playerBet;
                        endRound = true;
                        break;
                    }
                }

                if (response==2) {
                    break;
                }
            }

            //Revealing Dealer Cards
            System.out.println("Dealer Cards: " + dealerDeck.toString());
            // See if dealer has more points than player
            if ((dealerDeck.cardsValue() > playerDeck.cardsValue()) && endRound == false) {
                System.out.println("Dealer beats you!");
                playerMoney -= playerBet;
                endRound = true;
            }

            // Dealer draws at 16, stand at 17
            while (dealerDeck.cardsValue() < 17 && endRound == false) {
                dealerDeck.draw(playingDec);
                System.out.println("Dealer draws: " + dealerDeck.getCard(dealerDeck.deckSize()-1).toString());

            }

            //Display total value for dealer
            System.out.println("Dealer's Hand is valued at: " + dealerDeck.cardsValue());
            // Determined if dealer busted
            if ((dealerDeck.cardsValue() > 21) && endRound == false) {
                System.out.println("Dealer busts! You win.");
                playerMoney += playerBet;
                endRound = true;
            }

            //Determined if push
            if ((playerDeck.cardsValue() == dealerDeck.cardsValue()) && endRound == false) {
                System.out.println("Push");
                endRound = true;
            }

            if ((playerDeck.cardsValue() > dealerDeck.cardsValue()) && endRound == false) {
                System.out.println("You win the hand!");
                playerMoney += playerBet;
                endRound = true;
            } else if(endRound == false) {
                System.out.println("You lose the hand.");
                playerMoney-= playerBet;
                endRound = true;
            }

            playerDeck.moveAllToDeck(playingDec);
            dealerDeck.moveAllToDeck(playingDec);
            System.out.println("End of hand.");

        }

        System.out.println("Game over! You are out of money");



    }
}
