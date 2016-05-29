
public class Vec3 
{
	public float x, y, z;

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

}

