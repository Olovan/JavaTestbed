import java.nio.FloatBuffer;
import static org.lwjgl.opengl.GL11.*;


public class Camera
{
	FloatBuffer buffer;
	float x, y, z;
	float pitch, yaw;

	public Camera()
	{
		setPosition(0, 0, 0);
		setRotation(0, 0);
	}

	public void update()
	{
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();
		glRotatef(-pitch, 1, 0, 0);
		glRotatef(-yaw, 0, 1, 0);
		glTranslatef(-x, -y, -z);
	}

	public void setPosition(float x, float y, float z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}
	public void setRotation(float yaw, float pitch)
	{
		this.yaw = yaw;
		this.pitch = pitch;
	}
}
