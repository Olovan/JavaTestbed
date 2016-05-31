
public class Vec2 
{
	public float x, y;

	Vec2(float x, float y)
	{
		this.x = x;
		this.y = y;
	}

	public Vec2 Add(Vec2 in)
	{
		return new Vec2(x + in.x, y + in.y);
	}
	public Vec2 Subtract(Vec2 in)
	{
		return new Vec2(x - in.x, y - in.y);
	}
	public Vec2 Multiply(Vec2 in)
	{
		return new Vec2(x * in.x, y * in.y);
	}
	public Vec2 Divide(Vec2 in)
	{
		return new Vec2(x / in.x, y / in.y);
	}

}

