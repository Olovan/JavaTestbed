import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL.*;

public class Light
{
	int glLightValue;

	//CONSTRUCTORS
	public Light()
	{
		glLightValue = GL_LIGHT0;
		initialize();
	}

	public Light(int glLightValue)
	{
		this.glLightValue = glLightValue;
		initialize();
	}
	//METHODS
	private void initialize()
	{
		glEnable(GL_LIGHTING);
		glEnable(glLightValue);
	}
	public static void setAmbient(float[] ambientLight)
	{
		glLightModelfv(GL_LIGHT_MODEL_AMBIENT, Utils.asFloatBuffer(ambientLight));
	}
	public void setPosition(float[] position)
	{
		glLightfv(glLightValue, GL_POSITION, Utils.asFloatBuffer(position));
	}
	public void setSpecular(float[] specular)
	{
		glLightfv(glLightValue, GL_SPECULAR, Utils.asFloatBuffer(specular));
	}
	public void setDiffuse(float[] diffuse)
	{
		glLightfv(glLightValue, GL_DIFFUSE, Utils.asFloatBuffer(diffuse));
	}

}
