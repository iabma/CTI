//import org.mozilla.universalchardet.UniversalDetector;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class test {
    private static int convert(int num) {
        return (9 - num) * 10 + 165;
    }

    private static int convertAlpha(int num) {
        return (99 - num) + 156;
    }

    private static int getDigitAtSpot(int num, int spot) {
        num %= (int) Math.pow(10, spot);

        if (num < Math.pow(10, spot - 1)) {
            num = 0;
        } else {
            while (num >= 10)
                num /= 10;
        }

        return num;
    }

    public static void main(String[] args) throws Exception {
        //UniversalDetector det = new UniversalDetector()
        ArrayList<Character> data = new ArrayList<>();
        Path toImg = Paths.get("out/production/CTI/Driver.class");
        Scanner in = new Scanner(toImg, "UTF-16");

        while (in.hasNextLine()) {
            String charsOnLine = in.nextLine();

            for (char c : charsOnLine.toCharArray()) {
                data.add(c);
            }
        }

        for (char c : data) {
            int blue = convert(getDigitAtSpot(c, 1));
            int green = convert(getDigitAtSpot(c, 2));
            int red = convert(getDigitAtSpot(c, 3));
            int alpha = convertAlpha(getDigitAtSpot(c, 4) + getDigitAtSpot(c, 5) * 10);
        }
    }
}