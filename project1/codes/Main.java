public class Main {
    /**
     * get the random int between [a, b].
     * @param a the lower bound of range
     * @param b the upper bound of range
     * @return a random int between [a, b]
     */
    static int rand(int a, int b) {
        return (int)(Math.random() * (b - a + 2)) + a;
    }
    static Player Start(Player player1, Player player2) {
        while (player1.hasChoice() && player2.hasChoice()) {
            boolean roundResult = true;
            // in each round:
            Card lastCard = new Card(0, null);
            while (player1.hasChoice() && player1.hasChoice()) {
                lastCard = player1.playCard(lastCard);
                if (lastCard == null) {
                    roundResult = false;
                    break;
                }
                System.out.print(player1.name + " plays the card:");
                lastCard.Print(); System.out.println();
                if (!player1.hasChoice()) break;
                lastCard = player2.playCard(lastCard);
                if (lastCard == null) {
                    roundResult =  true;
                    break;
                }
                System.out.print(player2.name + " plays the card:");
                lastCard.Print(); System.out.println();
                if (!player2.hasChoice()) break;
            }
            if (player1.hasChoice() == false) {
                System.out.println(player1.name + " firstly plays all his cards.");
                return player1;
            }
            if (player2.hasChoice() == false) {
                System.out.println(player2.name + " firstly plays all his cards.");
                return player2;
            }
            if (roundResult == false) {
                Player p = player1;
                player1 = player2;
                player2 = p;
            }
        }
        if (player1.hasChoice() == false) {
            System.out.println(player1.name + " firstly plays all his cards.");
            return player1;
        } else {
            System.out.println(player2.name + " firstly plays all his cards.");
            return player2;
        }
    }
    public static void main(String[] args) {
        Card[] cards = new Card[54];
        int n = 0;
        for (int i = 1; i <= 13; ++i) {
            for (int j = 0; j < 4; ++j) {
                cards[n] = new Card(i, COLOR.values()[j]);
                ++n;
            }
        }
        cards[52] = new Card(14, COLOR.Joker1);
        cards[53] = new Card(15, COLOR.Joker2);
        Player player, computer;
        player = new Player("player");
        computer = new Player("computer");
        int starter = 1;
        Card startCard = new Card(3, COLOR.SPADE);
        for (int i = 0; i < 5; ++i) {
            int pos = rand(0, n);
            player.cards[i] = cards[pos];
            cards[pos] = cards[n];
            --n;
            if (player.cards[i] == startCard) starter = 0;
            pos = rand(0, n);
            computer.cards[i] = cards[pos];
            cards[pos] = cards[n];
            --n;
            if (computer.cards[i] == startCard) starter = 1;
        }
        player.sort();
        computer.sort();
        System.out.print("The cards player have:   "); player.Print();
        System.out.print("The cards computer have: "); computer.Print();
        Player winner;
        if (starter == 0) winner = Start(player, computer);
        else winner = Start(computer, player);
        System.out.println("The winner is " + winner.name);
    }
}
enum COLOR {SPADE, HEART, DIAMOND, CLUB, Joker1, Joker2};
class Card {
    int number;
    COLOR color;

    Card(int number, COLOR color) {
        this.number = number;
        this.color = color;
    }

    private static boolean CompareColor(COLOR col1, COLOR col2) {
        if (col1 == COLOR.SPADE) return true;
        if (col2 == COLOR.SPADE) return false;
        if (col1 == COLOR.HEART) return true;
        if (col2 == COLOR.HEART) return false;
        if (col1 == COLOR.DIAMOND) return true;
        if (col2 == COLOR.DIAMOND) return false;
        if (col1 == COLOR.CLUB) return true;
        if (col2 == COLOR.CLUB) return false;
        return true;
    }

    public void Print() {
        System.out.printf("(%d, %s)", this.number, this.color);
    }

    /**
     * Compare two cards.
     * @param c1 Card to compare
     * @param c2 Card to compare
     * @return true if c1 >= c2;false if c1 < c2
     */
    public static boolean Compare(Card c1, Card c2) {
        if (c1.color == COLOR.Joker2) return true;
        if (c2.color == COLOR.Joker2) return false;
        if (c1.color == COLOR.Joker1) return true;
        if (c2.color == COLOR.Joker2) return false;
        if (c1.number != c2.number) return c1.number > c2.number;
        else return CompareColor(c1.color, c2.color);
    }
}
class Player {
    Card[] cards;
    boolean[] hasPlayed;
    String name;
    Player(String name) {
        this.cards = new Card[5];
        this.hasPlayed = new boolean[5];
        for (int i = 0; i < 5; ++i) this.hasPlayed[i] = false;
        this.name = name;
    }

    /**
     * sort the cards the player has in increasing order
     */
    public void sort() {
        for (int i = 0; i < 5; ++i) {
            for (int j = i + 1; j < 5; ++j) {
                if (Card.Compare(cards[i], cards[j])) {
                    Card temp = cards[i];
                    cards[i] = cards[j];
                    cards[j] = temp;
                }
            }
        }
    }
    public boolean hasChoice() {
        for (int i = 0; i < 5; ++i) {
            if (hasPlayed[i] == false) return true;
        }
        return false;
    }
    /**
     * Find the smallest Card player has which is bigger than Card c.
     * @param c The Card need to bigger than.
     * @return the smallest Card player has which is bigger than Card c.
     */
    public Card playCard(Card c) {
        for (int i = 0; i < 5; ++i) {
            if (!hasPlayed[i] && Card.Compare(cards[i], c)) {
                hasPlayed[i] = true;
                return cards[i];
            }
        }
        return null;
    }
    public Card playCard() {
        for (int i = 0; i < 5; ++i) {
            if (!hasPlayed[i]) {
                hasPlayed[i] = true;
                return cards[i];
            }
        }
        return null;
    }
    public void Print() {
        for (int i = 0; i < 5; ++i) {
            cards[i].Print();
            System.out.print(' ');
        }
        System.out.println();
    }
}