 # Donde Invierto
 
 TP Del grupo numero 4 de la materia Diseño de Sistemas

 ## Correr la aplicación
 
  - Generar los archivos fuentes del javacc.
  - Posicionarse sobre la carpeta donde-invierto.
  - Compilar la aplicación usando maven y crear un ejecutable:

  ```
  mvn clean
  mvn generate-sources
  mvn compile
  mvn package	
  ```

  Ejecutar el .jar resultante:

  ```  	
  mvn exec:java
  ```
 
 ## Correr los tests

  ```  	
  mvn test
  ``` 

 ## Estructura del repositorio
 
  - docs: documentos de la aplicación.
  - donde-invierto: código fuente de la aplicación.
