import org.lwjgl.opengl.*;

import static org.lwjgl.opengl.GL.*;
import static org.lwjgl.opengl.GL11.*;


public class SquareDrawable 
{
	//Variables
	float x, y;
	float width, height;
	byte r, g, b;
	Texture texture;


	//Methods
	public void loadTexture(String path)
	{
		texture = new Texture(path);
	}
	
	public void draw()
	{
		if(texture != null)
			texture.bind();
		glBegin(GL_TRIANGLES);
			if(texture != null)
				glTexCoord2f(0, 1);
			glVertex2f(x - width/2, y - height/2);
			glColor3ub(r, g, b);
			if(texture != null)
				glTexCoord2f(1, 0);
			glVertex2f(x + width/2, y + height/2);
			glColor3ub(r, g, b);
			if(texture != null)
				glTexCoord2f(0, 0);
			glVertex2f(x - width/2, y + height/2);
			glColor3ub(r, g, b);

			if(texture != null)
				glTexCoord2f(0, 1);
			glVertex2f(x - width/2, y - height/2);
			glColor3ub(r, g, b);
			if(texture != null)
				glTexCoord2f(1, 0);
			glVertex2f(x + width/2, y + height/2);
			glColor3ub(r, g, b);
			if(texture != null)
				glTexCoord2f(1, 1);
			glVertex2f(x + width/2, y - height/2);
			glColor3ub(r, g, b);
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
