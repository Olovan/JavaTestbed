cd src
del *.class
javac -cp "../src;C:\Libraries\LWJGL\jar\lwjgl.jar;C:\Libraries\LWJGL\native;" Demo.java
move /y ./*.class ../bin
cd ..
