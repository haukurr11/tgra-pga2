package com.tgra;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.graphics.GL11;
import com.badlogic.gdx.utils.BufferUtils;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;


public class First3D_Core implements ApplicationListener, InputProcessor
{
	
	Camera cam;
	private boolean ligthBulbState = true;
	private boolean wiggleLights = false;
	private float wiggleValue = 1f;
	private float count = 0;
	private float speed = 30.0f;
	private MazeBase mazebase = null;
	private List<Wall> walls;
	@Override
	public void create() {
		Gdx.gl11.glEnable(GL11.GL_LIGHT2);

		Gdx.gl11.glEnable(GL11.GL_DEPTH_TEST);
		Gdx.gl11.glEnable(GL11.GL_NORMALIZE);
		Gdx.gl11.glEnable(GL11.GL_SMOOTH);
		

		
		Random random = new Random();
        this.walls = new ArrayList<Wall>();
		for(int i=0;i<20;i++)
            for(int j=0;j<20;j++)
            {
                    int rand = random.nextInt();
            this.walls.add(new Wall(i,j,rand % 2==0));
            }

		mazebase = new MazeBase(0,0,0);
		FloatBuffer mvm = BufferUtils.newFloatBuffer(100);
		Gdx.gl11.glMatrixMode(GL11.GL_MODELVIEW);
		Gdx.gl11.glLoadIdentity();
		Gdx.glu.gluLookAt(Gdx.gl11,8.0f, 5.0f, 7.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f);
		Gdx.input.setInputProcessor(this);
		
		Gdx.gl11.glEnable(GL11.GL_LIGHTING);
		Gdx.gl11.glShadeModel(GL11.GL_SMOOTH);

		Gdx.gl11.glEnable(GL11.GL_DEPTH_TEST);
		
		Gdx.gl11.glClearColor(0.0f, 0.6f, 1.0f, 1.0f);

		Gdx.gl11.glMatrixMode(GL11.GL_PROJECTION);
		Gdx.gl11.glLoadIdentity();
		Gdx.glu.gluPerspective(Gdx.gl11, 90, 1.333333f, 1.0f, 1000.0f);

		Gdx.gl11.glEnableClientState(GL11.GL_VERTEX_ARRAY);

		FloatBuffer vertexBuffer = BufferUtils.newFloatBuffer(72);
		vertexBuffer.put(new float[] {
				-100f, 0f, -100f, 
				-100f, 0f, 100f,
				100f, 0f, -100f, 
				100f, 0f, 100f});
		vertexBuffer.put(new float[] {
				0f, 0, 0f,  
				0f, 5f,0f,
				10.05f, 0,0f,  
				10.05f, 5f,0f });
		vertexBuffer.rewind();

		Gdx.gl11.glVertexPointer(3, GL11.GL_FLOAT, 0, vertexBuffer);
		cam = new Camera(new Point3D(-95.0f, 2.5f, -95.0f), new Point3D(3.0f, 2.5f, 10.0f), new Vector3D(0.0f, 1.0f, 0.0f));
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}
	
