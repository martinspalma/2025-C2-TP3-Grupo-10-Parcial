# Colaboración en Firebase (Compartir la Base de Datos)

Este documento explica cómo compartir el proyecto de Firebase/Firestore con el equipo sin exponer credenciales en el repositorio.

## 1) Dar acceso al proyecto
- Firebase Console > Project settings > Users and permissions (o IAM en Google Cloud).
- Agregar a cada integrante con su email de Google.
- Rol sugerido para desarrollo: `Firebase Admin` o `Editor` (restringir en producción).

## 2) Crear Firestore y habilitar Auth
- Firestore Database > Create database (elige región). Para dev, podés usar reglas permisivas temporales.
- Authentication > Sign-in method > habilitar “Email/Password”.

## 3) Restringir la API Key
- Google Cloud Console > APIs & Services > Credentials > elegí la API key del proyecto.
- “Restrict Key” > Application restrictions > `Android apps`.
- Agregar:
  - `Package name`: `com.ort.parcial.c2.tp3.grupo10`
  - Huellas SHA-1 y SHA-256 de cada developer (ver sección 4).
- (Opcional) API restrictions: limitar a APIs que use Firebase.

## 4) Huellas SHA de cada developer
Cada dev debe ejecutar:
```bash
cd 2025-C2-TP3-Grupo-10-Parcial
./gradlew signingReport
```
Tomar `SHA1` y `SHA-256` del variant `debug` (y `release` si corresponde) y pasarlas al owner del proyecto para agregarlas en Firebase (Project settings > Android app > “Add fingerprint”).

## 5) Distribución del google-services.json
- No se versiona. El repo tiene `app/google-services.json` ignorado.
- Opciones para compartir de forma segura:
  - Cada dev lo descarga desde Firebase Console (Project settings > Your apps > Android > “Download google-services.json”).
  - Guardarlo en un gestor de secretos (1Password, Bitwarden, Google Drive restringido) y compartir el enlace privado.
- Cada dev coloca su archivo en `app/google-services.json` localmente.

## 6) CI/CD (GitHub Actions)
Guardar el contenido del `google-services.json` en un secreto del repo (por ejemplo `GOOGLE_SERVICES_JSON_BASE64` con el archivo en Base64) y restaurarlo en build:
```yaml
- name: Restore google-services.json
  run: echo "$GOOGLE_SERVICES_JSON_BASE64" | base64 -d > app/google-services.json
  shell: bash
  env:
    GOOGLE_SERVICES_JSON_BASE64: ${{ secrets.GOOGLE_SERVICES_JSON_BASE64 }}
```

## 7) Reglas de Firestore (dev)
Usá reglas mínimas para desarrollo y endurecelas antes de producción.
```javascript
rules_version = '2';
service cloud.firestore {
  match /databases/{database}/documents {
    match /{document=**} {
      allow read, write: if request.auth != null; // Solo usuarios autenticados
    }
  }
}
```

## 8) Onboarding rápido para un dev nuevo
1. Clonar el repo.
2. Obtener acceso al proyecto Firebase.
3. Enviar SHA-1/SHA-256 del `signingReport` y esperar confirmación.
4. Descargar `google-services.json` y guardarlo en `app/` (no commitear).
5. Ejecutar: `./gradlew assembleDebug`.

## 9) Crear PR con GitHub CLI
```bash
gh auth login
git checkout -b feature/ajuste-firebase
# cambios...
git add -A && git commit -m "chore(firebase): onboarding equipo + ajustes"
git push -u origin feature/ajuste-firebase
gh pr create --fill --base main --head <tu-usuario>:feature/ajuste-firebase
```

## 10) Buenas prácticas
- No commitear `google-services.json` ni llaves/keystores.
- Mantener API keys restringidas por paquete y huellas.
- Considerar App Check en Android para evitar abuso automático.

