import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class UrlShort {
    private HashMap<String, String> urlMap;
    private String domain;
    private char[] chars;
    private Random random;

    public UrlShort(String domain) {
        this.urlMap = new HashMap<>();
        this.domain = domain;
        this.chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
        this.random = new Random();
    }

    public String shortenURL(String longURL) {
        String shortURL = generateShortURL();
        urlMap.put(shortURL, longURL);
        return domain + "/" + shortURL;
    }

    public String getLongURL(String shortURL) {
        return urlMap.get(shortURL.replace(domain + "/", ""));
    }

    private String generateShortURL() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            sb.append(chars[random.nextInt(chars.length)]);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UrlShort urlShortener = new UrlShort("http://short.url");

        while (true) {
            System.out.println("1. Shorten URL");
            System.out.println("2. Retrieve Long URL");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (choice == 1) {
                System.out.print("Enter the long URL: ");
                String longURL = scanner.nextLine();
                String shortURL = urlShortener.shortenURL(longURL);
                System.out.println("Short URL: " + shortURL);
            } else if (choice == 2) {
                System.out.print("Enter the short URL: ");
                String shortURL = scanner.nextLine();
                String longURL = urlShortener.getLongURL(shortURL);
                if (longURL != null) {
                    System.out.println("Long URL: " + longURL);
                } else {
                    System.out.println("Short URL not found.");
                }
            } else if (choice == 3) {
                System.out.println("Exiting...");
                break;
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }
}
