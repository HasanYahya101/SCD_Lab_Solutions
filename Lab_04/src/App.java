import java.io.IOException;
import java.util.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class App<T extends CharSequence> {

    public Map<String, Integer> countTagFrequency(Elements elements) {
        Map<String, Integer> tagFrequency = new HashMap<>();
        for (Element element : elements) {
            String tagName = element.tagName();
            tagFrequency.put(tagName, tagFrequency.getOrDefault(tagName, 0) + 1);
        }
        return tagFrequency;
    }

    public Map<String, Integer> countKeywordFrequency(String bodyText, List<T> keywords) {
        Map<String, Integer> keywordFrequency = new HashMap<>();
        for (T keyword : keywords) {
            int count = bodyText.split("\\b" + keyword.toString().toLowerCase() + "\\b").length - 1;
            keywordFrequency.put(keyword.toString(), count);
        }
        return keywordFrequency;
    }

    public void analyzePage(T url, List<T> keywords) {
        try {
            Document document = Jsoup.connect(url.toString()).get();
            System.out.println("Analyzing URL: " + url);

            Elements allElements = document.getAllElements();
            Elements images = document.select("img");

            if (images.size() > 5) {
                System.out.println("Warning: More than 5 images found on the page.");
            }

            Map<String, Integer> tagFrequency = countTagFrequency(allElements);

            String bodyText = document.body().text().toLowerCase();
            Map<String, Integer> keywordFrequency = countKeywordFrequency(bodyText, keywords);

            List<Map.Entry<String, Integer>> sortedTags = new ArrayList<>(tagFrequency.entrySet());
            sortedTags.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

            displayResults("HTML/CSS Tag Frequencies (Sorted):", sortedTags);
            displayKeywordResults("Keyword Frequencies:", keywordFrequency);

        } catch (IOException e) {
            System.out.println("Error fetching the page: " + e.getMessage());
        }
    }

    private void displayResults(String title, List<Map.Entry<String, Integer>> sortedTags) {
        System.out.println("\n" + title);
        for (Map.Entry<String, Integer> entry : sortedTags) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    private void displayKeywordResults(String title, Map<String, Integer> keywordFrequency) {
        System.out.println("\n" + title);
        for (Map.Entry<String, Integer> entry : keywordFrequency.entrySet()) {
            System.out.println("Keyword '" + entry.getKey() + "': " + entry.getValue());
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        App<String> analyzer = new App<String>();

        System.out.println("Enter URL:");
        String url = scanner.nextLine().trim();

        while (!url.startsWith("https://")) {
            System.out.println("Please enter a valid URL (should start with https://):");
            url = scanner.nextLine().trim();
        }

        System.out.println("Enter keywords separated by commas:");
        String[] keywordsArray = scanner.nextLine().split(",");
        List<String> keywords = Arrays.asList(keywordsArray);

        analyzer.analyzePage(url, keywords);
        scanner.close();
    }
}
