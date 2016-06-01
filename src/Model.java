import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;
import static org.lwjgl.opengl.GL11.*;

public class Model
{
	public List<Float> vertices;
	public List<Byte> colors;
	public List<Integer> indices;
	public List<Float> normals;

	public float rotation = 0;
	public float scale = 1;
	public float x = 0, y = 0, z = 0;


	private String data;
	private FloatBuffer vertexBuffer;
	private IntBuffer indexBuffer;
	private ByteBuffer colorBuffer;
	private FloatBuffer normalsBuffer;

	public Model()
	{
		init();
	}
	public Model(String file)
	{
		init();
		loadFromFile(file);
	}

	void init()
	{
		vertices = new ArrayList<Float>();
		indices = new ArrayList<Integer>();
		colors = new ArrayList<Byte>();
		normals = new ArrayList<Float>();
	}

	public void draw()
	{
		glEnableClientState(GL_VERTEX_ARRAY);
		glEnableClientState(GL_NORMAL_ARRAY);
		//glEnable(GL_COLOR_MATERIAL);
		glDisable(GL_TEXTURE_2D);

		glMatrixMode(GL_MODELVIEW);
		glPushMatrix();
		glTranslatef(x, y, z);
		glRotatef(rotation, 0, 1, 0);
		glScalef(scale, scale, scale);
		


		glNormalPointer(GL_FLOAT, 0, normalsBuffer);
		glVertexPointer(3, GL_FLOAT, 0, vertexBuffer);
		glColorPointer(3, GL_UNSIGNED_BYTE, 0, colorBuffer);
		glDrawElements(GL_TRIANGLES, indexBuffer);

		//		Alternate Draw Command
		//		glColor3ub((byte)255, (byte)255, (byte)255);
		//		glBegin(GL_TRIANGLES);
		//		for(int i = 0; i < indices.size(); i++)
		//			glArrayElement(indices.get(i) - 1);
		//		glEnd();

		glPopMatrix();
	}


	public Boolean loadFromFile(String file)
	{
		BufferedReader reader;
		try 
		{
			reader = new BufferedReader(new FileReader(file));
		} 
		catch (FileNotFoundException e)
		{
			System.out.println("Unable to find file " + file);
			return false;
		}
		String line; 
		try
		{
			while((line = reader.readLine()) != null)
			{
				insertDataFromString(line);
			}
			reader.close();
		}
		catch (IOException e)
		{
			System.out.println("IOException reading file " + file);	
			e.printStackTrace();
			return false;
		}
		generateBuffers();
		return true;
	}


	private void insertDataFromString(String input)
	{
		if(input == null || input.isEmpty() || input.length() == 0)
			return;
		Scanner scanner = new Scanner(input);
		//Vertex
		if(input.startsWith("v "))
		{
			scanner.skip("v");
			float temp;
			while(scanner.hasNextFloat() == true)
			{
				temp = scanner.nextFloat();
				vertices.add(temp);
				colors.add((byte) 255);
			}
		}
		else if(input.startsWith("vn "))
		{
			scanner.skip("vn ");
			float temp;
			while(scanner.hasNextFloat() == true)
			{
				temp = scanner.nextFloat();
				normals.add(temp);
			}
		}
		//Index
		else if(input.startsWith("f "))
		{
			scanner.skip("f");
			String[] verts = input.split(" ");
			for(int i = 1; i < verts.length; i++) //Skip verts[0] since that is the "f"
			{
				String vertexData[] = verts[i].split("/");
				indices.add(Integer.parseInt(vertexData[0]));
			}
		}
		scanner.close();
	}

	private void generateBuffers()
	{
		//Vertices
		vertexBuffer = BufferUtils.createFloatBuffer(vertices.size());
		float[] tempFloat = new float[vertices.size()];
		for(int i = 0; i < vertices.size(); i++)
			tempFloat[i] = vertices.get(i).floatValue();
		vertexBuffer.put(tempFloat);
		vertexBuffer.flip();

		//Colors
		colorBuffer = BufferUtils.createByteBuffer(colors.size());
		byte[] tempByte = new byte[colors.size()];
		for(int i = 0; i < colors.size(); i++)
			tempByte[i] = colors.get(i).byteValue();
		colorBuffer.put(tempByte);
		colorBuffer.flip();

		//Indices
		indexBuffer = BufferUtils.createIntBuffer(indices.size());
		int[] tempInt = new int[indices.size()];
		for(int i = 0; i < indices.size(); i++)
			tempInt[i] = indices.get(i).intValue() - 1;
		indexBuffer.put(tempInt);
		indexBuffer.flip();

		//Normals
		normalsBuffer = BufferUtils.createFloatBuffer(normals.size());
		float[] tempNormals = new float[normals.size()];
		for(int i = 0; i < normals.size(); i++)
			tempNormals[i] = normals.get(i).floatValue();
		normalsBuffer.put(tempNormals);
		normalsBuffer.flip();
	}

}
