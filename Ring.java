public class Ring implements Network {

    public Vertex[] network;
    private int numberOfVertices;

    public Ring(int numberOfVertices) {
        this.numberOfVertices = numberOfVertices;
        createModel();
    }

    @Override
    public void createModel() {
        network = new Vertex[numberOfVertices];
        for (int i = 0, name = 'a'; i < numberOfVertices; i++, name++) {
            network[i] = new Vertex((char)name);
        }
        for (int i = 0; i < network.length; i++) {
            if (i==0) {
                network[i].addAjaceny(network[(network.length)-1]);
                network[i].addAjaceny(network[i+1]);
            } else if (i == network.length-1) {
                network[i].addAjaceny(network[0]);
                network[i].addAjaceny(network[i-1]);
            } else {
                network[i].addAjaceny(network[i+1]);
                network[i].addAjaceny(network[i-1]);
            }
        }
    }

    @Override
    public Vertex find(char sourceName, char destinationName) {
        Vertex source = null, destination = null;
        for (Vertex v: network) {
            if(v.getName() == sourceName) {
                source = v;
            }
            if(v.getName() == destinationName) {
                destination = v;
            }
        }
        if (source != null && destination != null) {
            source.setVisited(true);
            destination = depthFirstSearch(source, destination);
            for (Vertex v: network) {
                v.setVisited(false);
            }
            return destination;
        }
        throw new VertexNotFoundException("Could not find vertex" + destinationName);
    }

    private Vertex depthFirstSearch(Vertex source, Vertex destination) {
        System.out.println(source + " has been visited when sending the message to " + destination);
        if(source.equals(destination)) {
            return source;
        }
        for (Vertex adjacency : source.getAdjacencies()) {
            if (!adjacency.isVisited()) {
                adjacency.setVisited(true);
                return depthFirstSearch(adjacency, destination);
            }
        }
        throw new VertexNotFoundException("Could not find vertex");

    }

    @Override
    public void sendMessage(char sourceName, char destinationName, String message) {
        Vertex messageSentTo = find(sourceName, destinationName);
        messageSentTo.addNewMessageSent(message + " (" + sourceName + " sent a message to " + sourceName + ").");
        messageSentTo.addNewMessageRecieved(message + " ( " + destinationName + " received a message from " + sourceName + ").");
        System.out.println("Message successfully sent from " + sourceName + " to " + destinationName);
    }
}
