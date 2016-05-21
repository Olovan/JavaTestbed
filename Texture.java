import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.nio.ByteBuffer;

import static org.lwjgl.opengl.GL11.*;

public class Texture 
{
	public int texID;

	public Boolean loadTexture(String path)
	{
		BufferedImage buffImg;
		try {
	       		buffImg = ImageIO.read(new File(path));
		} catch (IOException e) {
			System.err.println("Texture " + path + " failed to load");
			return false;
		}
		Raster raster = buffImg.getRaster();
		byte rawData[] = (byte[])raster.getDataElements(0, 0, buffImg.getWidth(), buffImg.getHeight(), null);
		ByteBuffer byteBuff = ByteBuffer.allocateDirect(rawData.length);
		byteBuff.put(rawData);
		byteBuff.flip();
		bind();
		int dataFormat = raster.getNumDataElements() > 3 ? GL_RGBA : GL_RGB;
		glTexImage2D(GL_TEXTURE_2D, 0, dataFormat, buffImg.getWidth(), buffImg.getHeight(), 0, dataFormat, GL_UNSIGNED_BYTE, byteBuff); 
		glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
		return true;
	}

	public void bind()
	{
		glBindTexture(GL_TEXTURE_2D, texID);
	}

	private void genTexID()
	{
		texID = glGenTextures();
	}

	public Texture()
	{
		genTexID();	
	}
	public Texture(String path)
	{
		genTexID();
		loadTexture(path);
	}
}
