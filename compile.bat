cd src
del *.class
javac -cp "../src;C:\Libraries\LWJGL\jar\lwjgl.jar;C:\Libraries\LWJGL\native;" Main.java
move /y ./*.class ../bin
cd ..
