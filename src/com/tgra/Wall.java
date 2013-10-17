package com.tgra;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL11;

public class Wall {
	float x;
	float y;
	float z;
	public Wall() {
	}
	public void display() {
		int j = 0;
		Gdx.gl11.glTranslatef(-0.5f,-0.5f,0f);

		for(int k=-90;k<100;k+=10) {
		for(float i=-100f;i<100;i+=10.05f) {
		Gdx.gl11.glPushMatrix();

		Gdx.gl11.glTranslatef(i,0f,k);

		float[] materialDiffuse = {0.2f, 7.3f, 7.0f, 0.0f};
		float[] materialDiffuse2 = {6.2f, 0.3f, 7.0f, 0.0f};
        if(j % 2 == 0)
		  Gdx.gl11.glMaterialfv(GL11.GL_FRONT, GL11.GL_AMBIENT, materialDiffuse, 0);
        else
          Gdx.gl11.glMaterialfv(GL11.GL_FRONT, GL11.GL_AMBIENT, materialDiffuse2, 0);
		Gdx.gl11.glDrawArrays(GL11.GL_TRIANGLE_STRIP, 4, 4);
		Gdx.gl11.glPopMatrix();
		j++;
		}
		}
		Gdx.gl11.glRotatef(90, 0,1, 0);
		for(int k=-90;k<100;k+=10) {
		for(float i=-100f;i<100;i+=10.05f) {
		Gdx.gl11.glPushMatrix();

		Gdx.gl11.glTranslatef(i,0f,k);

		float[] materialDiffuse = {0.2f, 7.3f, 7.0f, 0.0f};
		float[] materialDiffuse2 = {6.2f, 0.3f, 7.0f, 0.0f};
        if(j % 2 == 0)
		  Gdx.gl11.glMaterialfv(GL11.GL_FRONT, GL11.GL_AMBIENT, materialDiffuse, 0);
        else
          Gdx.gl11.glMaterialfv(GL11.GL_FRONT, GL11.GL_AMBIENT, materialDiffuse2, 0);
		Gdx.gl11.glDrawArrays(GL11.GL_TRIANGLE_STRIP, 4, 4);
		Gdx.gl11.glPopMatrix();
		j++;
		}
		}
	}
}
