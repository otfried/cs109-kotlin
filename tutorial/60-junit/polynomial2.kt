@Suppress("UNUSED_PARAMETER")
class Polynomial(coeffs: Array<Int>) {

  constructor(vararg coeffs: Int) : this(coeffs.toTypedArray()) { }

  private val c = createCoeffs(coeffs)
  
  private fun createCoeffs(a: Array<Int>): List<Int> {
    var s = a.lastIndex
    while (s >= 0 && a[s] == 0)
      s -= 1
    return a.take(s+1)
  }

  fun degree(): Int = c.lastIndex

  fun coeff(i: Int): Int = if (i < c.size) c[i] else 0
  
  override fun toString(): String {
    var s = StringBuilder()
    var plus = ""
    var minus = "-"
    for (i in degree() downTo 0) {
      if (c[i] != 0) {
	var e = c[i]
	s.append(if (e > 0) plus else minus)
	plus = " + "; minus = " - "
	e = Math.abs(e)
	if (i == 0)
	  s.append(e)
	else {
	  if (e != 1) {
	    s.append(e)
	    s.append(" * ")
	  }
	  if (i > 1) {
	    s.append("X^")
	    s.append(i)
	  } else 
	    s.append("X")
	}
      }
    }
    return s.toString()
  }
  
  operator fun plus (rhs: Polynomial): Polynomial = TODO()
  
  operator fun plus(rhs: Int) = this + Polynomial(rhs)

  operator fun minus (rhs: Polynomial): Polynomial = TODO()
  
  operator fun minus(rhs: Int) = this - Polynomial(rhs)

  operator fun times (rhs: Polynomial): Polynomial = TODO()

  infix fun pow (ex: Int): Polynomial = TODO()

  operator fun invoke(x: Double): Double = TODO()
}

operator fun Int.times(rhs: Polynomial) = Polynomial(this) * rhs

val X = Polynomial(0, 1)
