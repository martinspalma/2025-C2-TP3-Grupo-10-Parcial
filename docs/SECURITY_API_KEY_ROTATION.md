# Incidente: clave de API expuesta (Firebase/Google APIs)

Este playbook detalla cómo responder a la alerta de Google Cloud por clave expuesta y cómo normalizar el repo para evitar futuras filtraciones.

## 1) Impacto y contexto
- Almacenamos `app/google-services.json` en el repo, lo que expuso la clave de API (Firebase Web/Android API key). Aunque las API keys de Firebase no son “secretos” en el mismo sentido que credenciales de servidor, Google Cloud puede alertar si son de uso general y no están restringidas.

## 2) Contención inmediata
- Restringir o rotar la clave:
  1. Ir a Google Cloud Console > APIs & Services > Credentials.
  2. Ubicar la API key indicada en la alerta.
  3. Opción A (recomendada): “Restrict Key” → Tipo: `Android apps` → agregar:
     - `Package name`: `com.ort.parcial.c2.tp3.grupo10`
     - SHA-1 y SHA-256 del keystore de debug y release (si aplica).
  4. Opción B: “Regenerate Key” (rotar) y luego aplicar las restricciones del punto 3.
  5. Revisar “API restrictions” y limitar a APIs usadas por Firebase si es posible.

> Nota: Para obtener SHA-1/256 debug: `./gradlew signingReport` (module `app`).

## 3) Actualizar configuración en el proyecto
- En Firebase Console > Project settings > tu app Android:
  - Agregar las huellas SHA-1/256 si aún no están.
  - Descargar el nuevo `google-services.json` y reemplazarlo localmente (NO en el repo).

## 4) Cambios en el repo (hechos en esta rama)
- `app/google-services.json` eliminado del control de versiones.
- `.gitignore` ahora ignora `app/google-services.json`.
- Se agregó `app/google-services.sample.json` con placeholders.
- `README.md` actualizado con instrucciones para colocar el JSON localmente.

## 5) Limpieza del historial del repo (opcional pero recomendado)
Si la clave quedó en commits ya publicados, se recomienda purgar el archivo del historial. Opciones:

### Opción A: git filter-repo (recomendada)
1. Instalar: https://github.com/newren/git-filter-repo
2. Ejecutar desde la raíz del repo:
   ```bash
   git filter-repo --path app/google-services.json --invert-paths
   git push --force --all
   git push --force --tags
   ```
3. Avisar a los colaboradores que deben re-clonar o hacer `git fetch --all` y resetear sus ramas locales.

### Opción B: BFG Repo-Cleaner
1. https://rtyley.github.io/bfg-repo-cleaner/
2. Similar al paso A, luego `git push --force`.

> Importante: después de purgar, la clave debe estar ya rotada/restringida (Sección 2).

## 6) Verificación
- Android Studio: Sync + Build `./gradlew assembleDebug`.
- Ejecutar la app. Registro/login deberían funcionar. Verifica:
  - Auth habilitado (Email/Password) en Firebase.
  - Firestore creado (Database en Firebase Console) y accesible.

## 7) Crear Pull Request con GitHub CLI
Asumiendo que tenés tu fork configurado como `origin` y el repo original como `upstream`, y que estás en la rama `Ivan_Zarate`:

```bash
gh auth login                             # si no estás autenticado
git fetch upstream
git rebase upstream/main                  # opcional, traer últimos cambios
git push -u origin Ivan_Zarate            # asegurar rama actualizada en tu fork
gh pr create --fill --base main --head IAZARA:Ivan_Zarate
```

Si no usás `gh`, podés crear el PR desde la web:
`https://github.com/MaraiNicolas/2025-C2-TP3-Grupo-10-Parcial/compare/main...IAZARA:Ivan_Zarate?expand=1`

## 8) Mensaje sugerido para el equipo
> Detectamos que el archivo `app/google-services.json` expuso una API key. Ya removimos el archivo del repo, agregamos `.gitignore`, y dejamos un `google-services.sample.json` de referencia. Por favor, colocad vuestro `google-services.json` real localmente (no lo suban). Además, restringimos/rotamos la clave en Google Cloud. Si hiciste clones recientes, puede que necesites volver a clonar tras la limpieza del historial.

## 9) Buenas prácticas a futuro
- Nunca versionar `google-services.json` ni otros secretos.
- Mantener API keys restringidas a paquete y huellas.
- Revisar alertas de Cloud regularmente (Cloud Logging/Monitoring).

