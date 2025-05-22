# Sakura Memory Game 🎴

![Java](https://img.shields.io/badge/Java-17%2B-blue?logo=java)
![Swing](https://img.shields.io/badge/GUI-Swing-orange)
![MySQL](https://img.shields.io/badge/DB-MySQL-4479A1?logo=mysql)
![Clean Code](https://img.shields.io/badge/Principios-Clean_Code-brightgreen)
![SOLID](https://img.shields.io/badge/Arquitectura-SOLID-ff69b4)

**Sakura Memory** es un juego educativo de cartas diseñado para mejorar habilidades cognitivas mediante mecánicas de memoria visual. Desarrollado en Java, combina una interfaz intuitiva con un backend robusto, siguiendo estándares profesionales de desarrollo de software.

---

## 🛠 Habilidades y Conocimientos Aplicados

### **Arquitectura y Principios de Diseño**
- **Principios SOLID**: Separación clara de responsabilidades (SRP, DIP) en módulos independientes.
- **Patrón Builder**: Configuración flexible del juego mediante `GameConfig`.
- **MVC (Modelo-Vista-Controlador)**: Estructura modular para escalabilidad.
- **Manejo de Excepciones**: Custom exceptions (`GameInitializationException`) para errores críticos.

### **Frontend Avanzado**
- **Java Swing**: Interfaz gráfica responsive con:
  - Grids dinámicos para cartas.
  - Animaciones de volteo y feedback visual (acierto/error).
  - Temporizador integrado con `javax.swing.Timer`.
- **Diseño Adaptativo**: Paletas de colores duales (modos Simple/Avanzado) y tipografías legibles (Roboto).

### **Backend y Persistencia**
- **Conexión a MySQL**: CRUD de usuarios y puntuaciones mediante `DatabaseConnector`.
- **Gestión de Estado**: Lógica de juego en `GameMechanics` y scoring en `ScoringSystem`.
- **Serialización**: Configuración persistente con `SettingsManager`.

### **Optimización y Calidad**
- **Clean Code**: Nombrado semántico, métodos concisos (<15 líneas), documentación interna.
- **Pruebas Manuales**: Validación de flujos de usuario (login, registro, gameplay).
- **Gestión de Recursos**: Carga eficiente de imágenes con `ImageLoader` (scaling en tiempo real).

---

## 🚀 Características Clave

| Módulo           | Detalle                                                                 |
|------------------|-------------------------------------------------------------------------|
| **Autenticación**| Registro/login con validación en base de datos.                        |
| **Gameplay**     | 5 cartas interactivas, volteo con límite de 2 selecciones, feedback visual. |
| **Scoring**      | Sistema de puntos con bonificaciones por retos (cada 100 puntos).      |
| **Ranking**      | Tabla de líderes con TOP 10 puntuaciones históricas.                   |
| **Personalización**| Temas visuales y ajustes de dificultad.                              |

---

## 🖥 Tecnologías Utilizadas

| Categoría         | Herramientas                                                                 |
|-------------------|-----------------------------------------------------------------------------|
| **Lenguaje**      | Java 17                                                                     |
| **GUI**           | Java Swing, AWT                                                             |
| **Base de Datos** | MySQL 8.0                                                                   |
| **Patrones**      | Builder, Singleton, Observer                                                |
| **Herramientas**  | Maven, IntelliJ IDEA, Git                                                   |

---

## 📦 Instalación

1. **Clonar Repositorio**:
   ```bash
   git clone https://github.com/tu-usuario/sakura-memory-game.git
