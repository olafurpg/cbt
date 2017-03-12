package loop
import org.scalafmt._
object Main{
  def main( args: Array[String] ): Unit = {
    println(Scalafmt.format("object a    {}").get)
  }
}
