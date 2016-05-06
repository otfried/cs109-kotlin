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
kotlinc -d $EXT/cs109.jar $SRC/canvas.kt $SRC/framework.kt

echo "Building cs109ui.jar"
kotlinc -d $EXT/cs109ui.jar -cp $EXT/cs109.jar $UISRC/canvas.kt $UISRC/cs109ui.kt

echo "Building cs109-jslib.jar"
kotlinc-js -output jscanvas.js -meta-info $JSRC/jscanvas.kt $SRC/canvas.kt
jar cf $EXT/cs109-jslib.jar jscanvas.js jscanvas.meta.js
rm jscanvas.js jscanvas.meta.js

mkdir -p $BUILD/bin
cp $BASE/bin/kt* $BUILD/bin

echo "Making zipfile"
pushd $BASE/build
zip -r cs109-additions.zip kotlinc
popd

