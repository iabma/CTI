import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Decode {
    private static int decodeColor(int encoded) {
        return 9 - (encoded - 165) / 10;
    }

    private static char getChar(int pixel) {
        int a = 0;
        if (((pixel & 0xFF000000) >> 24) != 0) {
            a = 256 + ((pixel & 0xFF000000) >> 24);
        }
        int r = (pixel & 0x00FF0000) >> 16;
        int g = (pixel & 0x0000FF00) >> 8;
        int b = pixel & 0x000000FF;

        //System.out.println(a + " : " + r + " : " + g + " : " + b);

        a = 99 + 156 - a;
        r = decodeColor(r);
        g = decodeColor(g);
        b = decodeColor(b);

        //System.out.println(a + " : " + r + " : " + g + " : " + b);
        //System.out.println(a * 1000 + r * 100 + g * 10 + b);

        return (char) (a * 1000 + r * 100 + g * 10 + b);
    }

    public static void main(String[] args) throws Exception {
        Scanner sysIn = new Scanner(System.in);
        System.out.print("Image path: ");
        String path = sysIn.next();
        System.out.print("Output Name (full): ");
        String outName = sysIn.next();
        sysIn.close();

        BufferedImage img = ImageIO.read(new File(path));
        final int width = img.getWidth();
        final int height = img.getHeight();
        final int[] pixels = img.getRGB(0, 0, width, height, null, 0, width);
        final int pixelSize = 4;

        ArrayList<Character> converted = new ArrayList<>();

        /*for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                System.out.println(img.getRGB(x, y));
            }
        }*/
        for (int i = 0; i < pixels.length; i++) {
            int current = getChar(pixels[i]);
            if (current != 61167) {
                converted.add(getChar(pixels[i]));
            }
        }

        PrintStream out = new PrintStream(new File(outName));

        for (char c : converted) {
            out.print(c);
        }
    }
}