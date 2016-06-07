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


import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL.*;
import static org.lwjgl.system.MemoryUtil.*;
import static java.lang.Math.*;

public class Demo {

	int WIDTH = 1000;
	int HEIGHT = 800;

	Window window;
	SquareDrawable square1 = new SquareDrawable(-100, 0, 80, 80);
	SquareDrawable square2 = new SquareDrawable(200, 70, 80, 80);
	SquareDrawable square3 = new SquareDrawable(-300, -100, 80, 80);
	Model model;
	Camera camera;
	GLFWKeyCallback fuckGarbageCollector;
	Light light0;

	public static void main(String[] args) {
		Demo demo= new Demo();
		demo.run();
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
		window = new Window(WIDTH, HEIGHT);
		window.setClearColor(0.0f, 0.0f, 0.0f, 1);
		camera = new Camera();
		camera.perspectiveProjection(60, WIDTH/HEIGHT, 1, 1000);
		camera.z = 50;
		//glMatrixMode(GL_PROJECTION);
		//glLoadIdentity();
		//glFrustum(-320, 320, -240, 240, 100, 2000);
		//Mat4 matrix = Matrices.lookAt(new Vec3(0, 0, 151), new Vec3(0, 0, -100), new Vec3(0, 1, 0));
		//buffer = BufferUtils.createFloatBuffer(16);
		//buffer.put(matrix.getBuffer().array());
		//buffer.flip();
		//glMultMatrixf(buffer);
		//glTranslatef(0, 0, -150);


		//Texture Setup testing
		square2.setColor((byte)255, (byte)0, (byte)0);
		square3.loadTexture("./images/Spaceman.png");
		square1.loadTexture("./images/WallTexture.png");
		square1.setOrigin(20, 20);
		glEnable(GL_TEXTURE_2D);
		
		//Load Model
		model = new Model("./objects/bunny.obj");
		model.scale = 10;
		model.z = 0;
		model.y = -10;

		//Culling Test Settings
		//glEnable(GL_CULL_FACE);
		//glCullFace(GL_FRONT);
		//glFrontFace(GL_CCW);
		glEnable(GL_DEPTH_TEST);
		glEnable(GL_NORMALIZE);

		//Input Manager
		InputManager.init();
		fuckGarbageCollector = new GLFWKeyCallback() 
		{
			@Override
			public void invoke(long window, int key, int scancode, int action, int mods) {
				// TODO Auto-generated method stub
				InputManager.glfwKeyCallback(key, scancode, action, mods);
			}
		};
		glfwSetKeyCallback(window.windowHandle, fuckGarbageCollector);

		//Lighting
		glShadeModel(GL_SMOOTH);
		glEnable(GL_COLOR_MATERIAL);
		glColorMaterial(GL_FRONT_AND_BACK, GL_DIFFUSE);
		glMateriali(GL_FRONT_AND_BACK, GL_SHININESS, 120);
		glMaterialfv(GL_FRONT_AND_BACK, GL_SPECULAR, Utils.asFloatBuffer(new float[] {0.8f, 0.8f, 0.8f, 1}));
		light0 = new Light(GL_LIGHT0);
		light0.setDiffuse(new float[] {0.2f, 0.2f, 0.2f, 1});
		light0.setSpecular(new float[] {0.8f, 0.8f, 0.8f, 1});
		light0.setPosition(new float[] {20, 20, 40, 1});

		Light.setAmbient(new float[] {0, 0, 0, 1});

	}

	public void update()
	{
		window.pollEvents();
		
		model.rotation++;

		square1.setRotation(square1.rotation + 0.5f);
		square1.setRotationAxis(0, 1, 0);

		if(InputManager.getKey(GLFW_KEY_D))
			camera.yaw += 1;
		if(InputManager.getKey(GLFW_KEY_A))
			camera.yaw -= 1;
		if(InputManager.getKey(GLFW_KEY_W))
			camera.pitch += 1;
		if(InputManager.getKey(GLFW_KEY_S))
			camera.pitch -= 1;
		if(InputManager.getKey(GLFW_KEY_E))
			camera.roll += 1;
		if(InputManager.getKey(GLFW_KEY_Q))
			camera.roll -= 1;
		if(InputManager.getKey(GLFW_KEY_LEFT))
			camera.x -= 1;
		if(InputManager.getKey(GLFW_KEY_RIGHT))
			camera.x += 1;
		if(InputManager.getKey(GLFW_KEY_UP))
			camera.z -= 1;
		if(InputManager.getKey(GLFW_KEY_DOWN))
			camera.z += 1;
		if(InputManager.getKey(GLFW_KEY_SPACE))
			camera.y += 1;
		if(InputManager.getKey(GLFW_KEY_LEFT_CONTROL))
			camera.y -= 1;
		camera.update();
	}

	public void render()
	{
		window.clear();
		//draw Calls
		square2.draw();
		square3.draw();
		square1.draw();
		model.draw();

		window.display();
	}


}