	private void update() {
		System.out.println(this.cam.eye.x + "," + this.cam.eye.y + "," + this.cam.eye.z);
		this.mazebase.preventCollision(this.cam);

		float deltaTime = Gdx.graphics.getDeltaTime();
		//System.out.println(cam.eye.x + " " + cam.eye.y + " " + cam.eye.z);

		if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) 
			cam.yaw(-90.0f * deltaTime);
		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) 
			cam.yaw(90.0f * deltaTime);
		
		if(Gdx.input.isKeyPressed(Input.Keys.W)) 
		{
				cam.slide(0.0f, 0.0f, -this.speed * deltaTime);
		}
		if(Gdx.input.isKeyPressed(Input.Keys.S)) 
			cam.slide(0.0f, 0.0f,this.speed * deltaTime);
		if(Gdx.input.isKeyPressed(Input.Keys.A)) {
			
				cam.slide(-this.speed * deltaTime, 0.0f, 0.0f);
		}
		if(Gdx.input.isKeyPressed(Input.Keys.D)) {
			cam.slide(this.speed * deltaTime, 0.0f, 0.0f);
		}
		if(Gdx.input.isKeyPressed(Input.Keys.R)) 
			cam.slide(0.0f, this.speed * deltaTime, 0.0f);
		if(Gdx.input.isKeyPressed(Input.Keys.F)) 
			cam.slide(0.0f, -this.speed * deltaTime, 0.0f);
		this.mazebase.preventCollision(this.cam);
		for(Wall wall: this.walls)
		{
			if(Math.abs(rowcol(this.cam.eye.z)-wall.getColumn()) <=1
			  && Math.abs(rowcol(this.cam.eye.x)-wall.getRow()) <=1) {
		       wall.preventCollision(this.cam);
			}
		}

	}

	public int rowcol(float z) {
		return (int)(((z + 100)/200)*20);
	}
	private void display() {
		cam.setModelViewMatrix();	

		Gdx.gl11.glClear(GL11.GL_COLOR_BUFFER_BIT|GL11.GL_DEPTH_BUFFER_BIT);

		Gdx.gl11.glEnable(GL11.GL_COLOR_MATERIAL);

		
		float[] lightPosition1 = new float[4];
		lightPosition1 = new float[4];
		lightPosition1[0] = 115;
		lightPosition1[1] = 60;
		lightPosition1[2] = 111;
		lightPosition1[3] = 1.0f;
		
		float[] lightPosition2 = new float[4];
		lightPosition2 = new float[4];
		lightPosition2[0] = -lightPosition1[0];
		lightPosition2[1] = 60;
		lightPosition2[2] = -lightPosition1[2];
		lightPosition2[3] = 1.0f;
		
		float[] lightPosition3 = new float[4];
		lightPosition3 = new float[4];
		lightPosition3[0] = -lightPosition1[0];
		lightPosition3[1] = 60;
		lightPosition3[2] = lightPosition1[2];
		lightPosition3[3] = 1.0f;
		
		float[] lightPosition4 = new float[4];
		lightPosition4 = new float[4];
		lightPosition4[0] = lightPosition1[0];
		lightPosition4[1] = 60;
		lightPosition4[2] = -lightPosition1[2];
		lightPosition4[3] = 1.0f;
		
		Gdx.gl11.glLightfv(GL11.GL_LIGHT1, GL11.GL_POSITION, lightPosition1, 0);
		Gdx.gl11.glLightfv(GL11.GL_LIGHT2, GL11.GL_POSITION, lightPosition2, 0);
		Gdx.gl11.glLightfv(GL11.GL_LIGHT3, GL11.GL_POSITION, lightPosition3, 0);
		Gdx.gl11.glLightfv(GL11.GL_LIGHT4, GL11.GL_POSITION, lightPosition4, 0);


		float[] lightDiffuse1 = {0.05f, 0.05f, 0.05f, 0.05f};

		Gdx.gl11.glLightfv(GL11.GL_LIGHT0, GL11.GL_AMBIENT, lightDiffuse1, 0);
		Gdx.gl11.glLightfv(GL11.GL_LIGHT0, GL11.GL_DIFFUSE, lightDiffuse1, 0);
		Gdx.gl11.glLightfv(GL11.GL_LIGHT0, GL11.GL_SPECULAR, lightDiffuse1, 0);
		
		Gdx.gl11.glLightfv(GL11.GL_LIGHT1, GL11.GL_SPECULAR, lightDiffuse1, 0);
		
		Gdx.gl11.glLightfv(GL11.GL_LIGHT2, GL11.GL_AMBIENT, lightDiffuse1, 0);
		Gdx.gl11.glLightfv(GL11.GL_LIGHT2, GL11.GL_DIFFUSE, lightDiffuse1, 0);
		Gdx.gl11.glLightfv(GL11.GL_LIGHT2, GL11.GL_SPECULAR, lightDiffuse1, 0);

		Gdx.gl11.glLightfv(GL11.GL_LIGHT3, GL11.GL_AMBIENT, lightDiffuse1, 0);
		Gdx.gl11.glLightfv(GL11.GL_LIGHT3, GL11.GL_DIFFUSE, lightDiffuse1, 0);
		Gdx.gl11.glLightfv(GL11.GL_LIGHT3, GL11.GL_SPECULAR, lightDiffuse1, 0);

		Gdx.gl11.glLightfv(GL11.GL_LIGHT4, GL11.GL_AMBIENT, lightDiffuse1, 0);
		Gdx.gl11.glLightfv(GL11.GL_LIGHT4, GL11.GL_DIFFUSE, lightDiffuse1, 0);
		Gdx.gl11.glLightfv(GL11.GL_LIGHT4, GL11.GL_SPECULAR, lightDiffuse1, 0);

		Gdx.gl11.glDisable(GL11.GL_COLOR_MATERIAL);
		this.mazebase.display();
		for(Wall wall: this.walls)
		    wall.display();
		Gdx.gl11.glEnable(GL11.GL_COLOR_MATERIAL);
	}

	@Override
	public void render() {
		update();
		display();
	}

	@Override
	public void resize(int arg0, int arg1) {
	}

	@Override
	public void resume() {
	}

	@Override
	public boolean keyDown(int arg0) {
		return false;
	}

	@Override
	public boolean keyTyped(char arg0) {
		return false;
	}

	@Override
	public boolean keyUp(int arg0) {

		return false;
	}

	public boolean scrolled(int arg0) {
		return false;
	}

	@Override
	public boolean touchDown(int arg0, int arg1, int arg2, int arg3) {
		return false;
	}

	@Override
	public boolean touchDragged(int arg0, int arg1, int arg2) {
		return false;
	}

	@Override
	public boolean touchUp(int arg0, int arg1, int arg2, int arg3) {
		return false;
	}

	@Override
	public boolean touchMoved(int arg0, int arg1) {
		return false;
	}
}
