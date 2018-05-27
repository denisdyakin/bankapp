# bankapp

Instruction:
1. clone project
2. go to project's root folder
3. in terminal: mvn clean package
4. in terminal: cd target
5. in terminal: java -jar -Dserver.port={your free port / 8087 by default}  bankapp.jar
6. go to a browser: http://localhost:{your free port / 8087 by default}/swagger-ui.html#/bank-info-controller
7. choose any method "Try it out" and returned content type (json / xml). 
    Data for testing: customer ids - 1, 2, 3
