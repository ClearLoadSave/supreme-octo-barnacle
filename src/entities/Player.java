package entities;

import gfx.Colors;
import gfx.Display;
import level.Level;
import main.InputHandler;

public class Player extends Mob {

	private InputHandler input;
	private int color = Colors.get(-1, 111, 145, 543);
	private int scale = 1;
	
	public Player(Level level, int x, int y, InputHandler input)
	{
		super(level, "Player", x, y, 1);
		this.input = input;
	}
	
	@Override
	public void tick()
	{
		int xa = 0;
		int ya = 0;
		
		if(input.up.isPressed()) ya--;
		if(input.down.isPressed()) ya++;
		if(input.left.isPressed()) xa--;
		if(input.right.isPressed()) xa++;
		
		if(xa != 0 || ya != 0) {
			move(xa, ya);
			isMoving = true;
		} else {
			isMoving = false;
		}
	}
	
	@Override
	public void render(Display display)
	{
		int xTile = 0;
		int yTile = 28;
		int walkingSpeed = 4;
		int flipTop = (stepCount >> walkingSpeed) & 1;
		int flipBottom = (stepCount >> walkingSpeed) & 1;
		
		if(moveDir == 0 && isMoving == true) {
			xTile += 6;
		} else if(moveDir == 1 && isMoving == true) {
			xTile += 4;
		} else if(moveDir > 1 && isMoving == true) {
			xTile += 8 + ((stepCount >> walkingSpeed) & 1) * 2;
			flipTop = (moveDir - 1) % 2;
		} else if(moveDir == 1 && isMoving == false) {
			xTile +=2;
		} else if(moveDir >= 2 && isMoving == false) {
			xTile += 8;
		}
		
		int modifier = 8 * scale;
		int xOffset = x - modifier / 2;
		int yOffset = y - modifier / 2 - 4;
		
		//Top Quadrant
		display.render(xOffset + (modifier * flipTop), yOffset, xTile + yTile * 32, color, flipTop, scale);
		display.render(xOffset + modifier - (modifier * flipTop), yOffset, (xTile + 1) + yTile * 32, color, flipTop, scale);
		
		//Bottom Quadrant
		display.render(xOffset + (modifier * flipBottom), yOffset + modifier, xTile + (yTile + 1) * 32, color, flipBottom, scale);
		display.render(xOffset + modifier - (modifier * flipBottom), yOffset + modifier, (xTile + 1) + (yTile + 1) * 32, color, flipBottom, scale);
	}
	
	@Override
	public boolean hasCollided(int xa, int ya)
	{
		int xMin = 0;
		int xMax = 7;
		int yMin = 3;
		int yMax = 7;
		for(int x = xMin; x < xMax; x++)
		{
			if(isSolidTile(xa, ya, x, yMin)) return true;
		}
		for(int x = xMin; x < xMax; x++)
		{
			if(isSolidTile(xa, ya, x, yMax)) return true;
		}
		for(int y = yMin; y < yMax; y++)
		{
			if(isSolidTile(xa, ya, xMin, y)) return true;
		}
		for(int y = yMin; y < yMax; y++)
		{
			if(isSolidTile(xa, ya, xMax, y)) return true;
		}
		
		return false;
	}
}