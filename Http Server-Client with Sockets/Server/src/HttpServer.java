import java.io.*;
import java.net.*;
import java.util.Date;

    public class HttpServer {

        public static void main(String[] args) throws IOException
        {
            System.out.println("Server is lisenting at port 4777..");
            ServerSocket ss = new ServerSocket(4777);

            // running infinite loop for getting client request
            while (true)
            {
                Socket s = null; // to be closed outside try

                try
                {
                    // socket object to receive incoming client requests
                    s = ss.accept();

                    System.out.println("A new client is connected: " + s);
                    System.out.println("A new client is connected: " + s);

                    // obtaining input and out streams
                    DataInputStream dis = new DataInputStream(s.getInputStream());
                    DataOutputStream dos = new DataOutputStream(s.getOutputStream());

                    ClientThread c = new ClientThread(s, dis, dos);

                    // Invoking the start() method
                    Thread t = new Thread(c);
                    t.start();

                }
                catch (IOException e){
                    System.err.println(e);
                    s.close();
                }
            }
        }
    }

    // ClientHandler class
    class ClientThread implements Runnable {
        private final DataInputStream dis;
        private final DataOutputStream dos;
        private final Socket s;
        private static int fileCount;
        private int fileLength;

        // Constructor
        public ClientThread(Socket s, DataInputStream dis, DataOutputStream dos) {
            this.s = s;
            this.dis = dis;
            this.dos = dos;
        }

        @Override
        public void run() {
            String received;

            // run till the client close the connection
            while (true) {
                try {
                    // Ask user what he wants
                    dos.writeUTF("\nEnter your link..\n"
                            + "Exit to close connection");

                    // receive the link from client
                    received = dis.readUTF();

                    if (received.toLowerCase().equals("exit")) {
                        System.out.println("Client sends exit...");
                        this.s.close();
                        System.out.println("Connection closed\n");
                        break;
                    }

                    // the returned file of the downloaded web page
                    String filePath = DownloadWebPage(received);

                    dos.writeUTF("HTTP/1.1 200 OK\n"
                            + "Date: " + new Date() + "\n"
                            + "Content-type: text/html\n"
                            + "Content-length: " + fileLength + "\n"
                            + "Connection: closed\n"
                    );

                    dos.writeUTF(filePath);

                } catch (IOException e) {
                }
            }
            try {
                // closing resources
                this.dis.close();
                this.dos.close();
            } catch (IOException e) {
                System.err.println(e);
            }
        }

        // download the web page locally on the root of the http server
        private String DownloadWebPage(String webpage) {
            try {
                // Create URL object to connect to the web page
                URL url = new URL(webpage);
                InputStream is = url.openStream(); // throws the exception
                BufferedReader br = new BufferedReader(new InputStreamReader(is));

                // Enter filename in which you want to download
                FileWriter fileWriter = new FileWriter("index" + (++fileCount) + ".html");

                // read each line from stream till end
                String temp;
                while ((temp = br.readLine()) != null) {
                    fileWriter.write(temp);
                }

                br.close();
                fileWriter.close();
                System.out.println("\nFile " + fileCount + " downloaded.");

                // set the file length to send with the http response
                File f = new File("index" + (fileCount) + ".html");
                fileLength = (int) f.length();

                return "C:\\Users\\SHARKAWY\\Desktop\\Done\\Networks Project\\Server\\index" + (fileCount) + ".html";
            }
            // Exceptions if the link isn't valid or 404 not found
            catch (IOException ie) {
                System.err.println(ie);
                System.out.println();
                return "error";
            }
        }
    }
