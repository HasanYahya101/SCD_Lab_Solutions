import java.time.format.DateTimeFormatter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
import java.io.*;

class Authentication {
    public ArrayList<String> auth = null;

    public Authentication() {
        FileReader rd = null;
        BufferedReader bd = null;
        auth = new ArrayList<String>();
        try {
            rd = new FileReader("cred.txt");
            bd = new BufferedReader(rd);

            String line = "";
            while ((line = bd.readLine()) != null) {
                auth.add(line);
            }
        } catch (IOException ioex) {
            System.out.println(ioex);
        } finally {
            try {
                if (rd != null) {
                    rd.close();
                }
                if (bd != null) {
                    bd.close();
                }
            } catch (IOException io2e) {
                System.out.println(io2e);
            }
        }

    }

    public boolean auth(String user, String pass) {
        for (String s : this.auth) {
            String split[] = s.split(" ");
            if (split[0].equals(user) && split[1].equals(pass)) {
                return true;
            }
        }
        return false;
    }
}

class Logging {
    public void log(String user, String file, String mode) {
        FileWriter logg = null;
        try {
            logg = new FileWriter("logger.txt", true);
            String line_add = "";
            line_add = user;
            line_add += " ";
            line_add += file;
            line_add += " ";
            line_add += mode;
            line_add += " ";
            String time = "";
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/ddHH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            time = (dtf.format(now));
            line_add += time;
            logg.write("\n");
            logg.write(line_add);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (logg != null) {
                    logg.close();
                }
            } catch (IOException ioa) {
                System.out.println(ioa);
            }
        }

    }
}

class FileSystem {
    public ArrayList<String> filelist = null;

    public FileSystem() {
        filelist = new ArrayList<String>();
        // add all files in current folder and print it
        File dir = new File(".");
        File[] filesList = dir.listFiles();
        if (filesList != null) {
            for (File file : filesList) {
                if (file.isFile()) {
                    filelist.add(file.getName());
                }
            }
        }

        // printing all
        /*
         * for (String s : this.filelist) {
         * System.out.println(s);
         * }
         */
    }

    public void print_files() {
        if (filelist.size() == 0) {
            System.out.println("Error: No files left to print");
            return;
        }

        System.out.println("Files are as under: ");
        for (String s : this.filelist) {
            System.out.println(s);
        }
    }

    public boolean file_exists(String file) {
        for (String s : this.filelist) {
            if (s.equals(file)) {
                return true;
            }
        }
        return false;
    }

    public void print_menu() {
        System.out.println("Menu options are as under:");
        System.out.println("1. Write");
        System.out.println("2. Read");
        System.out.println("3. Back");
    }

    public void run() {
        Scanner scn = null;
        FileWriter fw = null;
        FileReader rd = null;
        BufferedReader bd = null;
        try {
            scn = new Scanner(System.in);
            String user = "";
            String pass = "";

            System.out.println("Enter your username: ");
            user = scn.nextLine();
            System.out.println("Enter your password: ");
            pass = scn.nextLine();
            Authentication ac = new Authentication();
            while (ac.auth(user, pass) == false) {
                System.out.println("Error: Invalid values");
                System.out.println("Enter your username: ");
                user = scn.nextLine();
                System.out.println("Enter your password: ");
                pass = scn.nextLine();
            }
            String choice = "";
            while (true) {
                print_files();
                System.out.println("Enter filename or enter (exit) to logout:");
                choice = scn.nextLine();
                if (choice.equals("exit")) {
                    System.out.println("Exiting...");
                    break;
                }
                while (file_exists(choice) == false) {
                    System.out.println("Error: Enter valid filename");
                    System.out.println("Enter valid filename");
                    choice = scn.nextLine();
                }
                while (true) {
                    String choice2 = "";
                    while (choice2.equals("1") == false && choice2.equals("2") == false
                            && choice2.equals("3") == false) {
                        print_menu();
                        System.out.println("Enter a choice:");
                        choice2 = scn.nextLine();
                    }

                    if (choice2.equals("1")) { // write
                        fw = new FileWriter(choice, true); // true for append
                        String data = "";
                        System.out.println("Enter the data you want to append to the file:");
                        data = scn.nextLine();
                        fw.write(data);
                        System.out.println("Data added to file");

                        Logging lg = new Logging();
                        lg.log(user, choice2, "Write");
                    } else if (choice2.equals("2")) { // read
                        rd = new FileReader(choice);
                        bd = new BufferedReader(rd);
                        String line = "";
                        System.out.println("File contents are as under:");
                        while ((line = bd.readLine()) != null) {
                            System.out.println(line);
                        }

                        Logging lg = new Logging();
                        lg.log(user, choice2, "Read");
                    } else if (choice2.equals("3")) { // back
                        break;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (scn != null) {
                    scn.close();
                }
                if (rd != null) {
                    rd.close();
                }
                if (bd != null) {
                    bd.close();
                }
                if (fw != null) {
                    fw.close();
                }

            } catch (Exception exa) {
                System.out.println(exa.getMessage());
            }
        }
    }
}

public class L227971_MID_01_Q2 {
    public static void main(String[] args) {
        FileSystem fs = new FileSystem();
        fs.run();
    }
}