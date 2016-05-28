import java.util.ArrayList;
import java.util.List;
import static org.lwjgl.glfw.GLFW.*;

public class InputManager 
{
	//State Boolean Arrays
	public static Boolean[] keys; 
	public static Boolean[] buttons; 
	//Listeners
	private static List<OnPressListener> onPressListeners = new ArrayList<OnPressListener>();
	private static List<OnReleaseListener> onReleaseListeners = new ArrayList<OnReleaseListener>();
	
	public static void glfwKeyCallback(int key, int scancode, int action, int mods)
	{
		if(action == GLFW_PRESS)
		{
			keys[key] = true;
			for(OnPressListener listener : onPressListeners)
				listener.onPress(key, mods);
		}
		else if(action == GLFW_RELEASE)
		{
			keys[key] = false;
			for(OnReleaseListener listener : onReleaseListeners)
				listener.onRelease(key, mods);
		}
	}
	
	public static void init()
	{
		keys = new Boolean[1024];
		buttons = new Boolean[128];
		for(int i = 0; i < keys.length; i++)
			keys[i] = false;
		for(int i = 0; i < buttons.length; i++)
			buttons[i] = false;
	}
	
	
	public static Boolean getKey(int keycode)
	{
		return keys[keycode];
	}
	
	
}
