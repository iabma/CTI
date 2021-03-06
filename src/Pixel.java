public class Pixel {
    private int alpha;
    private int red;
    private int green;
    private int blue;

    public Pixel(int inChar) {
        blue = convert(getDigitAtSpot(inChar, 1));
        green = convert(getDigitAtSpot(inChar, 2));
        red = convert(getDigitAtSpot(inChar, 3));
        alpha = convertAlpha(getDigitAtSpot(inChar, 4) + getDigitAtSpot(inChar, 5) * 10);
    }

    public int alpha() {
        return alpha;
    }

    public int red() {
        return red;
    }

    public int green() {
        return green;
    }

    public int blue() {
        return blue;
    }

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
}