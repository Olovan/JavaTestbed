import java.nio.FloatBuffer;
import static java.lang.Math.*;
import static org.lwjgl.opengl.GL11.*;


public class Camera
{
	FloatBuffer buffer;
	float x, y, z;
	float pitch, yaw;
	float nearClippingPlaneDist;

	public Camera()
	{
		setPosition(0, 0, 0);
		setRotation(0, 0);
	}

	public void update()
	{
		//Vec3 offset = calculateOffset();
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();
		glRotatef(-pitch, 1, 0, 0);
		glRotatef(yaw, 0, 1, 0);
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
	public void perspectiveProjection(double fovy, double aspect, double znear, double zfar)
	{
		double pi = 3.1415926535897932384626433832795;
		double frustumHeight = tan(fovy / 2 * pi / 180) * znear;
		double frustumWidth = aspect * frustumHeight;
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glFrustum(-frustumWidth, frustumWidth, -frustumHeight, frustumHeight, znear, zfar);
		
	}
	private Vec3 calculateOffset()
	{
		float x = 0;
		float y = 0;
		float z = nearClippingPlaneDist;

		x = (float)-sin(toRadians(yaw)) * this.x;
		z = (float)cos(toRadians(yaw)) * (this.z + nearClippingPlaneDist);
		return new Vec3(x, y, z);
	}
}
