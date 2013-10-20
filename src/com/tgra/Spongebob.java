package com.tgra;

import java.nio.FloatBuffer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL11;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.BufferUtils;

public class Spongebob {
	private FloatBuffer fb;
    private Texture tex;
    private Texture tex2;
    private Texture tex3;
    private int column;
    private int row;
    private FloatBuffer texCoordBuffer;
    
	public Spongebob(int column,int row) {
		this.fb = fb;
		 tex = new Texture(Gdx.files.internal("assets/textures/spongebob.png"));
		 tex2 = new Texture(Gdx.files.internal("assets/textures/sidebob.png"));
		 tex3 = new Texture(Gdx.files.internal("assets/textures/shoe.png"));

	        texCoordBuffer = BufferUtils.newFloatBuffer(48);
	        texCoordBuffer.put(new float[] {0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f,
	            0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f,
	            0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f,
	            0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f,
	            0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f,
	        0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f});
	        texCoordBuffer.rewind();
	        this.column=column;
	        this.row = row;
	}
    public void display() {
       	Gdx.gl11.glPushMatrix();

    	Gdx.gl11.glTranslatef(100f-this.column*10f-4f,-0f,-100f+this.row*10f+5f);
    	Gdx.gl11.glScalef(0.5f,0.5f,0.5f);
    	Gdx.gl11.glRotatef(90,0,1,0);

       	Gdx.gl11.glPushMatrix();
    	Gdx.gl11.glScalef(0.1f,0.1f,0.05f);
    	drawCube();
    	Gdx.gl11.glTranslatef(0f,0f,-40f);
    	drawCube();
       	Gdx.gl11.glPopMatrix();
       	
       	Gdx.gl11.glPushMatrix();
    	Gdx.gl11.glTranslatef(0f,0.5f,0f);
       	Gdx.gl11.glScalef(0.01f,0.5f,0.05f);
       	drawCube();
       	Gdx.gl11.glTranslatef(10f,0f,-40f);
       	drawCube();
       	Gdx.gl11.glPopMatrix();

        Gdx.gl11.glDisable(GL11.GL_TEXTURE_2D);
        Gdx.gl11.glDisableClientState(GL11.GL_TEXTURE_COORD_ARRAY);

        Gdx.gl11.glTexCoordPointer(2, GL11.GL_FLOAT, 0, texCoordBuffer);
      	Gdx.gl11.glPushMatrix();
    	Gdx.gl11.glTranslatef(-1f,2f,0.2f);
      	Gdx.gl11.glScalef(0.2f,0.8f,0.3f);
      	spongebob();
      	Gdx.gl11.glPopMatrix();
      	Gdx.gl11.glPopMatrix();

        
    }
    
    
    
