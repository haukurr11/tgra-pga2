package com.tgra;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL11;

public class Wall {
	float x;
	float y;
	float z;
	public Wall() {
	}
	public void display(int row, int column,boolean across) {
		if(across) {
			row = 19-row;
		}
		Gdx.gl11.glPushMatrix();

		  row = -100+10*(row);
		  column = -100+10*(column+1);
			if(across) {
				  Gdx.gl11.glRotatef(90, 0,1, 0);
				}

		int j = 0;

		for(int k=-100;k<100;k+=10) {
			if(k != column)
				continue;
		for(int i=-100;i<100;i+=10) {
			if(i != row)
				continue;
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
		Gdx.gl11.glPopMatrix();
	}
}
