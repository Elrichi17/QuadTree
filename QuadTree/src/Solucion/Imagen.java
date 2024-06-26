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

//constructors
    public Imagen(String imagePath) throws IOException {
        loadImage(imagePath);
    }

//operations
    
    private void loadImage(String imagePath) throws IOException {
    
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
                    boolArray[y][x] = gray > 128;//esto devuelve true o false
                }
            }
    }

    public BufferedImage getOriginalImage() {
		return originalImage;
	}

	public boolean[][] getBoolArray() {
        return boolArray;
    }

    public int getWidth() {
        return originalImage.getWidth();
    }

    public int getHeight() {
        return originalImage.getHeight();
    }
    public BufferedImage convertBooleanArrayToImage(boolean[][] boolArray) {
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


}
