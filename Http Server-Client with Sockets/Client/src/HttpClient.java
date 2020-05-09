import java.io.*; 
import java.net.*; 
import java.util.Scanner; 
import java.awt.Desktop;
import java.nio.file.Files;
import java.nio.file.Paths;

public class HttpClient {

    public static void main(String[] args)
	{ 
		try { 
			
            Scanner scn = new Scanner(System.in); 
            
			// establish the connection with server port 4777 
			Socket s = new Socket("localhost", 4777); 
	
			// to store input and output streams of the sockets
			DataInputStream dis = new DataInputStream(s.getInputStream()); 
			DataOutputStream dos = new DataOutputStream(s.getOutputStream()); 
	
			// run till client close connection
			while (true) 
			{ 
				System.out.println(dis.readUTF()); 
				String tosend = scn.nextLine(); 
				dos.writeUTF(tosend);
				
				// If client sends exit,close this connection 
				// and then break from the while loop 
				if(tosend.toLowerCase().equals("exit")) 
				{ 
					System.out.println("Closing this connection with: " + s.getPort()); 
					s.close(); 
					System.out.println("Connection closed"); 
					break; 
				} 
                
                // the respons of the http server
                String responseHeader = dis.readUTF();
				String responseFile = dis.readUTF();  
                
                if(responseFile.equals("error")){
                    System.err.println("Enter a valid link !\n");
                    continue;
                }
                
                System.out.println("\nHeader content:");
                System.out.println(responseHeader);
                
                // get the content of the file to display in console
                // return list the get(0)= 1st index in list [our file]
                String content = Files.readAllLines(Paths.get(responseFile)).get(0); 
                
                System.out.println("File content:");
                System.out.println(content);
                
                // in single func
                System.out.print("Do you want to open the file in browser (y/n): ");
                String choice = scn.nextLine();
                if(choice.equals("y"))
                    openBrowser(responseFile);
			} 
            
			// closing resources 
			scn.close(); 
			dis.close(); 
			dos.close(); 
		}catch(IOException e){
            System.err.println(e);
		} 
	}

    //open the browser
    static private void openBrowser(String path) throws IOException{
        File htmlFile = new File(path);
        Desktop desktop = Desktop.getDesktop();
        if(htmlFile.exists()) desktop.open(htmlFile);
    }
}