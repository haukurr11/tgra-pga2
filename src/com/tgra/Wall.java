package com.tgra;
import java.nio.FloatBuffer;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL11;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.BufferUtils;
public class Wall {
    private int row;
    private int column;
    private boolean front;
    private Texture tex;
    private FloatBuffer texCoordBuffer;
    public Wall(int column, int row, boolean front) {
        this.row = row;
        this.column = column;
        this.front = front;
        if(this.front) {
            int temp = this.row;
            this.row = this.column;
            this.column = temp;
        }
        tex = new Texture(Gdx.files.internal("assets/textures/hiresgrass.jpg"));
        texCoordBuffer = BufferUtils.newFloatBuffer(48);
        texCoordBuffer.put(new float[] {0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f,
            0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f,
            0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f,
            0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f,
            0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f,
        0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f});
        texCoordBuffer.rewind();
    }
    public int getColumn() {
        if(this.front)
        return row;
        return column;
    }
    public int getRow() {
        if(this.front)
        return column;
        return row;
    }
    public boolean isFront() {
        return front;
    }
    public int rowcol(float z) {
        return (int)(((z + 100)/200)*20);
    }
    public void preventCollision(Camera camera) {
        if(camera.eye.y < 0 || camera.eye.y > 10)
        return;
        int camera_col = rowcol(camera.eye.z);
        int camera_row = rowcol(camera.eye.x);
        if(this.front) {
            //FRONT APPROACH
            int lowZLimit = -100 + this.row*10;
            int highZLimit = lowZLimit + 10;
            int XLimit = -100 + (this.column+1)*10;
            if( (Math.ceil(camera.eye.x) == XLimit+1 || Math.ceil(camera.eye.x) == XLimit-1 || Math.ceil(camera.eye.x) == XLimit)
            && Math.ceil(camera.eye.z) <= highZLimit
            && Math.ceil(camera.eye.z) >= lowZLimit) {
                if(camera_row == this.column)
                camera.eye.x -= 1;
                else
                camera.eye.x += 1;
            }
        }
        else {
            int lowXLimit = -100 + (this.row) * 10;
            int highXLimit = lowXLimit +10;
            int ZLimit = -100 + ((this.column)*10) + 10;
            if( (Math.ceil(camera.eye.z) == ZLimit+1 || Math.ceil(camera.eye.z) == ZLimit-1|| Math.ceil(camera.eye.z) == ZLimit)
            && Math.ceil(camera.eye.x)<= highXLimit
            && Math.ceil(camera.eye.x) >= lowXLimit) {
                if(camera_col == this.column)
                camera.eye.z -= 1;
                else
                camera.eye.z += 1;
            }
        }
    }
    public void display() {
        this.display(this.row,this.column,this.front);
    }
    public void display(int row, int column,boolean across) {
        Gdx.gl11.glPushMatrix();

        Gdx.gl11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL11.GL_REPEAT);
        Gdx.gl11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL11.GL_REPEAT);
        Gdx.gl11.glEnable(GL11.GL_TEXTURE_2D);
        Gdx.gl11.glEnableClientState(GL11.GL_TEXTURE_COORD_ARRAY);
        tex.bind();  //Gdx.gl11.glBindTexture(GL11.GL_TEXTURE_2D, textureID);
        Gdx.gl11.glTexCoordPointer(2, GL11.GL_FLOAT, 0, texCoordBuffer);

        if(across) {
            row = 19-row;
        }
        row = -100+10*(row);
        column = -100+10*(column+1);
        
        if(across) {
            Gdx.gl11.glRotatef(90, 0,1, 0);
        }
        int j = 0;
        Gdx.gl11.glTranslatef(row,0f,column);
        float[] materialDiffuse = {255f, 255f, 255f, 0.0f};
        float[] materialDiffuse2 = {6.2f, 0.3f, 7.0f, 0.0f};
        float[] materialDiffuse3 = {9f, 0f, 0f, 0.0f};
        Gdx.gl11.glMaterialfv(GL11.GL_FRONT, GL11.GL_DIFFUSE, materialDiffuse, 0);
        Gdx.gl11.glDrawArrays(GL11.GL_TRIANGLE_STRIP, 4, 4);
        Gdx.gl11.glTranslatef(0,0,0.01f);
        Gdx.gl11.glNormal3f(0.0f, 1f, 0.0f);
        Gdx.gl11.glDrawArrays(GL11.GL_TRIANGLE_STRIP, 4, 4);
        j++;
        Gdx.gl11.glDisable(GL11.GL_TEXTURE_2D);
        Gdx.gl11.glDisableClientState(GL11.GL_TEXTURE_COORD_ARRAY);
        
        Gdx.gl11.glPopMatrix();
    }
}