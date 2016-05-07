package org.otfried.cs109emulator

import org.otfried.cs109.MiniApp
import org.otfried.cs109.Context
import org.otfried.cs109.TextAlign
import org.otfried.cs109.Color

import org.otfried.cs109ui.ImageCanvas

import java.awt.Dimension
import java.awt.Color as JColor
import java.awt.image.BufferedImage
import java.awt.event.WindowEvent
import java.awt.event.KeyEvent
import java.awt.event.ComponentEvent
import java.awt.event.MouseEvent
import java.awt.event.ActionListener
import javax.swing.JOptionPane as JOP

import javax.swing.JMenuItem

import java.net.URLClassLoader
import java.net.URL

// --------------------------------------------------------------------

private class JCanvas(w: Int, h: Int, val handler: UI): javax.swing.JComponent() {
  private var image = BufferedImage(w, h, BufferedImage.TYPE_INT_RGB)
  var miniApp: MiniApp? = null
  var toast: String? = null
  var redrawApp = true
  val toastFont = java.awt.Font("sans-serif", java.awt.Font.PLAIN, 32)

  init {
    setPreferredSize(Dimension(w, h))
    setFocusable(true)

    addKeyListener(object: java.awt.event.KeyAdapter() {
      override fun keyPressed(e: KeyEvent) {
        handler.handleKey(e.keyChar)
      }
    })
    addMouseListener(object: java.awt.event.MouseAdapter() {
      override fun mouseClicked(e: MouseEvent) {
        handler.handleMouse(e.button, e.x.toDouble(), e.y.toDouble())
      }
    })
  }
  
  override fun paintComponent(gr: java.awt.Graphics) {
    val gc = ImageCanvas(image)
    if (redrawApp)
      miniApp?.onDraw(gc)
    redrawApp = false
    gc.done()
    val g = gr as java.awt.Graphics2D
    g.drawImage(image, null, 0, 0)
    val t = toast
    t?.let {
      g.setFont(toastFont)
      val x = width / 2
      val y = height - 60
      val w = g.getFontMetrics().stringWidth(t).toInt() / 2
      g.setColor(JColor.BLUE)
      g.drawString(t, x - w, y)
    }
  }
}

// --------------------------------------------------------------------

private class UI(override val width: Int, override val height: Int) : Context {
  val canvas = JCanvas(width, height, this)
  val ui = javax.swing.JFrame()
  var miniAppMenu: List<Pair<String, () -> Unit>>? = null

