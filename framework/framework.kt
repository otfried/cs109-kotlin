package org.otfried.cs109

// --------------------------------------------------------------------

interface Context {
  fun update()
  fun toast(s: String)
  fun setTitle(s: String)
  fun showMessage(s: String)
  fun askYesNo(s: String, handle: (Boolean) -> Unit)
  fun inputString(prompt: String, handle: (String) -> Unit)
  fun onTap(f: (x: Double, y: Double) -> Unit)
  fun onDoubleTap(f: (x: Double, y: Double) -> Unit)
  fun onPress(f: (x: Double, y: Double) -> Unit)
  fun onFling(f: (x: Double, y: Double, dir: Char, dist: Double) -> Unit)
}

interface MiniApp {
  val name: String
  fun onDraw(canvas: Canvas)
}

// --------------------------------------------------------------------
