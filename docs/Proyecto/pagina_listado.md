## Diseño de alta fidelidad de una página de listado
En mi proyecto, Booky, podemos encontrar varias páginas de listado, pero en este apartado vamos a analizar la pantalla
" Lecturas pendientes"

![Lecturas pendientes](../res/pendientes.png)

# Estudio de Componentes

## Layouts
**`Scaffold` (`Box`):** Define el fondo general de la aplicación con la paleta de colores vistos en el diseño aportado con anterioridad (colores cálidos y fondo negro)
* **`Column` (Contenedor Principal):** 
    1. La cabecera de la pantalla.
    2. El título ("Lecturas Pendientes").
    3. El contenedor del listado de libros.
* **`LazyColumn` o `LazyVerticalGrid`:** Es el layout para mostrar la lista de libros pendientes.

## Componentes
* **Cabecera de Título (`Text`):** Tipografía de la app (*"Booky"*) acompañada de elementos decorativos.
* **Tarjeta de Libro en Lista (`Card`):**
    * Compuesta por una **`Image`** para la portada del libro.
    * Metadatos asociados como el título del libro y el autor (**`Text`**).
* **Botones de Acción / Iconos (`IconButton`):** Elementos interactivos para seleccionar el libro.
- **Menú Contextual(`DropdownMenu` / `Box`):** Pequeña pestaña emergente que se despliega al interactuar con el libro, la cual incluye la opción **"Marcar como leído"** (que lleva a la página de reseñas).
- **Botón de Acción Flotante / Captura (`FloatingActionButton` / `Button` con icono de cámara):** Componente interactivo para hacer una foto al libro, permitiendo al usuario escanear o fotografiar nuevos libros para incorporarlos a su lista de pendientes.





