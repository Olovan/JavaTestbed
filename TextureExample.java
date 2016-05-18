import org.lwjgl.*;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL.*;
import static org.lwjgl.system.MemoryUtil.*;

public class Main {
	
	long windowHandle;
	int WIDTH = 640;
	int HEIGHT = 480;
	BufferedImage textureImageData;
	int texID;

	public static void main(String[] args) {
		Main main = new Main();
		main.run();
	}
	
	public void run()
	{
		init();
		while(glfwWindowShouldClose(windowHandle) != GLFW_TRUE)
		{
			update();
			render();
		}

	}
	
	public void init()
	{
		//GLFW Init Code
		if(glfwInit() != GLFW_TRUE)
			throw new IllegalStateException("GLFW failed to init");
		glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE); //Set hints before creating Window
		windowHandle = glfwCreateWindow(WIDTH, HEIGHT, (CharSequence)"Window Title", NULL, NULL);
		if(windowHandle == NULL)
			System.err.println("Window Failed To Create");
		glfwMakeContextCurrent(windowHandle);
		glfwShowWindow(windowHandle);
		
		//OpenGL init code
		createCapabilities();
		glClearColor(0, 0, 0, 1);
		glClear(GL_COLOR_BUFFER_BIT);
		
		
		//Texture Setup testing
		texID = genTexture("./images/WallTexture.png");
		glEnable(GL_TEXTURE_2D);

	}
	
	public void update()
	{
		glfwPollEvents();
	}
	
	public void render()
	{
		glClear(GL_COLOR_BUFFER_BIT);
		//draw Calls
		glEnable(GL_TEXTURE_2D);
		glBindTexture(GL_TEXTURE_2D, texID);
		glBegin(GL_TRIANGLES);
			glTexCoord2f(0.5f, 0);
			glVertex2f(0, 1);
			glColor3ub((byte)255, (byte)0, (byte)0);
			glTexCoord2f(0, 1);
			glVertex2f(-1, -1);
			glColor3ub((byte)0, (byte)255, (byte)0);
			glTexCoord2f(1, 1);
			glVertex2f(1, -1);
			glColor3ub((byte)0, (byte)0, (byte)255);
		glEnd();
		glfwSwapBuffers(windowHandle);
	}
	
	public int genTexture(String str)
	{
		int texID = glGenTextures();
		glBindTexture(GL_TEXTURE_2D, texID);

		try {
			textureImageData = ImageIO.read(new File(str));
		}
		catch(IOException e) {
			System.err.println("Failed to load Texture");
		}
		Raster raster = textureImageData.getRaster();
		byte rawImageData[] = (byte[])raster.getDataElements(0, 0, textureImageData.getWidth(), textureImageData.getHeight(), null);
		ByteBuffer byteBuffer = ByteBuffer.allocateDirect(rawImageData.length);
		byteBuffer.put(rawImageData);
		byteBuffer.flip();
		
		glTexImage2D(GL_TEXTURE_2D, 0, GL_RGB, textureImageData.getWidth(), textureImageData.getHeight(), 0, GL_RGB, GL_UNSIGNED_BYTE, byteBuffer); 
		//required
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
		
		return texID;
	}

}
