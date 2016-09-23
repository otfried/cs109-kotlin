// Utility functions for the beginning

package org.otfried.cs109

fun readString(prompt: String): String {
  print(prompt)
  System.out.flush()
  return readLine() ?: ""
}
