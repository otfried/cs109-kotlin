class Board {
  private val a = Array<Array<Int>>(4) { arrayOf(0, 0, 0, 0) } 

  private fun displayRow(s: StringBuilder, row: Int, 
			 form: String?, term: String) {
    for (col in 0 until 4) {
      if (form == null) {
	val m = a[row][col]
	if (m != 0) {
	  var ms = "   " + m.toString()
	  ms = ms.substring(ms.length - 3)
	  s.append(if (m < 1000) "|$ms " else "|$m")
	} else
	  s.append("|    ")
      } else
	s.append(form)
    }
    s.append(term)
    s.append('\n')
  }

  override fun toString(): String {
    val s = StringBuilder()
    for (row in 0 .. 3) {
      displayRow(s, row, "o----", "o")
      displayRow(s, row, "|    ", "|")
      displayRow(s, row, null, "|")
      displayRow(s, row, "|    ", "|")
    }
    displayRow(s, 3, "o----", "o")
    return s.toString()
  }

  // for debugging and testing
  constructor(vararg contents: Int) {
    if (contents.size != 0) {
      assert(contents.size == 15)
      for (row in 0 until 4)
        for (col in 0 until 4)
          a[row][col] = contents[4 * row + col]
    }
  }  

  // for debugging and testing
  fun toList(): List<Int> = a.flatMap { it.toList() }

  fun cell(row: Int, col: Int): Int = a[row][col]

  // is the board completely filled?
  fun isFull(): Boolean = TODO()
  
  fun insert() {
    TODO()
  }
	
  fun pushLeft(): Int {
    TODO()
  }

  fun pushRight(): Int {
    TODO()
  }

  fun pushUp(): Int {
    TODO()
  }

  fun pushDown(): Int {
    TODO()
  }

  // pushes in direction ch (in 'lrud')
  // returns number of points
  fun push(ch: Char): Int = when(ch) {
    'l' -> pushLeft()
    'r' -> pushRight()
    'u' -> pushUp()
    'd' -> pushDown()
    else -> 0
  }
}

