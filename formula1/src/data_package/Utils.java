package data_package;

import java.util.Random;

public class Utils {
    static Random rand = new Random();

    //This method is for the fastest lap time.

    // This method returns a random lap time between 1 min 30 sec and 1 min 35 sec.
    public static String generateRandomLapTime() {
        int minLapTime = 90; // in seconds
        int maxLapTime = 95; // in seconds
        int randomLapTime = rand.nextInt(maxLapTime - minLapTime + 1) + minLapTime; // in seconds

        int minutes = randomLapTime / 60;
        int seconds = randomLapTime % 60;
        int milliseconds = rand.nextInt(1000); // generate random milliseconds

        return String.format("%02d:%02d.%03d", minutes, seconds, milliseconds);
    }

      public static int generateRandomNumber() {
            Random random = new Random();
            return random.nextInt(10); // Generates a random number between 0 (inclusive) and 10 (exclusive).
        }
}
