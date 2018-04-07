package tiles;

import gfx.Display;
import level.Level;

public class BaseTile extends Tile {
	
	protected int tileID;
	protected int tileColor;
	
	public BaseTile(int id, int x, int y, int tileColor)
	{
		super(id, false, false);
		this.tileID = x + y;
		this.tileColor = tileColor;
	}

	@Override
	public void render(Display display, Level level, int x, int y)
	{
		display.render(x, y, tileID, tileColor);
	}
}
