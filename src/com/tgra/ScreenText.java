package com.tgra;


import java.nio.FloatBuffer;

import org.lwjgl.opengl.GL11;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.BufferUtils;

/**
 * This class creates all the text you can see in the game as {@link GraphicObject}. It is
 * used for the score which the player has earned through the gameplay, as well display what
 * level he is on.
 */

public class ScreenText
{
    private String text;
    private float x;
    private float y;
    private FloatBuffer vertexBuffer; 

	private Texture tex;
	private FloatBuffer texCoordBuffer;
	
    /**
     * A constructor which creates a graphical object along with a string for the text to
     * be displayed.
     * 
     * @param x		Position of the text on the x-grid.
     * @param y		Position of the text on the y-grid.
     * @param text	The text which is displayed.
     * @param vb	The vertex buffer which the objected is inserted.
     */
    public ScreenText(float x,float y,String text,FloatBuffer vb)
    {
        this.text = text;
        this.x = x;
        this.y = y;
        this.vertexBuffer = vb;
        texCoordBuffer = BufferUtils.newFloatBuffer(48);
        texCoordBuffer.put(new float[] {0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f,
            0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f,
            0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f,
            0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f,
            0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f,
        0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f});
        texCoordBuffer.rewind();
	    tex = new Texture(Gdx.files.internal("assets/textures/grass.png"));

    }
    
    /**
     * A constructor similar to the one before, but includes no text.
     * 
     * @param x		Position of the text on the x-grid.
     * @param y		Position of the text on the y-grid.
     * @param text	The text which is displayed.
     * @param vb	The vertex buffer which the objected is inserted.
     */
    public ScreenText(int x,int y,FloatBuffer vb)
    {
        this.text = "";
    }
    
    /**
     * {@inheritDoc}
     */
    public void display()
    {

    	//Setup for 2D
    	Gdx.gl11.glMatrixMode(GL11.GL_PROJECTION);
    	
    	Gdx.gl11.glLoadIdentity();
    	Gdx.gl11.glOrthof(0, 800, 400, 0, -1, 1);

    	Gdx.gl11.glMatrixMode(GL11.GL_MODELVIEW);
    	Gdx.gl11.glLoadIdentity();

    	Gdx.gl11.glClear(GL11.GL_COLOR_BUFFER_BIT);
    	Gdx.gl11.glDrawArrays(GL11.GL_TRIANGLE_STRIP, 0, 4);

    }
    
    /**
     * Getter function for the text message.
     * @return	The text to be displayed.
     */
    public String getText()
    {
        return text;
    }
    
    /**
     * Setter function for the text message.
     * @param text	The text to be displayed.
     */
    public void setText(String text)
    {
        this.text = text;
    }

}