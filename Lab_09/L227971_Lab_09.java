import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.ArrayList;

class WordCounter {
    private final ConcurrentMap<String, Integer> wordCountMap = new ConcurrentHashMap<String, Integer>();

    public synchronized void AddWord(String word) {
        wordCountMap.merge(word, 1, Integer::sum);
    }

    public synchronized void processLine(String line) {
        String[] words = line.split(" ");

        for (String word : words) {
            AddWord(word);
        }
    }

    public ConcurrentMap<String, Integer> getWordCounts() {
        return wordCountMap;
    }
}

class FileProcessor implements Runnable {
    private final String line;
    private final WordCounter wordcount;

    public FileProcessor(String line, WordCounter wordcount) {
        this.line = line;
        this.wordcount = wordcount;
    }

    @Override
    public void run() {
        this.wordcount.processLine(line);
    }

}

class MultithreadingCountTask {
    ArrayList<String> list = new ArrayList<>();
    WordCounter counter = new WordCounter();

    public void count(String path) {
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                list.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        ArrayList<Thread> threads = new ArrayList<Thread>();

        for (String line : list) {
            Thread temp = new Thread(new FileProcessor(line, counter));
            threads.add(temp);
        }

        for (Thread th : threads) {
            th.start();
        }

        for (Thread th : threads) {
            try {
                th.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        counter.getWordCounts().forEach((k, v) -> System.out.println(k + " : " + v));

    }
}

public class L227971_Lab_09 {

    public static void main(String[] args) {
        MultithreadingCountTask task = new MultithreadingCountTask();
        task.count("inputfile.txt");
    }

}
