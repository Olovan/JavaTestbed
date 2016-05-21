import org.lwjgl.opengl.*;

import static org.lwjgl.opengl.GL.*;
import static org.lwjgl.opengl.GL11.*;


public class SquareDrawable 
{
	//Variables
	private float x, y;
	private float width, height;
	private byte r, g, b;
	private Texture texture;


	//Methods
	public void loadTexture(String path)
	{
		texture = new Texture(path);
	}

	public void draw()
	{
		if(texture != null) {
			texture.bind();
			glEnable(GL_TEXTURE_2D);
		}else
			glDisable(GL_TEXTURE_2D);
		glBegin(GL_TRIANGLES);
			glTexCoord2f(0, 1);
			glColor3ub(r, g, b);
			glVertex2f(x - width/2, y - height/2);
			glTexCoord2f(1, 0);
			glColor3ub(r, g, b);
			glVertex2f(x + width/2, y + height/2);
			glTexCoord2f(0, 0);
			glColor3ub(r, g, b);
			glVertex2f(x - width/2, y + height/2);

			glTexCoord2f(0, 1);
			glColor3ub(r, g, b);
			glVertex2f(x - width/2, y - height/2);
			glTexCoord2f(1, 0);
			glColor3ub(r, g, b);
			glVertex2f(x + width/2, y + height/2);
			glTexCoord2f(1, 1);
			glColor3ub(r, g, b);
			glVertex2f(x + width/2, y - height/2);
		glEnd();
	}

	public void setColor(byte r, byte g, byte b)
	{
		this.r = r;
		this.g = g;
		this.b = b;
	}
	public void setSize(float sizeX, float sizeY)
	{
		this.width = sizeX;
		this.height = sizeY;
	}
	public void setPosition(float x, float y)
	{
		this.x = x;
		this.y = y;
	}
	private void createSquare(float posX, float posY, float sizeX, float sizeY)
	{
		setPosition(posX, posY);
		setSize(sizeX, sizeY);
		setColor((byte)255, (byte)255, (byte)255);
	}


	//Constructors
	SquareDrawable()
	{
		setPosition(0, 0);
		setSize(1, 1);
		setColor((byte)255, (byte)255, (byte)255);
	}
	SquareDrawable(float posX, float posY)
	{
		setPosition(posX, posY);
		setSize(1, 1);
		setColor((byte)255, (byte)255, (byte)255);
	}
	SquareDrawable(float posX, float posY, float sizeX, float sizeY)
	{
		setPosition(posX, posY);
		setSize(sizeX, sizeY);
		setColor((byte)255, (byte)255, (byte)255);
	}


}
