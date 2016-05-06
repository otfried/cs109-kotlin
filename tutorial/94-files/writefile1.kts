
val out = java.io.File("test.txt").printWriter()

for (i in 1 .. 10) {
  out.println("$i: ${i * i}")
}

out.close()


  
