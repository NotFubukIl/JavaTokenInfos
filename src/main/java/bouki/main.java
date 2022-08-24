package src.main.java.bouki;

import java.io.*;
import java.net.*;
import java.util.*;
import org.json.*;
public class main {
    public static String Request(String urlToRead, String Token) throws Exception {
        StringBuilder result = new StringBuilder();
        URL url = new URL(urlToRead);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2227.0 Safari/537.36");
        conn.setRequestProperty("authorization", Token);
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(conn.getInputStream()))) {
            for (String line; (line = reader.readLine()) != null;) {
                result.append(line);
            }
        }
        return result.toString();
    }
    public static void main(String[] args) throws Exception {
        try {
            System.out.print("Give Me A Token: ");
            Scanner ln = new Scanner(System.in);
            String Token = ln.nextLine();
            String Req = Request("https://discord.com/api/v9/users/@me", Token);
            JSONObject result = new JSONObject(Req);
            clearConsole();
            for (String n : result.keySet()) {
                System.out.println("[ " + mainColors.Rainbow() + n + mainColors.RESET + " ]: " + mainColors.Rainbow() + result.get(n) + mainColors.RESET);
            }
        } catch (IOException e) {
            System.out.println(mainColors.RED + " An Invalid Token Was Provided " + mainColors.RESET);
        }
    }
    public final static void clearConsole() {
        try {
            if (System.getProperty("os.name").contains("Windows")) new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else Runtime.getRuntime().exec("clear");
        } catch (IOException | InterruptedException ex) {}
    }
}


