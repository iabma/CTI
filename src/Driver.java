import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Driver {
    static int nextPerfectDim(int N) {
        return (int) Math.ceil(Math.sqrt(N));
    }

    public static void main(String[] args) throws Exception {
        Scanner sysIn = new Scanner(System.in);
        System.out.print("File path: ");
        String path = sysIn.next();
        System.out.print("Charset: ");
        String charset = sysIn.next();
        System.out.print("Output Name: ");
        String outName = sysIn.next();
        sysIn.close();

        Path toImg = Paths.get(path);
        Scanner in = new Scanner(toImg, charset);
        ArrayList<Character> data = new ArrayList<>();
        ArrayList<Pixel> converted = new ArrayList<>();

        while (in.hasNextLine()) {
            String charsOnLine = in.nextLine();

            for (char c : charsOnLine.toCharArray()) {
                data.add(c);
            }
        }

        for (char c : data) {
            converted.add(new Pixel(c));
        }

        int width = nextPerfectDim(converted.size());
        int height = width;

        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        File out;

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int index = y * width + x;

                if (index >= converted.size()) {
                    img.setRGB(x, y, 0);
                } else {
                    int a, r, g, b, p;
                    Pixel current = converted.get(index);

                    a = current.alpha();
                    r = current.red();
                    g = current.green();
                    b = current.blue();

                    p = (a<<24) | (r<<16) | (g<<8) | b;

                    img.setRGB(x, y, p);
                }
            }
        }

        try {
            out = new File(outName + ".png");
            ImageIO.write(img, "png", out);
        } catch (IOException ex) {
            System.out.println("Error: " + ex);
        }
    }
}