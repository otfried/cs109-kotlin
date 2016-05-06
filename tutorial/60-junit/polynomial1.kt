@Suppress("UNUSED_PARAMETER")
class Polynomial(coeffs: Array<Int>) {

  constructor(vararg coeffs: Int) : this(coeffs.toTypedArray()) { }

  fun degree(): Int = TODO()

  fun coeff(i: Int): Int = TODO()
  
  override fun toString(): String = TODO()
  
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
