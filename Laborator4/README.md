Laborator 4 - 3

Descriere
Aplicatie Android care deseneaza un cub 3D folosind OpenGL ES.

Modificari realizate

1. Schimbarea ordinii transformărilor
- Rotațiile aplicate înainte de translație modifică mișcarea cubului.

2. Scalare (glScalef)
- Cubul devine mai înalt pe axa Y.

3. Modificare zFar = 6.0
- Cubul dispare deoarece este în afara volumului de vizualizare.

4. Modificare zNear = 6.0
- Cubul este tăiat parțial.

5. Translație la z = -20
- Cubul apare mai mic (mai departe de cameră).

6. Field of View = 10
- Efect de zoom (câmp vizual mai îngust).

7. Backface culling
- Fețele din față nu mai sunt desenate.


  CubeRenderer.java

// muta cubul sus-jos pe axa Y si il pozitioneaza mai departe pe axa Z
gl.glTranslatef(0.0f, (float) Math.sin(mTransY), -20.0f);

// rotatie pe axa Y
gl.glRotatef(mAngle, 0.0f, 1.0f, 0.0f);

// rotatie pe axa X
gl.glRotatef(mAngle, 1.0f, 0.0f, 0.0f);

// scalare pe axa Y (dubleaza inaltimea cubului)
gl.glScalef(1.0f, 2.0f, 1.0f);

// cresterea unghiului pentru animatia de rotatie
mAngle += 0.4f;


CubeRenderer.java → metoda onSurfaceChanged()

// modificare camp vizual (Field of View)
float fieldOfView = 10.0f / 57.3f;

// modificare plan apropiat
float zNear = 6.0f;

// modificare plan departat
float zFar = 1000.0f;

// configurarea viewing frustum
gl.glFrustumf(-size, size, -size / aspectRatio, size / aspectRatio, zNear, zFar);

CubeRenderer.java → metoda onSurfaceCreated()

// activare backface culling
gl.glEnable(GL10.GL_CULL_FACE);

// eliminare fete frontale
gl.glCullFace(GL10.GL_FRONT);

Cube.java → metoda draw()

// desenare primul triangle fan
gl.glDrawElements(GL10.GL_TRIANGLE_FAN, 6 * 3, GL10.GL_UNSIGNED_BYTE, mTFan1);

// desenare al doilea triangle fan
gl.glDrawElements(GL10.GL_TRIANGLE_FAN, 6 * 3, GL10.GL_UNSIGNED_BYTE, mTFan2);
