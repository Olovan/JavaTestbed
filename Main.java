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
	SquareDrawable square1 = new SquareDrawable(0, 0, 0.2f, 0.2f);
	SquareDrawable square2 = new SquareDrawable(-0.5f, 0, 0.2f, 0.2f);
	SquareDrawable square3 = new SquareDrawable(0.7f, 0, 0.2f, 0.2f);

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
		
		//Texture Setup testing
		square1.loadTexture("./images/WallTexture.png");
		square2.setColor((byte)255, (byte)0, (byte)0);
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
		square1.draw();
		square2.draw();
		square3.draw();

		window.display();
	}
	

}
