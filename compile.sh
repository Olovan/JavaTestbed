#!/bin/sh
cd src
javac -cp ".:/home/micah/Documents/Libraries/LWJGL/jar/lwjgl.jar:/home/micah/Documents/Libraries/LWJGL/native" Demo.java  
mv ./*.class ../bin/
cd ..
