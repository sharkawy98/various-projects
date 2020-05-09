import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static String cwd = "D:\\"; // updated if cd command is used

    public static void main(String[] args) throws IOException {
        boolean isRunning = true;

        while (isRunning) {

            System.out.print(Main.cwd + " ");
            String in = new Scanner(System.in).nextLine();

            Parser parser = new Parser();
            boolean isFound = parser.parse(in);

            if (isFound == false)
                continue;

            // handling short paths
            ArrayList<String> arguments = parser.getArgs();
            for (int i = 0; i < arguments.size(); i++) {
                String tmp = arguments.get(i);
                if (!tmp.contains(":"))
                    arguments.set(i, cwd + tmp + "\\");
            }


            switch (parser.getCmd()) {
                case "cd":
                    Terminal.cd(arguments.size() == 0 ? "" : arguments.get(0));
                    break;
                case "ls":
                    if(Parser.hasDoubleOperator == true) {
                        Terminal.roLs(arguments.get(0));
                        Parser.hasDoubleOperator = false;
                    }
                    else if(Parser.hasSingleOperator == true) {
                        Terminal.roLs2(arguments.get(0));
                        Parser.hasSingleOperator = false;
                    }
                    else {
                        Terminal.ls(arguments);
                    }
                    break;
                case "cp":
                    Terminal.cp(arguments.get(0), arguments.get(1));
                    break;
                case "cat":
                    if(Parser.hasDoubleOperator == true) {
                        Terminal.rocat(arguments);
                        Parser.hasDoubleOperator = false;
                    }
                    else if(Parser.hasSingleOperator == true) {
                        Terminal.rocat2(arguments);
                        Parser.hasSingleOperator = false;
                    }
                    else {
                        Terminal.cat(arguments);
                    }
                    break;
                case "more":
                    Terminal.more(arguments);
                    break;
                case "mkdir":
                    Terminal.mkdir(arguments);
                    break;
                case "rmdir":
                    Terminal.rmdir(arguments);
                    break;
                case "mv":
                    Terminal.mv(arguments.get(0), arguments.get(1));
                    break;
                case "rm":
                    Terminal.rm(arguments);
                    break;
                case "args":
                    Terminal.args(Parser.argsCmd);
                    break;
                case "date":
                    if(Parser.hasDoubleOperator == true) {
                        String action = ">>";
                        Terminal.rodate(arguments.get(0), action);
                        Parser.hasDoubleOperator = false;
                    }
                    else if(Parser.hasSingleOperator == true) {
                        String action = ">";
                        Terminal.rodate(arguments.get(0), action);
                        Parser.hasSingleOperator = false;
                    }
                    else {
                        String d =Terminal.date();
                        System.out.println(d);
                    }
                    break;
                case "help":
                    if(Parser.hasDoubleOperator == true) {
                        String action = ">>";
                        Terminal.rohelp(arguments.get(0), action);
                        Parser.hasDoubleOperator = false;
                    }
                    else if(Parser.hasSingleOperator == true) {
                        String action = ">";
                        Terminal.rohelp(arguments.get(0), action);
                        Parser.hasSingleOperator = false;
                    }
                    else {
                        String h = Terminal.help();
                        System.out.println(h);
                    }
                    break;
                case "pwd":
                    if(Parser.hasDoubleOperator == true) {
                        String action = ">>";
                        Terminal.ropwd(arguments.get(0), action);
                        Parser.hasDoubleOperator = false;
                    }
                    else if(Parser.hasSingleOperator == true) {
                        String action = ">";
                        Terminal.ropwd(arguments.get(0), action);
                        Parser.hasSingleOperator = false;
                    }
                    else {
                        String p =Terminal.pwd();
                        System.out.println(p);
                    }
                    break;
                case "clear":
                    Terminal.clear();
                    break;
                default: // if exit
                    isRunning = false;
            }
        }
    }
}


