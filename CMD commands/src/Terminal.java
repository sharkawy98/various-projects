import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;
import java.util.stream.Stream;

public class Terminal{

    static enum commands {clear,cd,ls,cp,mv,rm,mkdir,rmdir,cat,more,pwd,args,date,help,exit}


    public static void cd(String newPath) {
        if (newPath == "")
            Main.cwd = "D:\\";
        else {
            if (Files.isDirectory(Paths.get(newPath))) {
                Main.cwd = newPath;
            } else {
                System.out.println("Not a directory");
            }
        }
    }

    public static void cp(String sourcePath, String destinationPath ) {
        Path source = Paths.get(sourcePath);
        Path dest = Paths.get(destinationPath);

        try {
            if(Files.isDirectory(dest))
                Files.copy(source, dest.resolve(source.getFileName()), StandardCopyOption.REPLACE_EXISTING);
            else
                Files.copy(source, dest, StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            System.out.println("No such file or directory");
        }
    }

    public static void mv(String sourcePath, String destinationPath) {
        Path source = Paths.get(sourcePath);
        Path dest = Paths.get(destinationPath);

        try {
            if(Files.isDirectory(dest))
                Files.move(source, dest.resolve(source.getFileName()), StandardCopyOption.REPLACE_EXISTING);
            else
                Files.move(source, dest, StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            System.out.println("No such file or directory");
        }
    }

    public static void rm(ArrayList<String> paths) {
        for (String path : paths) {
            Path file = Paths.get(path);

            if (Files.isDirectory(file)) {
                System.out.println("Is a directory");
                return;
            }

            try {
                Files.deleteIfExists(file);
            } catch (IOException e) {
                System.out.println("No such file or directory");
            }
        }
    }

    public static void ls(ArrayList<String> paths) throws IOException {
        if (paths.size() == 0) {
            System.out.println("----[" + Main.cwd +"]----");

            Stream<Path> content = Files.list(Paths.get(Main.cwd));
            content.forEach(s -> System.out.println(s.getFileName()));

            return;
        }

        for (String path : paths) {
            System.out.println("----[" + path + "]----");

            Path dir = Paths.get(path);

            try {
                Stream<Path> content = Files.list(dir);

                content.forEach(s -> System.out.println(s.getFileName()));
            } catch (Exception e) {
                System.out.println("No such file or directory");
            }
        }
    }

    public static void cat(ArrayList<String> paths) {
        for (String path : paths) {
            Path dir = Paths.get(path);

            try {
                Stream<String> content = Files.lines(dir);

                content.forEach(s -> System.out.println(s));
            } catch (Exception e) {
                System.out.println("No such file or directory");
            }
        }
    }

    public static void more(ArrayList<String> paths) {
        for (String path : paths) {
            Path dir = Paths.get(path);

            try {
                Stream<String> content = Files.lines(dir);
                ArrayList<String> tmp = new ArrayList<>();
                content.forEach(s -> tmp.add(s));

                Scanner in = new Scanner(System.in);
                for (int i = 1; i <= tmp.size(); i++) {
                    System.out.println(tmp.get(i-1));
                    if (i % 5 == 0) {
                        System.out.print("--More--");
                        in.nextLine();
                    }
                }
            } catch (Exception e) {
                System.out.println("No such file or directory");
            }
        }
    }

    public static void mkdir(ArrayList<String> paths) throws IOException {
        for (String path : paths) {
            Path dir = Paths.get(path);

            if (Files.exists(dir))
                System.out.println("File exists");
            else
                Files.createDirectory(dir);
        }
    }

    public static void rmdir(ArrayList<String> paths) {
        for (String path : paths) {
            Path dir = Paths.get(path);

            try {
                Files.deleteIfExists(dir);
            } catch (IOException e) {
                System.out.println("Directory not empty");
            }
        }
    }


    public static void args(String cmd) {
        int idx = commands.valueOf(cmd).ordinal();

        switch (idx) {
            case 1:
                System.out.println("Number of args is 1: Source Path");break;
            case 2:
                System.out.println("Any number of args: Source Path");break;
            case 3: case 4:
                System.out.println("Number of args is 2: Source Path, Destination Path");break;
            case 5:
                System.out.println("Any number of args: The file to be removed");break;
            case 6:
                System.out.println("Any number of args: The new Directory Path");break;
            case 7:
                System.out.println("Any number of args: The Directory to be removed");break;
            case 8: case 9:
                System.out.println("Any number of args: The File Directories");break;
            case 11:
                System.out.println("Number of args is 1: Command Instruction");break;
            case 0: case 10: case 12: case 13: case 14:
                System.out.println("No Arguments for this command");break;
        }
    }

    public static String date() {
        String timeStamp = new SimpleDateFormat("dd/MM/yyyy  HH:mm:ss").
                format(Calendar.getInstance().getTime());
        return timeStamp;
    }


    public static String pwd() {
        return "Current Directory is " + Main.cwd;
    }

    public static void clear() {
        String tmp = "\n";
        tmp = tmp.repeat(50);
        System.out.println(tmp);
    }

    public static String help() {
        String help ="clear , No Arguments for this command ,This command can be called to clear the current\r\n" +
                "terminal screen and it can be redirected to clear the screen of some other terminal \n" +
                "------------------------------------------------------------------------------------------------------------------------\n"+
                "cd , Number of args is 1: Source Path ,This command changes the current directory to\r\n" +
                "another one. \n" +
                "------------------------------------------------------------------------------------------------------------------------\n"+
                "ls , Any number of args: Source Path , Destination Path , + ls is a command to list computer files in Unix and Unix-like \r\n"+
                "operating systems \n " +
                "------------------------------------------------------------------------------------------------------------------------\n"+
                "cp , Number of args is 2: Source Path, Destination Path ,+  The cp command is a command-line utility for copying files \r\n"+
                "and directories.\n" +
                "------------------------------------------------------------------------------------------------------------------------\n"+
                "mv , Number of args is 2: Source Path , + The mv command is a command line utility that moves files or directories from \r\n "
                + "one place to another .\n" +
                "------------------------------------------------------------------------------------------------------------------------\n"+
                "rm , Any number of args: The file to be removed ,rm command is used to delete or remove files and directory in UNIX \r\n "
                + "like operating system.\n" +
                "------------------------------------------------------------------------------------------------------------------------\n"+
                "mkdir , Any number of args: The new Directory Path ,The mkdir command is is used to create new directories.\n " +
                "------------------------------------------------------------------------------------------------------------------------\n"+
                "rmdir , Any number of args: The Directory to be removed , rmdir is a command which will remove an empty directory \r\n"
                + "on various operating systems. \n" +
                "------------------------------------------------------------------------------------------------------------------------\n"+
                "cat , Any number of args: The File Directories , cat command allows us to create single or multiple files,\r\n"
                + " view contain of file, concatenate files and redirect output in terminal or files \n" +
                "------------------------------------------------------------------------------------------------------------------------\n"+
                "more , Any number of args: The File Directories , more is a command to view (but not modify) the contents of \r\n"
                + "a text file one screen at a time. \n" +
                "------------------------------------------------------------------------------------------------------------------------\n"+
                "pwd , No Arguments for this command , The pwd command is a command line utility for printing the current working directory.\n"+
                "------------------------------------------------------------------------------------------------------------------------\n"+
                "args , Number of args is 1: Command Instruction , The info args command displays the function argument values of \r\n"
                + " the current frame \n " +
                "------------------------------------------------------------------------------------------------------------------------\n"+
                "date , No Arguments for this command , The date command displays the current date and time \n " +
                "------------------------------------------------------------------------------------------------------------------------\n"+
                "help , No Arguments for this command , The help command is a command prompt command that's used to provide \r\n"
                + "more information on another command. \n" +
                "------------------------------------------------------------------------------------------------------------------------\n"+
                "exit , No Arguments for this command ,  The command causes the shell or program to terminate \n";

        return help;
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    public static void roLs(String file) throws IOException {
        ArrayList<String> tmp = new ArrayList<>();

        Stream<Path> content = Files.list(Paths.get(Main.cwd));
        content.forEach(s -> tmp.add(s.getFileName().toString()));

        for(String str: tmp) {
            Files.write(Paths.get(file), str.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            Files.write(Paths.get(file), "\n".getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        }
    }


    public static void roLs2(String file) throws IOException {
        ArrayList<String> tmp = new ArrayList<>();

        Stream<Path> content = Files.list(Paths.get(Main.cwd));
        content.forEach(s -> tmp.add(s.getFileName().toString()));

        FileWriter writer = new FileWriter(file);
        for(String str: tmp) {
            writer.write(str + System.lineSeparator());
        }
        writer.close();
    }
    ///////////////////////////////////////////////////////////////////////////////////////

    public static void rocat(ArrayList<String> paths) throws IOException {
        ArrayList<String> tmp = new ArrayList<>();
        String fileName;
        int posFileName = paths.size();
        fileName = paths.get(posFileName-1);

        for (int i = 0 ; i < paths.size()-1 ; i++) {
            Path dir = Paths.get(paths.get(i));
            System.out.println(dir);
            try {
                Stream<String> content = Files.lines(dir);
                content.forEach(s -> tmp.add(s));
            } catch (Exception e) {
                System.out.println(e);
            }
        }

        for(String str: tmp) {
            Files.write(Paths.get(fileName), str.getBytes(), StandardOpenOption.CREATE,StandardOpenOption.APPEND);
            Files.write(Paths.get(fileName), "\n".getBytes(), StandardOpenOption.CREATE,StandardOpenOption.APPEND);
        }
    }

    public static void rocat2(ArrayList<String> paths) throws IOException {
        ArrayList<String> tmp = new ArrayList<>();
        String fileName;
        int posFileName = paths.size();
        fileName = paths.get(posFileName-1);

        for (int i = 0 ; i < paths.size()-1 ; i++) {
            Path dir = Paths.get(paths.get(i));

            try {
                Stream<String> content = Files.lines(dir);

                content.forEach(s -> tmp.add(s));
            } catch (Exception e) {
                System.out.println(e);
            }
        }

        FileWriter writer = new FileWriter(fileName);
        for(String str: tmp) {
            writer.write(str + System.lineSeparator());
        }
        writer.close();
    }
    ///////////////////////////////////////////////////////////////////////////////////////

    public static void rodate (String file,String action) throws IOException {
        String d = date();
        if(action == ">>") {
            Files.write(Paths.get(file), d.getBytes(), StandardOpenOption.CREATE,StandardOpenOption.APPEND);
            Files.write(Paths.get(file), "\n".getBytes(), StandardOpenOption.APPEND);
        }
        else if(action == ">") {
            FileWriter writer = new FileWriter(file);
            writer.write(d + System.lineSeparator());
            writer.close();
        }
    }


    public static void ropwd (String file,String action) throws IOException {
        String p = pwd();

        if(action == ">>") {
            Files.write(Paths.get(file), p.getBytes(), StandardOpenOption.CREATE,StandardOpenOption.APPEND);
            Files.write(Paths.get(file), "\n".getBytes(), StandardOpenOption.APPEND);
        }
        else if(action == ">") {
            FileWriter writer = new FileWriter(file);
            writer.write(p + System.lineSeparator());
            writer.close();
        }
    }


    public static void rohelp (String file ,String action) throws IOException {
        String h = help();
        if(action == ">>") {
            Files.createFile(Paths.get(file));
            Files.write(Paths.get(file), h.getBytes(), StandardOpenOption.CREATE,StandardOpenOption.APPEND);
            Files.write(Paths.get(file), "\n".getBytes(), StandardOpenOption.APPEND);
        }
        else if(action == ">") {
            FileWriter writer = new FileWriter(file);
            writer.write(h + System.lineSeparator());
            writer.close();
        }
    }
}