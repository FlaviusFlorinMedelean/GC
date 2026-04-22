package com.example.bouncycube;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLSurfaceView;

public class CubeRenderer implements GLSurfaceView.Renderer {

    public final static int SS_SUNLIGHT = GL10.GL_LIGHT0;

    private Cube mCube;
    private float mTransY;
    private float mAngle;

    public CubeRenderer() {
        mCube = new Cube();
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
        gl.glLoadIdentity();

        gl.glTranslatef(0.0f, (float) Math.sin(mTransY), -20.0f);
        gl.glRotatef(mAngle, 0.0f, 1.0f, 0.0f);
        gl.glRotatef(mAngle, 1.0f, 0.0f, 0.0f);
        gl.glScalef(1.0f, 2.0f, 1.0f);

        mCube.draw(gl);

        mTransY += 0.075f;
        mAngle += 0.4f;
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        gl.glViewport(0, 0, width, height);

        float fieldOfView = 10.0f / 57.3f;
        float aspectRatio = (float) width / (float) height;
        float zNear = 6.0f;
        float zFar = 1000.0f;
        float size = zNear * (float) (Math.tan((double) (fieldOfView / 2.0f)));

        gl.glMatrixMode(GL10.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glFrustumf(-size, size, -size / aspectRatio, size / aspectRatio, zNear, zFar);
        gl.glMatrixMode(GL10.GL_MODELVIEW);
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        gl.glEnable(GL10.GL_DEPTH_TEST);
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
        gl.glShadeModel(GL10.GL_SMOOTH);
        gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);

        gl.glEnable(GL10.GL_CULL_FACE);
        gl.glCullFace(GL10.GL_BACK);

        initLighting(gl);
    }

    private void initLighting(GL10 gl) {
        float[] white  = {1.0f, 1.0f, 1.0f, 1.0f};
        float[] red    = {1.0f, 0.0f, 0.0f, 1.0f};
        float[] green  = {0.0f, 1.0f, 0.0f, 1.0f};
        float[] blue   = {0.0f, 0.0f, 1.0f, 1.0f};

        // EXPERIMENT 4: pozitia luminii la 50.0f - lumina e mai departe
        // cubul va arata mai intunecat
        float[] position = {50.0f, 0.0f, 3.0f, 1.0f};

        gl.glLightfv(SS_SUNLIGHT, GL10.GL_POSITION, makeFloatBuffer(position));
        gl.glLightfv(SS_SUNLIGHT, GL10.GL_DIFFUSE,  makeFloatBuffer(white));
        gl.glLightfv(SS_SUNLIGHT, GL10.GL_SPECULAR, makeFloatBuffer(red));
        gl.glLightfv(SS_SUNLIGHT, GL10.GL_AMBIENT,  makeFloatBuffer(blue));

        gl.glEnable(GL10.GL_LIGHTING);
        gl.glEnable(SS_SUNLIGHT);

        gl.glMaterialfv(GL10.GL_FRONT_AND_BACK, GL10.GL_DIFFUSE,  makeFloatBuffer(green));
        gl.glMaterialfv(GL10.GL_FRONT_AND_BACK, GL10.GL_SPECULAR, makeFloatBuffer(red));
        gl.glMaterialfv(GL10.GL_FRONT_AND_BACK, GL10.GL_AMBIENT,  makeFloatBuffer(blue));
        gl.glMaterialf (GL10.GL_FRONT_AND_BACK, GL10.GL_SHININESS, 25);

        gl.glLightf(SS_SUNLIGHT, GL10.GL_LINEAR_ATTENUATION, 0.025f);

        float[] worldAmbient = {0.2f, 0.2f, 0.2f, 1.0f};
        gl.glLightModelfv(GL10.GL_LIGHT_MODEL_AMBIENT, makeFloatBuffer(worldAmbient));
    }

    protected static FloatBuffer makeFloatBuffer(float[] array) {
        ByteBuffer bb = ByteBuffer.allocateDirect(array.length * 4);
        bb.order(ByteOrder.nativeOrder());
        FloatBuffer fb = bb.asFloatBuffer();
        fb.put(array);
        fb.position(0);
        return fb;
    }
}