    public void drawCube() {
        Gdx.gl11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL11.GL_REPEAT);
        Gdx.gl11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL11.GL_REPEAT);
        Gdx.gl11.glEnable(GL11.GL_TEXTURE_2D);
        Gdx.gl11.glEnableClientState(GL11.GL_TEXTURE_COORD_ARRAY);
        tex3.bind();  //Gdx.gl11.glBindTexture(GL11.GL_TEXTURE_2D, textureID);

		float[] materialDiffuse = {255f, 0f, 0.0f, 0.0f};
		Gdx.gl11.glMaterialfv(GL11.GL_FRONT, GL11.GL_DIFFUSE, materialDiffuse, 0);
       	Gdx.gl11.glPushMatrix();
        Gdx.gl11.glDrawArrays(GL11.GL_TRIANGLE_STRIP, 4, 4);
        Gdx.gl11.glRotatef(90,0,1, 0);
        Gdx.gl11.glDrawArrays(GL11.GL_TRIANGLE_STRIP, 4, 4);
        Gdx.gl11.glRotatef(90,0,1, 0);
        Gdx.gl11.glTranslatef(-10f,0f,10f);
        Gdx.gl11.glDrawArrays(GL11.GL_TRIANGLE_STRIP, 4, 4);
        Gdx.gl11.glRotatef(90,0,1, 0);
        Gdx.gl11.glDrawArrays(GL11.GL_TRIANGLE_STRIP, 4, 4);
        Gdx.gl11.glPopMatrix();

    	Gdx.gl11.glPushMatrix();

        Gdx.gl11.glRotatef(90,-1,0, 0);
        Gdx.gl11.glDrawArrays(GL11.GL_TRIANGLE_STRIP, 4, 4);
        Gdx.gl11.glTranslatef(0f,5f,0f);
        Gdx.gl11.glDrawArrays(GL11.GL_TRIANGLE_STRIP, 4, 4);
        Gdx.gl11.glPopMatrix();
        
        
    	Gdx.gl11.glPushMatrix();

        Gdx.gl11.glRotatef(90,-1,0, 0);
        Gdx.gl11.glTranslatef(0f,0f,5f);
        Gdx.gl11.glDrawArrays(GL11.GL_TRIANGLE_STRIP, 4, 4);
        Gdx.gl11.glTranslatef(0f,5f,0f);
        Gdx.gl11.glDrawArrays(GL11.GL_TRIANGLE_STRIP, 4, 4);
        
    	Gdx.gl11.glPopMatrix();
    }
    public void spongebob() {
        Gdx.gl11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL11.GL_REPEAT);
        Gdx.gl11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL11.GL_REPEAT);
        Gdx.gl11.glEnable(GL11.GL_TEXTURE_2D);
        Gdx.gl11.glEnableClientState(GL11.GL_TEXTURE_COORD_ARRAY);
        tex2.bind();  //Gdx.gl11.glBindTexture(GL11.GL_TEXTURE_2D, textureID);

       	Gdx.gl11.glPushMatrix();
        Gdx.gl11.glDrawArrays(GL11.GL_TRIANGLE_STRIP, 4, 4);

        Gdx.gl11.glRotatef(90,0,1, 0);
        Gdx.gl11.glDrawArrays(GL11.GL_TRIANGLE_STRIP, 4, 4);
        Gdx.gl11.glRotatef(90,0,1, 0);
        Gdx.gl11.glTranslatef(-10f,0f,10f);
        Gdx.gl11.glDrawArrays(GL11.GL_TRIANGLE_STRIP, 4, 4);
        Gdx.gl11.glRotatef(90,0,1, 0);


        Gdx.gl11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL11.GL_REPEAT);
        Gdx.gl11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL11.GL_REPEAT);
        Gdx.gl11.glEnable(GL11.GL_TEXTURE_2D);
        Gdx.gl11.glEnableClientState(GL11.GL_TEXTURE_COORD_ARRAY);
        tex.bind();  //Gdx.gl11.glBindTexture(GL11.GL_TEXTURE_2D, textureID);
        Gdx.gl11.glTexCoordPointer(2, GL11.GL_FLOAT, 0, texCoordBuffer);

        Gdx.gl11.glDrawArrays(GL11.GL_TRIANGLE_STRIP, 4, 4);
        Gdx.gl11.glPopMatrix();
        Gdx.gl11.glDisable(GL11.GL_TEXTURE_2D);
        Gdx.gl11.glDisableClientState(GL11.GL_TEXTURE_COORD_ARRAY);

        

        Gdx.gl11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL11.GL_REPEAT);
        Gdx.gl11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL11.GL_REPEAT);
        Gdx.gl11.glEnable(GL11.GL_TEXTURE_2D);
        Gdx.gl11.glEnableClientState(GL11.GL_TEXTURE_COORD_ARRAY);
        tex2.bind();  //Gdx.gl11.glBindTexture(GL11.GL_TEXTURE_2D, textureID);
        Gdx.gl11.glTexCoordPointer(2, GL11.GL_FLOAT, 0, texCoordBuffer);

    	Gdx.gl11.glPushMatrix();


    	Gdx.gl11.glRotatef(90,-1,0, 0);

    	Gdx.gl11.glDrawArrays(GL11.GL_TRIANGLE_STRIP, 4, 4);

    	Gdx.gl11.glTranslatef(0f,5f,0f);
        tex2.bind();  //Gdx.gl11.glBindTexture(GL11.GL_TEXTURE_2D, textureID);

    	Gdx.gl11.glDrawArrays(GL11.GL_TRIANGLE_STRIP, 4, 4);
        Gdx.gl11.glPopMatrix();
        
        
    	Gdx.gl11.glPushMatrix();
        Gdx.gl11.glRotatef(90,-1,0, 0);
        Gdx.gl11.glTranslatef(0f,0f,5f);
        Gdx.gl11.glDrawArrays(GL11.GL_TRIANGLE_STRIP, 4, 4);
        Gdx.gl11.glTranslatef(0f,5f,0f);

        
        Gdx.gl11.glDrawArrays(GL11.GL_TRIANGLE_STRIP, 4, 4);
        
    	Gdx.gl11.glPopMatrix();

        Gdx.gl11.glDisable(GL11.GL_TEXTURE_2D);
        Gdx.gl11.glDisableClientState(GL11.GL_TEXTURE_COORD_ARRAY);

    }
    
}
