import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

class ImageManager 
{
	private BufferedImage image;

	ImageManager() throws IllegalArgumentException, IOException
	{
		
		image = ImageIO.read(new File("1.bmp"));
		
	}
	
	BufferedImage getImage()
	{
		return image;
	}

	
}
