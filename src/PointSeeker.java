import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class PointSeeker {

	List<Point> QueuePoints;
	List<Point> QueuePointsTmp;
	List<Point> Path;
	boolean pathFound;
	
	
	
	PointSeeker()
	{
		QueuePoints = new ArrayList<Point>();
		QueuePointsTmp = new ArrayList<Point>();
		Path = new ArrayList<Point>();
		pathFound = false;
	}
	
	void findPath(Point start, Point end, boolean [][] mazeIn, int maze_w, int maze_h, BufferedImage img)
	{
		QueuePoints.add(start);
		boolean [][] maze = new boolean [maze_w][maze_h];
		
		for(int i=0; i<mazeIn.length; i++)
			  for(int j=0; j<mazeIn[i].length; j++)
			    maze[i][j]=mazeIn[i][j];
		
		int [][] pathValues = new int[maze_w][maze_h];
		int iteration = 1;
		while(!QueuePoints.isEmpty())
		{
			for (Point point : QueuePoints)
			{
				int x = point.x;
				int y = point.y;
								
				pathValues[x][y] = iteration;
				if((x == end.x) && (y == end.y)) { pathFound = true; break; }
					
				x++;
				if(!maze[x][y]&&pathValues[x][y]==0) 
				{ 
					QueuePointsTmp.add(new Point(x,y)); 
					maze[x][y] = true; 
				}
				
				x -= 2;
				if(!maze[x][y]&&pathValues[x][y]==0) 
				{ 
					QueuePointsTmp.add(new Point(x,y)); 
					maze[x][y] = true; 
				}
				
				x++;
				y++;
				if(!maze[x][y]&&pathValues[x][y]==0) 
				{ 
					QueuePointsTmp.add(new Point(x,y)); 
					maze[x][y] = true; 
				}
				
				y -= 2;
				if(!maze[x][y]&&pathValues[x][y]==0) 
				{ 
					QueuePointsTmp.add(new Point(x,y)); 
					maze[x][y] = true; 
				}
				
			}

			if(pathFound) { break; }
			iteration++;
			QueuePoints.clear();
			QueuePoints.addAll(QueuePointsTmp);
			QueuePointsTmp.clear();
			
		}
		//drawIterations(pathValues, maze_w, maze_h);
		drawPath(start, end, pathValues, mazeIn, iteration, maze_w, maze_h, img);
	}
	

	
	
	private void drawIterations(int [][] pathValues,int maze_w, int maze_h)
	{
		System.out.print("\n");
		for(int h=0;h<maze_h;h++)
		{
			for(int w=0;w<maze_w;w++)
			{
				System.out.print(Math.min(pathValues[w][h],9));
			}
			System.out.print("\n");
		}
	}
	
	
	
	private void drawPath (Point start, Point end,int [][] pathValues, boolean [][] mazeIn, int iteration, int maze_w, int maze_h, BufferedImage img)
	{
		System.out.print("\n");
		int i = iteration - 1;
		Point tmp= new Point(end.x, end.y);
		boolean [][] maze = mazeIn.clone();
		boolean [][] pathArray = new boolean [maze_w][maze_h];
		
		while (i > 0)
		{
			if (pathValues[tmp.x+1][tmp.y] == i) tmp.x = tmp.x+1;
			else if (pathValues[tmp.x-1][tmp.y] == i) tmp.x = tmp.x-1;
			else if (pathValues[tmp.x][tmp.y+1] == i) tmp.y = tmp.y+1;
			else if (pathValues[tmp.x][tmp.y-1] == i) tmp.y = tmp.y-1;
			
			Path.add(new Point (tmp.x,tmp.y));
			i--;
		}

		
		
		File outputfile = new File("1_solved.bmp");
		
		for (Point p : Path)
		{
			pathArray [p.x][p.y] = true;
			img.setRGB(p.x, p.y, 0xFF);
		}
		try {
			
		    ImageIO.write(img, "bmp", outputfile);
		} catch (IOException e) {
		}

		

		
		
		
//		for(int h=0;h<maze_h;h++)
//		{
//			for(int w=0;w<maze_w;w++)
//			{
//				if (maze[w][h]) System.out.print("X");
//				else if (pathArray[w][h]) System.out.print("*");
//				else System.out.print(" ");
//				
//			}
//			System.out.print("\n");
//		}
		
	}
}
