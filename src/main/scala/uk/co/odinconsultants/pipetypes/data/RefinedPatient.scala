package uk.co.odinconsultants.pipetypes.data

import scala.math.Ordered.orderingToOrdered
import scala.math.Ordering.Implicits.infixOrderingOps
import io.github.iltotore.iron.constraint.refineValue
import io.github.iltotore.iron.*, constraint.{given, *}
import numeric.constraint.{given, *}


object TestTypes {

  type Age = Int  / ((0 <= ?? <= 120) DescribedAs "Reasonable ages should be between 0 and 120")
  type Binary = Int / ((0 <= ?? <= 1) DescribedAs "Binary values are either 1 or 0")

  def ageToBinary(age: Age): Refined[Int] = age.flatMap { age =>
      val binary: Binary = if (age < 40) {
        1: Binary
      } else {
        0: Binary
      }
      binary
    }

  def accept(x: Binary): Binary = {
    print(s"accept $x")
    x
  }

  def main(args: Array[String]): Unit = {
    val oldFart = ageToBinary(42)
    val asBinary = oldFart.flatMap(x => accept(refineValue(x)))
    print(s"asBinary = $asBinary")
//    oldFart.map(accept)
//    val doesNotCompile = accepted(121)
//    val doesNotCompile = accepted(0: Binary)
  }

}
