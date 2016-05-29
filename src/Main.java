import org.lwjgl.*;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import com.hackoeur.jglm.*;
import com.sun.javafx.geom.Vec3f;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL.*;
import static org.lwjgl.system.MemoryUtil.*;

public class Main {
	
	Window window;

	int WIDTH = 640;
	int HEIGHT = 480;
	SquareDrawable square1 = new SquareDrawable(-100, 0, 80, 80);
	SquareDrawable square2 = new SquareDrawable(200, 70, 80, 80);
	SquareDrawable square3 = new SquareDrawable(-300, -100, 80, 80);
	FloatBuffer buffer;
	Camera camera;

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
		glFrustum(-320, 320, -240, 240, 500, 1000);

		//setup Camera
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();
		//Mat4 matrix = Matrices.lookAt(new Vec3(0, 0, 151), new Vec3(0, 0, -100), new Vec3(0, 1, 0));
		//buffer = BufferUtils.createFloatBuffer(16);
		//buffer.put(matrix.getBuffer().array());
		//buffer.flip();
		//glMultMatrixf(buffer);
		//glTranslatef(0, 0, -150);
		camera = new Camera();
		camera.z = 550;


		//Texture Setup testing
		square2.setColor((byte)255, (byte)0, (byte)0);
		square3.loadTexture("./images/Spaceman.png");
		square1.loadTexture("./images/WallTexture.png");
		square1.setOrigin(20, 20);
		glEnable(GL_TEXTURE_2D);
		
		//Input Manager
		InputManager.init();
		glfwSetKeyCallback(window.windowHandle, new GLFWKeyCallback() {
			@Override
			public void invoke(long window, int key, int scancode, int action, int mods) {
				// TODO Auto-generated method stub
				InputManager.glfwKeyCallback(key, scancode, action, mods);
			}
			});
	}
	
	public void update()
	{
		window.pollEvents();
		//square1.setRotation(square1.rotation + 0.5f);
		//square1.setRotationAxis(0, 1, 0);
		if(InputManager.getKey(GLFW_KEY_D))
			camera.yaw += 1;
		if(InputManager.getKey(GLFW_KEY_A))
			camera.yaw -= 1;
		if(InputManager.getKey(GLFW_KEY_W))
			camera.pitch += 1;
		if(InputManager.getKey(GLFW_KEY_S))
			camera.pitch -= 1;
		camera.update();
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
