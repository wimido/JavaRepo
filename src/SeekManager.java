import java.io.IOException;

public class SeekManager {
	public static void main(String [ ] args) throws IllegalArgumentException, IOException
	{
		Maze m = new Maze();
//		m.drawMaze();
		PointSeeker ps = new PointSeeker();
		ps.findPath(m.start, m.end, m.maze, m.img_w, m.img_h, m.i.getImage());
		System.out.println("Koniec. " + ps.pathFound);
	}
}
