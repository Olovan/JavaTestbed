import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import org.lwjgl.BufferUtils;

public class Utils
{
	public static FloatBuffer asFloatBuffer(float[] input)
	{
		FloatBuffer output = BufferUtils.createFloatBuffer(input.length);
		output.put(input);
		output.flip();
		return output;
	}
	public static ByteBuffer asByteBuffer(byte[] input)
	{
		ByteBuffer output = BufferUtils.createByteBuffer(input.length);
		output.put(input);
		output.flip();
		return output;
	}
}
