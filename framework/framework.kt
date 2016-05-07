package org.otfried.cs109

// --------------------------------------------------------------------

interface Context {
  val width: Int
  val height: Int

  fun update()

  fun setTitle(s: String)

  fun toast(s: String)
  fun showMessage(s: String)
  fun askYesNo(s: String, handle: (Boolean) -> Unit)
  fun inputString(prompt: String, handle: (String) -> Unit)

  fun onTap(f: (x: Double, y: Double) -> Unit)
  fun onDoubleTap(f: (x: Double, y: Double) -> Unit)
  fun onFling(f: (x: Double, y: Double, dir: Char, dist: Double) -> Unit)

  fun onGravity(f: (x: Double, y: Double, z: Double) -> Unit)
  fun onLight(f: (lux: Double) -> Unit)

  fun after(ms: Long, f: () -> Unit)

  fun createMenu(items: List<Pair<String, () -> Unit>>)
}

interface MiniApp {
  fun onDraw(canvas: Canvas)
}

// --------------------------------------------------------------------
