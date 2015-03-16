import scala.annotation.tailrec
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
    //println(fourPassParamTest(0.25,0.25,0.25,0.25)._2)

    val probability = List(0.5, 0.3, 0.1, 0.1)

    for(x <- 1 to 100){
      for(y <- 1 to 8)
        print(resolveType(choosePassChar(probability)._2))
      println()
    }

  }

  def resolveType(t:Int): Char = t match {
    case 1 => lowerChar()
    case 2 => (digit()+48).toChar
    case 3 => upperChar()
    case 4 => symbol("!#$^%+@*&_-")
  }

  def choosePassChar(buckets: List[Double]): (List[Double], Int) = {

    val rand = Random.nextDouble();
    var lt: List[Double] = Nil
    lt = buckets.zipWithIndex.flatMap(x => lt :+ buckets.take(x._2).sum)

    def place(f : Double, c : Int): Int = f match {
    case f if f <= lt(c)    => c
    case f if c+1 < lt.size => place(f,c+1)
    case _                  => lt.size
  }

    if (buckets.size > 1)
      (buckets,place(rand,1))
    else
      (buckets,1)
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
