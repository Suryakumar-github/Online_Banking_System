import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.util.Scanner;

public class AdminAuthentication {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter username:");
        String username = scanner.nextLine();

        System.out.println("Enter password:");
        String password = scanner.nextLine();

        while (!authenticateAdmin(username, password)) {
            System.out.println("Unauthorized access!");
            playSound("/home/zoho/Suryakumar/Online_Banking_System/TF043.WAV");
            System.out.println("Enter username:");
            username = scanner.nextLine();
            System.out.println("Enter password:");
            password = scanner.nextLine();
        }

        System.out.println("Welcome, Admin!");
    }

    private static boolean authenticateAdmin(String username, String password) {
        return username.equals("admin") && password.equals("admin123");
    }

   private static void playSound(String filename) {
    try {
        File file = new File(filename);
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
        Clip clip = AudioSystem.getClip();
        clip.open(audioStream);
        clip.start();
        clip.drain();
        clip.close();
    } catch (Exception e) {
        System.err.println("Error playing sound: " + e.getMessage());
    }
}
}
