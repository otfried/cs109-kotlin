import org.junit.Assert.*
import org.junit.Test

class PolynomialTest {

  @Test
  fun creatingPolynomials() {
    val p1 = Polynomial(3)
    assertEquals(p1.degree(), 0)
    assertEquals(p1.toString(), "3")
    val p2 = Polynomial(-1, 3, -4, 0, -6)
    assertEquals(p2.degree(), 4)
    assertEquals(p2.toString(), "-6 * X^4 - 4 * X^2 + 3 * X - 1")
    val p3 = Polynomial(0, 0, 1)
    assertEquals(p3.degree(), 2)
    assertEquals(p3.toString(), "X^2")
    val p0 = Polynomial(0)
    assertEquals(p0.degree(), -1)
  }

  @Test
  fun additionAndSubtraction() {
    val p1 = Polynomial(3)
    val p2 = Polynomial(-1, 3, -4, 0, -6)
    val p3 = Polynomial(5, 0, 4, 0, -6)

    val q1 = p1 + p2
    val q2 = p2 - p3
    assertEquals(q1.toString(), "-6 * X^4 - 4 * X^2 + 3 * X + 2")
    assertEquals(q2.degree(), 2)
    assertEquals(q2.toString(), "-8 * X^2 + 3 * X - 6")
  }

  @Test
  fun multiplication() {
    val p1 = Polynomial(3)
    val p2 = Polynomial(-1, 3, -4, 0, -6)
    val p3 = Polynomial(0, 0, 5)
    val p4 = Polynomial(2, -4, 6, 8)

    val q1 = p1 * p2
    val q4 = p2 * p3
    val q5 = p2 * p4
    assertEquals(q1.toString(), "-18 * X^4 - 12 * X^2 + 9 * X - 3")
    assertEquals(q4.degree(), 6)
    assertEquals(q4.toString(), "-30 * X^6 - 20 * X^4 + 15 * X^3 - 5 * X^2")
    assertEquals(q5.toString(), "-48 * X^7 - 36 * X^6 - 8 * X^5 - 12 * X^4 + 26 * X^3 - 26 * X^2 + 10 * X - 2")
  }

  @Test
  fun power() {
    val p1 = Polynomial(3)
    val p3 = Polynomial(0, 0, 5)

    val q2 = p1 pow 5
    val q3 = p3 pow 5
    assertEquals(q2.degree(), 0)
    assertEquals(q2.coeff(0), 3*3*3*3*3)
    assertEquals(q3.degree(), 10)
    assertEquals(q3.toString(), "3125 * X^10")
  }

@Test
  fun creatingPolynomialsUsingX() {
    assertEquals(X.toString(), "X")
    val p4 = -1 * (X pow 5) + 3 * (X pow 3) - (X pow 2) + 5
    assertEquals(p4.toString(), "-X^5 + 3 * X^3 - X^2 + 5")
    val p5 = (X - 1) * (X - 3) * (X + 5) pow 2
    assertEquals(p5.toString(), "X^6 + 2 * X^5 - 33 * X^4 - 4 * X^3 + 319 * X^2 - 510 * X + 225")
  }

  @Test
  fun evaluation() {
    val p1 = Polynomial(3)
    val p2 = Polynomial(-1, 3, -4, 0, -6)
    val p3 = Polynomial(0, 0, 1)
    val p4 = -1 * (X pow 5) + 3 * (X pow 3) - (X pow 2) + 5
    val p5 = (X - 1) * (X - 3) * (X + 5) pow 2

    val eps = 1.0e-9 // floating point precision

    assertEquals(3.0, p1(5.0), eps)
    assertEquals(-1.0, p2(0.0), eps)
    assertEquals(4.0, p3(2.0), eps)
    assertEquals(2.0, p4(-1.0), eps)    
    assertEquals(0.0, p5(-5.0), eps)
  }

  @Test
  fun derivatives() {
    @Suppress("UNUSED_VARIABLE")
    val p1 = (X - 1) * (X - 3) * ((X + 5) pow 2)
    /*
    val q1 = p1.derivative()
    assertEquals(q1.degree(), 3)
    assertEquals(q1.toString(), "4 * X^3 + 18 * X^2 - 24 * X - 70")
    val q2 = q1.derivative()
    assertEquals(q2.degree(), 2)
    assertEquals(q2.toString(), "12 * X^2 + 36 * X - 24")
    */
  }
}
