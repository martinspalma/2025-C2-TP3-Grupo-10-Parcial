# 2025-C2-TP3-Grupo-10-Parcial

App Android nativa con Jetpack Compose y Firebase Firestore para el parcial de TP3.

## Integrantes
- Gabriel Birman
- Iván Schutt
- Jonathan Vitangeli
- Martin Palma
- Ivan Zarate
- Marai Nicolás

## ¿Qué hace?
- Navegación entre pantallas (Welcome, Login, Register)
- Base de datos en tiempo real con Firebase
- Arquitectura MVVM con inyección de dependencias

## Tecnologías
- **Kotlin + Jetpack Compose**: UI moderna y declarativa
- **Hilt**: Inyección de dependencias
- **Firebase Firestore**: Base de datos NoSQL en la nube
- **Navigation Component**: Navegación entre pantallas
- **Coroutines & Flow**: Programación asíncrona reactiva

## Configuración rápida
1. Clonar el repositorio
2. Abrir en Android Studio (versión Hedgehog o superior)
3. Configurar Firebase:
   - Copiar tu archivo `google-services.json` (descargado de Firebase) a `app/google-services.json`.
   - No se versiona. Si necesitás un ejemplo, ver `app/google-services.sample.json`.

Consulta también:
- `docs/SECURITY_API_KEY_ROTATION.md` — Rotación/restricción de API key y limpieza de historial.
- `docs/FIREBASE_TEAM_SETUP.md` — Cómo compartir la base con el equipo (accesos, SHA, CI, reglas).
4. Sync del proyecto y listo para compilar

## Comandos útiles
```bash
./gradlew clean assembleDebug    # Compilar la app
./gradlew testDebugUnitTest      # Ejecutar tests unitarios
./gradlew installDebug           # Instalar en dispositivo/emulador
./gradlew lint                   # Verificar calidad del código
```

## Requisitos
- Android Studio Hedgehog o superior
- Android SDK 27 o superior
- Emulador (API 27+) o dispositivo físico
- Conexión a internet para Firebase

## Estructura del proyecto
```
app/src/main/java/com/ort/parcial/c2/tp3/grupo10/
├── data/
│   ├── repository/          # Implementación del repositorio
│   └── di/                 # Módulos de inyección (Firebase)
├── domain/
│   └── repository/          # Interfaces del dominio
├── ui/
│   ├── screens/             # Pantallas principales
│   ├── items/viewmodel/      # ViewModel para items
│   └── theme/              # Tema y estilos
└── MainActivity.kt
```

## Firebase
- **Proyecto**: parcial-grupo-10
- **Colección**: items (para guardar datos de ejemplo)
- **Configuración**: Lista para usar en tiempo real

## Contribuir
1. Hacer fork del proyecto
2. Crear rama para la feature: `git checkout -b feature/nueva-funcionalidad`
3. Commits descriptivos en español
4. Push y crear pull request

## Licencia
Proyecto educativo para el parcial de TP3 - ORT 2025