  init {
   ui.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE)
   ui.setTitle("CS109 emulator")
   ui.setResizable(false)
   ui.add(canvas)
   ui.pack()
  }

  val toastTimer = javax.swing.Timer(2000, { clearToast() } )
  val afterTimer = javax.swing.Timer(2000, { handleTimeout() } )
  
  var tapHandler: ((x: Double, y: Double) -> Unit)? = null
  var doubleTapHandler: ((x: Double, y: Double) -> Unit)? = null
  var flingHandler: ((x: Double, y: Double, 
                      dir: Char, dist: Double) -> Unit)? = null

  var afterHandler: (() -> Unit)? = null
  var gravityHandler: ((x: Double, y: Double, z: Double) -> Unit)? = null
  var lightHandler: ((lux: Double) -> Unit)? = null

  // ------------------------------------------------------------------

  override fun update() {
    canvas.redrawApp = true
    canvas.repaint()
  }

  override fun setTitle(s: String) {
   ui.setTitle(s)
  }

  override fun toast(s: String) {
    canvas.toast = s
    toastTimer.start()
    canvas.repaint()
  }

  fun clearToast() { 
    canvas.toast = null
    canvas.repaint()
  }

  override fun onTap(f: (x: Double, y: Double) -> Unit) { 
    tapHandler = f
  }
  override fun onDoubleTap(f: (x: Double, y: Double) -> Unit) {
    doubleTapHandler = f
  }
  override fun onFling(f: (x: Double, y: Double, dir: Char, 
                           dist: Double) -> Unit) {
    flingHandler = f
  }

  override fun onGravity(f: (x: Double, y: Double, z: Double) -> Unit) {
    gravityHandler = f
    // resume()
  }

  override fun onLight(f: (lux: Double) -> Unit) {
    lightHandler = f
    // resume()
  }

  override fun after(ms: Long, f: () -> Unit) {
    afterTimer.stop()
    afterTimer.setInitialDelay(ms.toInt())
    afterHandler = f
    afterTimer.start()
  }

  fun handleTimeout() {
    afterHandler?.invoke()
  }

  override fun createMenu(items: List<Pair<String, () -> Unit>>) {
    miniAppMenu = items
    val menu = javax.swing.JMenu("Menu")
    for (i in items.indices) {
      val item = javax.swing.JMenuItem(items[i].first)
      item.addActionListener(ActionListener { handleMenuItem(i) })
      menu.add(item)
    }
    val menubar = javax.swing.JMenuBar()
    menubar.add(menu)
    ui.setJMenuBar(menubar)
  }

  fun handleMenuItem(item: Int) {
    val m = miniAppMenu
    m?.let { m[item].second() }
  }
  
  // ------------------------------------------------------------------

  fun handleKey(ch: Char) {
    val x = width / 2.0
    val y = height / 2.0
    val dist = 200.0
    if (ch in "udlr")
      flingHandler?.invoke(x, y, ch, dist)
  }

  fun handleMouse(button: Int, x: Double, y: Double) {
    when(button) {
    MouseEvent.BUTTON1 -> tapHandler?.invoke(x, y)
    MouseEvent.BUTTON3 -> doubleTapHandler?.invoke(x, y)
    else -> { /* ignore it */ } 
    }
  }

  // ------------------------------------------------------------------

  override fun showMessage(s: String) {
    JOP.showMessageDialog(canvas, s, ui.title, JOP.PLAIN_MESSAGE)
  }
  
  override fun askYesNo(s: String, handle: (Boolean) -> Unit) {
    val r = JOP.showConfirmDialog(canvas, s, ui.title, 
    	    		          JOP.YES_NO_OPTION, JOP.PLAIN_MESSAGE)
    handle(r == JOP.YES_OPTION)
  }
  
  override fun inputString(prompt: String, handle: (String) -> Unit) {
    val r = JOP.showInputDialog(canvas, prompt, ui.title,
  			        JOP.PLAIN_MESSAGE, null, null, "")
    if (r is String)
      handle(r)
  }
  
  // up to three buttons are okay
  fun askButtons(question: String, buttons: Array<String>): Int {
    var result: Int = 0
    val r = JOP.showOptionDialog(canvas, question, ui.title,
  				 JOP.YES_NO_CANCEL_OPTION, JOP.PLAIN_MESSAGE,
  				 null, buttons, null)
    if (r != JOP.CLOSED_OPTION)
      result = r + 1
    return result
  }
  
  fun askChoice(msg: String, choices: Array<String>): String {
    var result: String = ""
    val r = JOP.showInputDialog(canvas, msg, ui.title,
  			        JOP.PLAIN_MESSAGE, null,
  			        choices, null)
    if (r is String)
      result = r
    return result
  }
}
  
// --------------------------------------------------------------------
  
fun loadJar(jar: String, ctx: Context): MiniApp {
  System.err.print("Loading '$jar' ...")
  val loader = URLClassLoader(arrayOf(URL("file:$jar")))
  val klass = loader.loadClass("Main")
  @Suppress("UNCHECKED_CAST")
  val ctor = klass.getConstructor(Context::class.java)
               as java.lang.reflect.Constructor<MiniApp>
  val miniApp = ctor.newInstance(ctx)
  System.err.println("succeeded")
  return miniApp
}

fun usage() {
  System.err.println("Usage: kt-emulator <jar file> [ <width> <height> ]")
  kotlin.system.exitProcess(1)
}

fun main(args: Array<String>) {
  var width = 480
  var height = 720
  if (args.size == 3) {
    try {
      width = args[1].toInt()
      height = args[2].toInt()
    }
    catch (e: NumberFormatException) {
      usage()
    }
  }
  if (args.size != 1 && args.size != 3)
    usage()
  val jar = args[0]
  if (!jar.endsWith(".jar"))
    usage()
  val ui = UI(width, height)
  val miniApp = loadJar(jar, ui)
  ui.canvas.miniApp = miniApp
  ui.ui.setVisible(true)
}

// --------------------------------------------------------------------
