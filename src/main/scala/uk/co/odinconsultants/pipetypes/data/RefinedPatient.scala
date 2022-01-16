package uk.co.odinconsultants.pipetypes.data

import scala.math.Ordered.orderingToOrdered
import scala.math.Ordering.Implicits.infixOrderingOps
import io.github.iltotore.iron.constraint.refineValue
import io.github.iltotore.iron.*, constraint.{given, *}
import numeric.constraint.{given, *}


object ExamplePipeline {

  type Age = Int  / ((0 <= ?? <= 120) DescribedAs "Reasonable ages should be between 0 and 120")
  type BinaryInteger = Int / ((0 <= ?? <= 1) DescribedAs "Binary integer values are either 1 or 0")

  def ageToBinary(age: Age): Refined[Int] = age.flatMap { age =>
      val binary: BinaryInteger = if (age < 40) {
        1: BinaryInteger
      } else {
        0: BinaryInteger
      }
      binary
    }

  def accept(x: BinaryInteger): BinaryInteger = {
    println(s"accept $x")
    x
  }

  def main(args: Array[String]): Unit = {
    val oldFart = ageToBinary(42)
    val asBinary = oldFart.flatMap(x => accept(refineValue(x)))
    println(s"asBinary = $asBinary")
//    oldFart.map(accept)
//    val doesNotCompile = accepted(121)
//    val doesNotCompile = accepted(0: Binary)
  }

}
