# Arquitecturas Android — MVC, MVP y MVVM

Una misma aplicación Android en Kotlin, escrita tres veces con tres arquitecturas distintas para compararlas. La aplicación consulta la API de cupones de [LinkMyDeals](https://linkmydeals.com/), muestra las ofertas en una lista y abre el detalle de cada una.

## Ramas

Cada arquitectura está en su propia rama; no se fusionan entre sí.

| Rama | Contenido |
|---|---|
| [`main`](../../tree/main) | Código base: pantallas, modelo `Coupon` y adaptador de la lista, sin la llamada a la API. `BaseCode.kt` guarda los fragmentos de partida. |
| [`mvc`](../../tree/mvc) | Modelo-Vista-Controlador: la actividad hace de vista y de controlador. |
| [`mvp`](../../tree/mvp) | Modelo-Vista-Presentador: vista, presentador, interactor y repositorio separados por interfaces. |
| [`mvvm`](../../tree/mvvm) | Modelo-Vista-VistaModelo con `ViewModel`, `LiveData` y Data Binding. |

```bash
git checkout mvvm
```

En `main` la aplicación abre la pantalla principal vacía, porque todavía no llama a la API. En `mvvm` la lista se muestra con Data Binding y las tarjetas no abren el detalle.

## Tecnologías

- Kotlin 1.8 y Android Gradle Plugin 7.4 (Gradle 7.6)
- Retrofit 2 con Gson para la API
- Picasso y CircleImageView para las imágenes
- `ViewModel`, `LiveData` y Data Binding (rama `mvvm`)
- JUnit 4 para las pruebas del modelo

## Requisitos

- JDK 11
- Android SDK con la plataforma 31 (Android Studio lo instala)
- Una clave de la API de LinkMyDeals

## Configuración de la clave

La clave no está en el código. Añádela al archivo `local.properties` de la raíz del proyecto, que no se sube al repositorio:

```properties
LINKMYDEALS_API_KEY=tu_clave
```

Sin la clave el proyecto compila, pero la API no devuelve cupones.

## Cómo ejecutarlo

Abre el proyecto en Android Studio y ejecútalo en un emulador o dispositivo, o desde la terminal:

```bash
./gradlew assembleDebug
```

Para las pruebas unitarias:

```bash
./gradlew testDebugUnitTest
```

## Estructura

```
app/src/main/java/com/example/apparquitectura/
  model/        Coupon, acceso a la API y repositorio
  view/         Actividades y adaptador de la lista
  presenter/    Presentador (rama mvp)
  viewmodel/    ViewModel (rama mvvm)
app/src/main/res/layout/
  activity_main.xml            Lista de cupones
  card_coupon.xml              Tarjeta de un cupón
  activity_coupon_detail.xml   Detalle de un cupón
```

En `main` y `mvc` las clases están directamente en el paquete raíz.

## Autor

Leonardo Medina — [github.com/leomedinadev](https://github.com/leomedinadev)
