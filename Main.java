import org.lwjgl.*;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL.*;

public class Main {
	
	long windowHandle;
	int WIDTH = 640;
	int HEIGHT = 480;

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
		windowHandle = glfwCreateWindow(WIDTH, HEIGHT, (CharSequence)"Window Title", 0, 0);
		if(windowHandle == 0)
			System.err.println("Window Failed To Create");
		glfwMakeContextCurrent(windowHandle);
		glfwShowWindow(windowHandle);
		
		//OpenGL init code
		createCapabilities();
		glClearColor(0, 0, 0, 1);
		glClear(GL_COLOR_BUFFER_BIT);
		
		
		//Texture Setup testing

		
	}
	
	public void update()
	{
		glfwPollEvents();
	}
	
	public void render()
	{
		glClear(GL_COLOR_BUFFER_BIT);
		//draw Calls
		glBegin(GL_TRIANGLES);
			glVertex2f(0, 1);
			glColor3ub((byte)255, (byte)0, (byte)0);
			glTexCoord2f(0, 1);
			glVertex2f(-1, -1);
			glColor3ub((byte)0, (byte)255, (byte)0);
			glTexCoord2f(-1, -1);
			glVertex2f(1, -1);
			glColor3ub((byte)0, (byte)0, (byte)255);
			glTexCoord2f(1, -1);
		glEnd();
		glfwSwapBuffers(windowHandle);
	}

}
