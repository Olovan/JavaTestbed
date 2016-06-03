#!/bin/sh
LWJGL_DIR="/home/micah/Documents/Libraries/LWJGL"
cd src
javac -cp ".:${LWJGL_DIR}/jar/lwjgl.jar:${LWJGL_DIR}/native" Demo.java  
mv ./*.class ../bin/
cd ..
