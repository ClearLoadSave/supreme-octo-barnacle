package entities;

import level.Level;

public abstract class Mob extends Entity {
	
	protected String name;
	protected int speed;
	protected int stepCount = 0;
	protected boolean isMoving;
	protected int moveDir = 0; //0: Up 1: Down 2: Left 3: Right
	protected int scale = 1;
	
	public Mob(Level level, String name, int x, int y, int speed)
	{
		super(level);
		this.name = name;
		this.x = x;
		this.y = y;
		this.speed = speed;
	}
	
	public void move(int xa, int ya)
	{
		if(xa != 0 && ya != 0)
		{
			move(xa, 0);
			move(0, ya);
			stepCount--;
			return;
		}
		
		stepCount++;
		
		if(!hasCollided(xa, ya))
		{
			if(ya < 0) moveDir = 0;
			if(ya > 0) moveDir = 1;
			if(xa < 0) moveDir = 2;
			if(xa > 0) moveDir = 3;
			
			x += xa * speed;
			y += ya * speed;
		}
	}
	
	public abstract boolean hasCollided(int xa, int ya);
	
	public String getName()
	{
		return name;
	}
}
