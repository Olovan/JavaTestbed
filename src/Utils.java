import java.util.List;
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

	public static float[] toFloatArray (List<Float> list)
	{
		float[] array = new float[list.size()];
		for(int i = 0; i < list.size(); i++)
			array[i] = list.get(i).floatValue();

		return array;
	}
	public static byte[] toByteArray (List<Byte> list)
	{
		byte[] array = new byte[list.size()];
		for(int i = 0; i < list.size(); i++)
			array[i] = list.get(i).byteValue();

		return array;
	}
	public static int[] toIntArray(List<Integer> list)
	{
		int[] array = new int[list.size()];
		for(int i = 0; i < list.size(); i++)
			array[i] = list.get(i).intValue();

		return array;
	}

}
