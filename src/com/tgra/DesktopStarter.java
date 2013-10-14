package com.tgra;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;

public class DesktopStarter
{
	public static void main(String[] args)
	{
		new LwjglApplication(new First3D_Core(), "Aron and Haukur will not let you escape!", 800, 600, false);
	}
}
