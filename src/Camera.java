import static java.lang.Math.*;
import static org.lwjgl.opengl.GL11.*;


public class Camera
{
	public float x, y, z;
	public float pitch, yaw, roll;
	public float nearClippingPlaneDist;
	private Vec3 forward;
	private Vec3 up;

	public Camera()
	{
		setPosition(0, 0, 0);
		setRotation(0, 0);
	}

	public void update()
	{
		//Vec3 offset = calculateOffset();
		forward = getForwardVector();
		up = getUpVector();
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();
		glRotatef(-pitch, 1, 0, 0);
		glRotatef(yaw, 0, 1, 0);
		glRotatef(roll, 0, 0, 1);
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
	public void orthoProjection(double left, double right, double bottom, double top, double near, double far)
	{
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(left, right, bottom, top, near, far);
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
	public Vec3 getForwardVector()
	{
		Vec3 forward = new Vec3 ((float)sin(toRadians(yaw)), 0, (float)cos(toRadians(yaw)));
		forward.y = (float)tan(toRadians(pitch));
		return forward.normalized();
	}
	public Vec3 getUpVector()
	{
		Vec3 up = new Vec3();	
		up.x = (float)sin(toRadians(roll));
		up.y = (float)cos(toRadians(roll));
		up.z = (float)-tan(toRadians(pitch));
		return up.normalized();
	}
}
