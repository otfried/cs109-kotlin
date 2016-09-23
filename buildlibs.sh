#
# Build all additional files to be installed 
#

# dx.jar is from `SDK/build-tools/23.0.3/lib/dx.jar

BASE=`pwd`
BUILD=$BASE/build/kotlinc
EXT=$BUILD/ext
SRC=$BASE/framework
JSRC=$BASE/js/jslib
UISRC=$BASE/cs109ui

mkdir -p $EXT

echo "Building cs109.jar"
kotlinc -d $EXT/cs109.jar $SRC/canvas.kt $SRC/framework.kt $SRC/utils.kt

echo "Building cs109ui.jar"
kotlinc -d $EXT/cs109ui.jar -cp $EXT/cs109.jar $UISRC/canvas.kt $UISRC/cs109ui.kt

echo "Building cs109-jslib.jar"
kotlinc-js -output jscanvas.js -meta-info $JSRC/jscanvas.kt $SRC/canvas.kt
jar cf $EXT/cs109-jslib.jar jscanvas.js jscanvas.meta.js
rm jscanvas.js jscanvas.meta.js

echo "Building cs109-emulator.jar"
kotlinc -d $EXT/cs109-emulator.jar -cp $EXT/cs109.jar:$EXT/cs109ui.jar $BASE/emulator/emulator.kt

mkdir -p $BUILD/bin
cp $BASE/bin/kt* $BUILD/bin

mkdir -p $BUILD/dx
cp $ANDROID_HOME/build-tools/23.0.3/lib/dx.jar $BUILD/dx
wget -O $EXT/junit-4.12.jar http://search.maven.org/remotecontent?filepath=junit/junit/4.12/junit-4.12.jar
wget -O $EXT/hamcrest-core-1.3.jar http://search.maven.org/remotecontent?filepath=org/hamcrest/hamcrest-core/1.3/hamcrest-core-1.3.jar

echo "Making zipfile"
pushd $BASE/build
zip -r cs109-additions.zip kotlinc
popd

