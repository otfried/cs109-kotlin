package org.otfried.cs109

// --------------------------------------------------------------------

interface Context {
  fun update()

  fun setTitle(s: String)

  fun toast(s: String)
  fun showMessage(s: String)
  fun askYesNo(s: String, handle: (Boolean) -> Unit)
  fun inputString(prompt: String, handle: (String) -> Unit)

  fun onTap(f: (x: Double, y: Double) -> Unit)
  fun onDoubleTap(f: (x: Double, y: Double) -> Unit)
  fun onPress(f: (x: Double, y: Double) -> Unit)
  fun onFling(f: (x: Double, y: Double, dir: Char, dist: Double) -> Unit)

  fun after(ms: Long, f: () -> Unit)
}

interface MiniApp {
  fun onDraw(canvas: Canvas)
}

// --------------------------------------------------------------------
