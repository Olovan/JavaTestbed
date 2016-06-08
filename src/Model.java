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
	public list<Float> textures;
	public List<Integer> indices;
	public List<Float> normals;

	private List<Float> verticies_index;
	private List<Float> textures_index;
	private List<Float> normals_index;
	private List<Float> colors_index;

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
		//Vertex
		if(input.startsWith("v "))
		{
			String[] verts = input.split(" ");
			float temp;
			for(int i = 1; i < verts.length; i++)
			{
				temp = Float.parseFloat(verts[i]);
				vertices.add(temp);
				colors.add((byte) 255);
			}
		}
		else if(input.startsWith("vn "))
		{
			String[] stringNormals= input.split(" ");
			float normal;
			for(int i = 1; i < stringNormals.length; i++)
			{
				normal = Float.parseFloat(stringNormals[i]);
				normals.add(normal);
			}
		}
		else if(input.startsWith("vt "))
		{
			String[] stringTextures = input.split(" ");
			float texture;
			for(int i = 1; i < stringTextures.length; i++)
			{
				texture = Float.parseFloat(stringTextures[i]);
				textures.add(texture);
			}
		}
		//Index
		else if(input.startsWith("f "))
		{
			String[] verts = input.split(" ");
			for(int i = 1; i < verts.length; i++) //Skip verts[0] since that is the "f"
			{
				String vertexData[] = verts[i].split("/");
				int vertex = Integer.parseInt(vertexData[0]);
				int texture = Integer.parseInt(vertexData[1]);
				int normal = Integer.parseInt(vertexData[2]);
				//indices.add(Integer.parseInt(vertexData[0]));
				verticies_index.add(vertices.get(vertex - 1));
				textures_index.add(textures.get(texture - 1));
				normals_index.add(normals.get(normal - 1));
			}
		}
	}

	private void generateBuffers()
	{
		//Vertices
		vertexBuffer = BufferUtils.createFloatBuffer(verticies_index.size());
		float[] tempFloat = new float[verticies_index.size()];
		for(int i = 0; i < verticies_index.size(); i++)
			tempFloat[i] = verticies_index.get(i).floatValue();
		vertexBuffer.put(tempFloat);
		vertexBuffer.flip();

		//Colors
		colorBuffer = BufferUtils.createByteBuffer(colors_index.size());
		byte[] tempByte = new byte[colors_index.size()];
		for(int i = 0; i < colors_index.size(); i++)
			tempByte[i] = colors_index.get(i).byteValue();
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
		normalsBuffer = BufferUtils.createFloatBuffer(normals_index.size());
		float[] tempNormals = new float[normals_index.size()];
		for(int i = 0; i < normals_index.size(); i++)
			tempNormals[i] = normals_index.get(i).floatValue();
		normalsBuffer.put(tempNormals);
		normalsBuffer.flip();
	}

}
