package gfx;

public class Font {
	private static String chars = "" + "ABCDEFGHIJKLMNOPQRSTUVWXYZ      " + "0123456789.,:;'\"!?$%()-=+/      ";
	
	public static void render(String msg, Display display, int x, int y, int color)
	{
		msg = msg.toUpperCase();
		
		for(int i = 0; i < msg.length(); i++)
		{
			int charIndex = chars.indexOf(msg.charAt(i));
			if(charIndex >= 0) display.render(x + (i * 8), y, charIndex + 30 * 32, color);
		}
	}
}
