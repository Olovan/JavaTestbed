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
	
	Window window;

	int WIDTH = 640;
	int HEIGHT = 480;
	SquareDrawable square1 = new SquareDrawable(100, 200, 40, 40);
	SquareDrawable square2 = new SquareDrawable(500, 70, 40, 40);
	SquareDrawable square3 = new SquareDrawable(300, 200, 40, 40);

	public static void main(String[] args) {
		Main main = new Main();
		main.run();
	}
	
	public void run()
	{
		init();
		while(glfwWindowShouldClose(window.windowHandle) != GLFW_TRUE)
		{
			update();
			render();
		}

	}
	
	public void init()
	{
		//Create Window
		window = new Window();
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, 640, 0, 480, 0, 10);
		
		//Texture Setup testing
		square1.loadTexture("./images/Spaceman.png");
		square2.setColor((byte)255, (byte)0, (byte)0);
		square3.loadTexture("./images/Spaceman.png");
		glEnable(GL_TEXTURE_2D);
	}
	
	public void update()
	{
		window.pollEvents();
	}
	
	public void render()
	{
		window.clear();
		//draw Calls
		square2.draw();
		square3.draw();
		square1.draw();

		window.display();
	}
	

}
