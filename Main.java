public class Main {

    public static void main(String[] args) {
        int numberOfConnectedDevices = 5;
        Ring ring = new Ring(numberOfConnectedDevices);
        for (int i = 'a'; i < 'a'+numberOfConnectedDevices; i++) {
            ring.sendMessage('a', (char) i, "message");
        }
    }
}

