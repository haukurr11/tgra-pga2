package com.tgra;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL11;

public class Wall {
	float x;
	float y;
	float z;
	public Wall(float x, float y , float z) {
		this.x=x;
		this.y=y;
		this.z=z;
	}
	public void display() {
		Gdx.gl11.glTranslatef(this.x,this.y,this.z);
		Gdx.gl11.glScalef(1f, 0.02f, 0.1f);
		Gdx.gl11.glRotatef(90, 0,0, 1);
		Gdx.gl11.glRotatef(90, 0,0, 1);

		float[] materialDiffuse = {5f, 1.3f, 6.0f, 0.0f};
		Gdx.gl11.glMaterialfv(GL11.GL_FRONT, GL11.GL_AMBIENT, materialDiffuse, 0);
		Gdx.gl11.glDrawArrays(GL11.GL_TRIANGLE_STRIP, 0, 4);
	}
}
