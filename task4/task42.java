import java.util.Scanner;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;

public class task42 {
    private static final String API_KEY = "";
    private static final String API_URL = "https://api.exchangerate-api.com/v4/latest/";

    private static Scanner scanner = new Scanner(System.in);
    private static HttpClient client = HttpClient.newHttpClient();

    public static void main(String[] args) {
    System.out.println("[DEBUG] Starting program...");
    
    try {
        double testRate = getExchangeRate("USD", "INR");
        System.out.println("[DEBUG] Successfully fetched rate: " + testRate);
        System.out.println("[DEBUG] Mode: REAL-TIME API");
    } catch (Exception e) {
        System.out.println("[DEBUG] API failed: " + e.getMessage());
        System.out.println("[DEBUG] Mode: STATIC FALLBACK");
    }

        System.out.println("=".repeat(60));
        System.out.println("        CURRENCY CONVERTER (Real‑time)");
        System.out.println("=".repeat(60));

        while (true) {
            System.out.print("\nEnter base currency (e.g., USD): ");
            String base = scanner.next().toUpperCase();

            System.out.print("Enter target currency (e.g., INR): ");
            String target = scanner.next().toUpperCase();

            System.out.print("Enter amount: ");
            double amount = scanner.nextDouble();

            try {
                double rate = getExchangeRate(base, target);
                double converted = amount * rate;

                System.out.println("\n" + "=".repeat(60));
                System.out.printf("%.2f %s = %.2f %s\n", amount, base, converted, target);
                System.out.printf("1 %s = %.4f %s\n", base, rate, target);
                System.out.println("=".repeat(60));
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                System.out.println("Please check your internet connection and currency codes.");
            }

            System.out.print("\nConvert another? (yes/no): ");
            if (!scanner.next().equalsIgnoreCase("yes")) break;
        }

        scanner.close();
        System.out.println("\nThank you for using Currency Converter!");
    }

    private static double getExchangeRate(String base, String target) throws Exception {
        String url = API_URL + base + "?apikey=" + API_KEY;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new Exception("API returned status " + response.statusCode());
        }

        String json = response.body();
        String searchKey = "\"" + target + "\":";
        int idx = json.indexOf(searchKey);
        if (idx == -1) {
            throw new Exception("Currency code '" + target + "' not found");
        }

        int start = idx + searchKey.length();
        int end = start;
        while (end < json.length() && (Character.isDigit(json.charAt(end)) || json.charAt(end) == '.')) {
            end++;
        }
        String rateStr = json.substring(start, end);
        return Double.parseDouble(rateStr);
    }
}
