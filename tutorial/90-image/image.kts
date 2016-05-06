import java.io.File
import javax.imageio.ImageIO
import java.awt.image.BufferedImage

fun phototest(img: BufferedImage): BufferedImage {
  // obtain width and height of image
  val w = img.width
  val h = img.height

  // create new image of the same size
  val out = BufferedImage(w, h, BufferedImage.TYPE_INT_RGB)

  // copy pixels (mirror horizontally)
  for (x in 0 until w)
    for (y in 0 until h)
      out.setRGB(x, y, img.getRGB(w - x - 1, y) and 0xffffff)
  
  // draw red diagonal line
  for (x in 0 until Math.min(h, w))
    out.setRGB(x, x, 0xff0000)

  return out
}
  
fun test() {
  // read original image, and obtain width and height
  val photo1 = ImageIO.read(File("photo.jpg"))
  
  val photo2 = phototest(photo1) 

  // save image to file "test.jpg"
  ImageIO.write(photo2, "jpg", File("test.jpg"))
}

test()
