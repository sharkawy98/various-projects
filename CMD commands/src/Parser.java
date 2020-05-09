import java.util.ArrayList;

public class Parser{
    private ArrayList<String> args; // Will be filled by arguments extracted by parse method
    private String cmd; // Will be filled by the command extracted by parse method
    public static String argsCmd; // used in the main for case of command (args)
    public static boolean hasSingleOperator = false; // redirect >
    public static boolean hasDoubleOperator = false; // redirect >>

    Parser() {
        args = new ArrayList<>();
    }

    private String[] split(String input) {  //cd"ahmed sharkawy" [cd, ahmed sharkawy]
        if (input.contains("\"")) {
            input = input.replaceAll("\\s(\")", "\"");
            return input.split("\"");
        } else if (input.contains("'")) {
            input = input.replace("\\s(')", "\"");
            return input.split("'");
        } else { // cp "f1.txt" 'games'
            input = input.replace("\"", "");
            input = input.replace("'", "");
            return input.split(" ");
        }
    }

    private boolean is_a_redirectOperatorCmd(String cmd) {
        if (cmd.equals("ls") || cmd.equals("cat") || cmd.equals("pwd") || cmd.equals("date") || cmd.equals("help")) {
            return true;
        } else {
            System.out.println("Operator: command not found");
            return false;
        }
    }

    // Returns true if it was able to parse user input correctly. Otherwise false
    // In case of success, it should save the extracted command and arguments
    // to args and cmd variables
    // It should also print error messages in case of too few arguments for a commands
    // eg. “cp requires 2 arguments”
    public boolean parse(String input) {

        if (input.contains(">>")) { // ls cat help date pwd
            hasDoubleOperator = true;
            String[] tmp = input.split(">>");
            String[] tmp2= this.split(tmp[0]);

            cmd = tmp2[0];
            for(int i = 1 ; i < tmp2.length ; i++) {
                args.add(tmp2[i]);
            }
            args.add(tmp[1].replace(" ", "")); // file name & replace space after >>

            return this.is_a_redirectOperatorCmd(cmd);
        }else if (input.contains(">")) {
            hasSingleOperator = true;
            String[] tmp = input.split(">");
            String[] tmp2= this.split(tmp[0]);

            cmd = tmp2[0];
            for(int i = 1 ; i < tmp2.length ; i++) {
                args.add(tmp2[i]);
            }
            args.add(tmp[1].replace(" ", "")); // file name & replace space after >>

            return this.is_a_redirectOperatorCmd(cmd);
        } else {
            String[] tmp = this.split(input);
            cmd = tmp[0];
            for (int i = 1; i < tmp.length; i++) {
                 args.add(tmp[i]);
            }
        }

        switch (cmd)
        {
            case "cd":
                if(args.size() > 1) {
                    System.out.println("too many arguments");
                    return false;
                }
                break;
            case "cp": case "mv":
                if(args.size() != 2) {
                    System.out.println("must take two arguments");
                    return false;
                }
                break;
            case "cat": case "more": case "mkdir":
            case "rmdir": case "rm":
                if(args.size() < 1) {
                    System.out.println("missing arguments");
                    return false;
                }
                break;
            case "args":
                if(args.size() < 1) {
                    System.out.println("missing arguments");
                    return false;
                } else if (args.size() > 1) {
                    System.out.println("too many arguments");
                    return false;
                }
                argsCmd = args.get(0);
                break;
            case "date": case "help": case "pwd": case "clear":
                if(args.size() != 0) {
                    System.out.println("take no arguments");
                    return false;
                }
                break;
            case "exit": case "ls":
                return true;
            default:
                System.out.println("command not found");
                return false;
        }

        return true;
    }


    public String getCmd() {
        return  cmd;
    }


    public ArrayList<String> getArgs() {
        return args;
    }
}