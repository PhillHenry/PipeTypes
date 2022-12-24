package uk.co.odinconsultants.pipetypes.data

//import scala.math.Ordered.orderingToOrdered
//import scala.math.Ordering.Implicits.infixOrderingOps
import io.github.iltotore.iron.*
import io.github.iltotore.iron.constraint.all.*


object ExamplePipeline {

  type Age = DescribedAs[
    Greater[0] & Less[121],
    "Reasonable ages should be between 0 and 120"
  ]
  type BinaryInteger = DescribedAs[
    StrictEqual[0] | StrictEqual[1],
    "Binary integer values are either 1 or 0"
  ]

  def ageToBinary(age: Int :| Age): Int :| BinaryInteger =
      val binary: Int :| BinaryInteger = if (age < 40) {
        1
      } else {
        0
      }
      binary

  def accept(x: Int :| BinaryInteger): Int :| BinaryInteger = {
    println(s"accept $x")
    x
  }

  def main(args: Array[String]): Unit = {
    val oldFart = ageToBinary(42)
    val asBinary = accept(oldFart)
    println(s"asBinary = $asBinary")
//    oldFart.map(accept)
//    val doesNotCompile = accepted(121)
//    val doesNotCompile = accepted(0: Binary)
  }

}
