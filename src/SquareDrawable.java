import org.lwjgl.opengl.*;

import static org.lwjgl.opengl.GL.*;
import static org.lwjgl.opengl.GL11.*;


public class SquareDrawable 
{
	//Variables
	public float x, y, z;
	public float width, height;
	public float originX = 0, originY = 0;
	public float rotation = 0;
	public float rotX = 0, rotY = 0, rotZ = 1;
	private byte r, g, b;
	private Texture texture;


	//Methods
	public void loadTexture(String path)
	{
		texture = new Texture(path);
	}

	public void draw()
	{
		glMatrixMode(GL_MODELVIEW);
		glPushMatrix();
		glTranslatef(x, y, z);
		glRotatef(rotation, rotX, rotY, rotZ);
		if(texture != null) {
			texture.bind();
			glEnable(GL_TEXTURE_2D);
		}else
			glDisable(GL_TEXTURE_2D);
		glBegin(GL_TRIANGLES);
			glTexCoord2f(0, 0);
			glColor3ub(r, g, b);
			glVertex2f(-originX, height - originY);
			glTexCoord2f(1, 1);
			glColor3ub(r, g, b);
			glVertex2f(width - originX, -originY);
			glTexCoord2f(0, 1);
			glColor3ub(r, g, b);
			glVertex2f(-originX, -originY);

			glTexCoord2f(0, 0);
			glColor3ub(r, g, b);
			glVertex2f(-originX, height - originY);
			glTexCoord2f(1, 1);
			glColor3ub(r, g, b);
			glVertex2f(width - originX, -originY);
			glTexCoord2f(1, 0);
			glColor3ub(r, g, b);
			glVertex2f(width - originX, height - originY);
		glEnd();
		glPopMatrix();
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
		this.z = 0;
	}
	public void setOrigin(float x, float y)
	{
		this.originX = x;
		this.originY = y;
	}
	public void setRotation(float degrees)
	{
		rotation = degrees;
	}
	public void setRotationAxis(float x, float y, float z)
	{
		this.rotX = x;
		this.rotY = y;
		this.rotZ = z;
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
