import org.junit.Assert.*
import org.junit.Test

class ArithmeticTest {

  @Test
  fun onePlusOne() {
    assertEquals("1 + 1 must be 2", 2, 1 + 1)
    assertNotEquals("1 + 1 must not be 3", 3, 1 + 1)
  }

  @Test(expected = ArithmeticException::class)
  fun divisionByZero() {
    @Suppress("UNUSED_VARIABLE")
    val result = 1 / 0
  }
}

