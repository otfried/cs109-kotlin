import org.junit.Assert.*
import org.junit.Test

public class AdditionTest {

  @Test
  fun onePlusOne() {
    assertEquals("1 + 1 must be 2", 2, 1 + 1)
    assertNotEquals("1 + 1 must not be 3", 3, 1 + 1)
  }
} 
