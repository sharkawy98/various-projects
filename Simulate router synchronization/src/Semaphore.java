public class Semaphore {
    private int connQueue;

    Semaphore(int nConnections){ connQueue = nConnections; }


    public synchronized void P(Device d){
        connQueue--;
        if(connQueue<0) {
            try {
                System.out.println(d.getName() + " arrived and waiting");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        else {
            System.out.println(d.getName() + " arrived");
        }
    }


    public synchronized void V() {
        connQueue++;
        if (connQueue <= 0)
            notify();
    }

}
