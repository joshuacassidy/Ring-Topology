public class Main {

    public static void main(String[] args) {
        Ring ring = new Ring(5);
        for (int i = 'a'; i < 'f'; i++) {
            ring.sendMessage('a', (char) i, "message");
        }
    }
}