package Solucion;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Imagen {
	

//attributes
	private BufferedImage originalImage;
    private boolean[][] boolArray;
    private BufferedImage generatedImage;

//constructors
    public Imagen(String imagePath) {
        loadImage(imagePath);
    }

//operations
    private void loadImage(String imagePath) {
        try {
            originalImage = ImageIO.read(new File(imagePath));
            int width = originalImage.getWidth();
            int height = originalImage.getHeight();
            boolArray = new boolean[height][width];

            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    int rgb = originalImage.getRGB(x, y);
                    int red = (rgb >> 16) & 0xFF;
                    int green = (rgb >> 8) & 0xFF;
                    int blue = (rgb) & 0xFF;
                    int gray = (red + green + blue) / 3;
                    boolArray[y][x] = gray > 128;
                }
            }
            generatedImage = convertBooleanArrayToImage(boolArray);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean[][] getBoolArray() {
        return boolArray;
    }

    public BufferedImage getGeneratedImage() {
        return generatedImage;
    }

    private BufferedImage convertBooleanArrayToImage(boolean[][] boolArray) {
        int height = boolArray.length;
        int width = boolArray[0].length;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_BINARY);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int value = boolArray[y][x] ? 0xFF : 0x00;
                int rgb = new Color(value, value, value).getRGB();
                image.setRGB(x, y, rgb);
            }
        }

        return image;
    }

    public static void main(String[] args) {
    	Imagen converter = new Imagen("example.png");
        boolean[][] boolArray = converter.getBoolArray();
        BufferedImage generatedImage = converter.getGeneratedImage();

        try {
            ImageIO.write(generatedImage, "png", new File("output.png"));
            System.out.println("Image saved successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
