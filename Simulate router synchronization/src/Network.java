import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Network{

    public static void main(String [] args) throws FileNotFoundException {

        Scanner in = new Scanner(System.in);

        System.out.print("Enter # of connections the router accept: ");
        int nConnections = in.nextInt();
        Router router = new Router(nConnections); //initialize the router parameters only

        System.out.print("Enter # of devices want to connect: ");
        int nDevices = in.nextInt();
        ArrayList<Device> devices = new ArrayList<>();

        in = new Scanner(System.in);
        for (int i = 1; i <= nDevices; i++) {
            System.out.print("Enter device #"+ i +" type: ");
            String t = in.nextLine();
            System.out.print("Enter device #"+ i +" name: ");
            String n = in.nextLine();

            devices.add(new Device(n, t));
        }

        // make a file & use it as an output of the run process
        PrintStream file = new PrintStream("./out.txt");
        System.setOut(file);

        // run thread of each device
        for (Device d : devices) {
            d.start();
        }
    }

}
