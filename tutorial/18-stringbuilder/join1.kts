fun join(l: List<Int>): String {
  var s = ""
  for (e in l) {
    if (s.isEmpty())
      s = e.toString()
    else
      s = s + ", " + e.toString()
  }
  return s
}
