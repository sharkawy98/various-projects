public class Router {
    private static boolean[] isConnected;
    private static int nConnections;

    public static Semaphore connection; //use it to use semaphore in run of each device thread

    Router(int x) {
        nConnections = x;
        connection = new Semaphore(nConnections);
        isConnected = new boolean[nConnections];
    }


    public static synchronized void occupy(Device d) {
        for (int i = 0; i < nConnections; i++) {
            if (isConnected[i] == false) {
                d.takenConnection = i+1;
                System.out.println("Connection " + d.takenConnection + ": " + d.getName() + d.connect());
                isConnected[i] = true;
                break; // device searched for place and break
            }
        }
    }


    public static synchronized void leave(Device d) {
        System.out.println("Connection " + d.takenConnection + ": " + d.getName() + d.disconnect());
        isConnected[d.takenConnection - 1] = false;
    }

}
