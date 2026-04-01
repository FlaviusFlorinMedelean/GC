Laborator 4 - 

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
