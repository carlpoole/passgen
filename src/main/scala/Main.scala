import scala.util.Random
import scala.io.Source

/**
 * Created by carl on 3/5/15.
 */
object Main {

  def main(args: Array[String]) {
//    println(printableChar())
//    println(upperChar())
//    println(lowerChar())
//    println(digit())
//    println(symbol("!#$^%+@*&_-"))
//    println(word(5)+word(8)+digit()+digit()+digit())
    print(dec(0.25,0.25,0.25,0.25)._2)

  }

  def dec(f1:Double, f2:Double, f3:Double, f4:Double): ((Double,Double,Double,Double),Int) = {
      val f = Random.nextDouble();

      def bucket(f : Double): Int = f match {
        case f if f <= f1                       => 1
        case f if f > f1 && f <= f1+f2          => 2
        case f if f > f1+f2 && f <= f1+f2+f3    => 3
        case _                                  => 4
    }

    ((f1,f2,f3,f4),bucket(f))
  }


  def printableChar():        Char = Random.nextPrintableChar()
  def upperChar():            Char = (Random.nextInt(26) + 65).asInstanceOf[Char]
  def lowerChar():            Char = upperChar().toLower
  def digit():                Int  = Random.nextInt(10)
  def symbol(chars: String):  Char = chars.charAt(Random.nextInt(chars.length))

  def word(length: Int = 4):  String = {
    val words = Source.fromFile("/usr/share/dict/words")
      .mkString
      .split("\n")
      .filter(x => x.length() == length)

    words(Random.nextInt(words.length))
  }

}
