import static java.lang.Math.*;

public class Vec3 
{
	public float x, y, z;

	Vec3()
	{
		x = 0;
		y = 0;
		z = 0;
	}
	Vec3(float x, float y, float z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}
	public Vec3 Add(Vec3 in)
	{
		return new Vec3(x + in.x, y + in.y, z + in.z);
	}
	public Vec3 Subtract(Vec3 in)
	{
		return new Vec3(x - in.x, y - in.y, z - in.z);
	}
	public Vec3 Multiply(Vec3 in)
	{
		return new Vec3(x * in.x, y * in.y, z * in.z);
	}
	public Vec3 Divide(Vec3 in)
	{
		return new Vec3(x / in.x, y / in.y, z / in.z);
	}
	public double magnitude()
	{
		return sqrt(x*x + y*y + z*z);
	}
	public Vec3 normalized()
	{
		return new Vec3(x / (float)magnitude(), y / (float)magnitude(), z / (float)magnitude());
	}
	public Vec3 cross(Vec3 in)
	{
		return new Vec3(y*in.z - z*in.y, z*in.x - x*in.z, x*in.y-y*in.x);
	}

}

