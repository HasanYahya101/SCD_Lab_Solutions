import java.io.*;
import java.util.ArrayList;

class FileHandler {
    public int count_words(String filename) {
        int count = 0;
        FileReader rd = null;
        BufferedReader br = null;
        try {
            rd = new FileReader(filename);
            br = new BufferedReader(rd);
            String line = "";
            while ((line = br.readLine()) != null) {
                String[] splited = line.split(" ");
                count = count + splited.length;
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (rd != null) {
                    rd.close();
                }
                if (br != null) {
                    br.close();
                }
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
        System.out.println("Total words are: " + count);
        return count;
    }

    public void replace_word(String file, String out, String given, String replace_with) {
        FileReader rd = null;
        BufferedReader br = null;
        boolean found = false;
        ArrayList<String> list = new ArrayList<String>();
        try {
            rd = new FileReader(file);
            br = new BufferedReader(rd);

            String line = "";

            while ((line = br.readLine()) != null) {
                String splitted[] = line.split(" ");

                for (int i = 0; i < splitted.length; i++) {
                    if (splitted[i].equals(given) == true) {
                        splitted[i] = replace_with;
                        found = true;
                    }
                }
                String new_line = String.join(" ", splitted);
                // System.out.println(new_line);

                list.add(new_line);
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (rd != null) {
                    rd.close();
                }
                if (br != null) {
                    br.close();
                }
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }

        try {
            File f = new File(out);
            f.createNewFile();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        FileWriter wr = null;

        try {
            wr = new FileWriter(out);

            for (String s : list) {
                wr.write(s);
                wr.write("\n");
            }

        } catch (IOException EX) {
            System.out.println(EX.getMessage());
        } finally {
            try {
                if (wr != null) {
                    wr.close();
                }
            } catch (IOException c) {
                System.out.println(c.getMessage());
            }
        }

        if (found == true) {
            System.out.println("Word replaced succesfully");
        } else {
            System.out.println("Word not found, same data copied");
        }

    }
}

public class L227971_MID_01_Q1 {
    public static void main(String[] args) {
        FileHandler hd = new FileHandler();
        hd.count_words("word_count.txt");

        hd.replace_word("replace_word.txt", "replaced.txt", "doing", "hello");
    }
}