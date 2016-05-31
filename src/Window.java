import org.lwjgl.*;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import java.io.IOException;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL.*;
import static org.lwjgl.glfw.GLFW.*;

public class Window
{
	//Variables
	public long windowHandle;


	//Methods
	public void pollEvents()
	{
		glfwPollEvents();
	}
	
	public void clear()
	{
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
	}
	
	public void setClearColor(float r, float g, float b, float a)
	{
		glClearColor(r, g, b, a);
	}

	public void display()
	{
		glfwSwapBuffers(windowHandle);
	}

	void createWindow(int width, int height, String title)
	{
		if(glfwInit() != GLFW_TRUE)
			throw new IllegalStateException("GLFW Failed to Init");
		windowHandle = glfwCreateWindow(width, height, (CharSequence)title, 0, 0);
		if(windowHandle == 0)
		{
			System.err.println("Window failed to create!");
			return;	
		}
		glfwMakeContextCurrent(windowHandle);
		glfwSwapInterval(1);
		glfwShowWindow(windowHandle);
		createCapabilities();
	}

	//Constructors
	public Window()
	{
		createWindow(640, 480, "Title");
	}
	public Window(int width, int height)
	{
		createWindow(width, height, "Title");
	}
	public Window(int width, int height, String title)
	{
		createWindow(width, height, title);
	}
}
