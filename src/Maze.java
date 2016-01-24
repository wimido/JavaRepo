import java.awt.image.BufferedImage;
import java.io.IOException;

class Maze {
	boolean [][] maze;
	int img_h;
	int img_w;
	Point start;
	Point end;
	ImageManager i;
	
	Maze() throws IllegalArgumentException, IOException
	{
		i = new ImageManager();
		start = new Point();
		end = new Point();
		getMazeFromImageManager(i.getImage());	
	}
	void getMazeFromImageManager(BufferedImage image)
	{
		img_h = image.getHeight();
		img_w = image.getWidth();
		int img_h1 = img_h-1;
		int img_w1 = img_w-1;
		int img_rgb_tmp = 0;
		maze = new boolean [img_w][img_h];
		
		
		//Make boolean table with True on black walls and false on white path
		for (int h = 1; h < img_h1; h++)
		{
			for(int w = 1; w < img_w1; w++)
			{
				img_rgb_tmp = image.getRGB(w, h);
				maze[w][h] = (((
						img_rgb_tmp & 0x000000FF) < 0x80) 
						&& 
						((img_rgb_tmp & 0x0000FF00)>>8 < 0x80)
						&&
						((img_rgb_tmp & 0x00FF0000)>>16 < 0x80)
						);
			}
		}
		
		
		//Make border
		for (int h = 0; h < img_h; h++) {maze[0][h] = true; maze[img_w1][h] = true;}
		for (int w = 1; w < img_w1; w++) {maze[w][0] = true; maze[w][img_h1] = true;}

		
		
		//Find start point
		start.x = -1;
		for (int h = 1; h < img_h1; h++)
		{
			for(int w = 1; w < img_w1; w++)
			{
				img_rgb_tmp = image.getRGB(w, h);
				
				if(((img_rgb_tmp & 0x000000FF) < 0xC8) 
					&& 
					((img_rgb_tmp & 0x0000FF00)>>8 > 0x96)
					&&
					((img_rgb_tmp & 0x00FF0000)>>16 < 0x80))
				{
					start.x = w;
					start.y = h;
					break;
				}

			}
			if(start.x >=0) break;
		}
		System.out.println("Start: " + start.x + " " + start.y);
		
		//Find end point
		end.x = -1;
		for (int h = 1; h < img_h1; h++)
		{
			for(int w = 1; w < img_w1; w++)
			{
				img_rgb_tmp = image.getRGB(w, h);
				
				if(((img_rgb_tmp & 0x000000FF) < 0x80) 
						&& 
						((img_rgb_tmp & 0x0000FF00)>>8 < 0x80)
						&&
						((img_rgb_tmp & 0x00FF0000)>>16 > 0xC8))
				{
					end.x = w;
					end.y = h;
					break;
				}

			}
			if(end.x >=0) break;
		}
		System.out.println("End: " + end.x + " " + end.y);
	}
	
	
	
	void drawMaze()
	{
		for (int h = 0; h < img_h; h++)
		{
			for(int w = 0; w < img_w; w++)
			{
				if (w == start.x && h == start.y) System.out.print("S");
				else if (w == end.x && h == end.y) System.out.print("E");
				else if (maze[w][h]) System.out.print("X");
				else if (!maze[w][h]) System.out.print(" ");
				else System.out.print("\n JAKIŒ B£¥D W ZA£O¯ENIACH \n");
						
			}
			System.out.print("\n");
		}
	}
	

	
	
}
