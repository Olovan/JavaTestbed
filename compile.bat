cd src
del *.class
javac -cp "../src;C:\Libraries\LWJGL\jar\lwjgl.jar;C:\Libraries\LWJGL\native;C:\Libraries\jglm\target\jglm-1.0.1-SNAPSHOT.jar" Main.java
move /y ./*.class ../bin
cd ..
