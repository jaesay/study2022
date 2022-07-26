package chapter9.item59;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;

public class Item59Example {

    public static void main(String[] args) throws IOException {
        int n = 2 * (Integer.MAX_VALUE / 3);
        int low = 0;
        for (int i = 0; i < 1_000_000; i++) {
            if (random(n) < n/2) {
                low++;
            }
        }
        System.out.println(low);

        try (InputStream in = new URL("https://www.oracle.com/java/technologies/javase/10-relnote-issues.html#NewFeature").openStream()) {
            in.transferTo(System.out);
        }
    }

    private static Random rnd = new Random();

    private static int random(int n) {
        return Math.abs(rnd.nextInt()) % n;
    }
}
