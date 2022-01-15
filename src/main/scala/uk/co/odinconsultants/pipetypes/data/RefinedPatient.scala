package uk.co.odinconsultants.pipetypes.data

import scala.math.Ordered.orderingToOrdered
import scala.math.Ordering.Implicits.infixOrderingOps
import io.github.iltotore.iron.constraint.refineValue
import io.github.iltotore.iron.*, constraint.{given, *}
import numeric.constraint.{given, *}


object TestTypes {

  type Age = Int  / ((0 <= ?? <= 120) DescribedAs "Reasonable ages should be between 0 and 120")
  type Binary = Int / (-1 < ?? < 2)

  def accepted(age: Age): Refined[Int] = {
    println(s"age type = ${age.getClass.getName}")
    println(age.getClass.getTypeParameters.map(_.getBounds.map(_.getTypeName).mkString(", ")).mkString(", "))
    val binary = age.flatMap { age =>
      val binary: Binary = if (age < 40) {
        1: Binary
      } else {
        0: Binary
      }
      binary
    }
    println(s"age = $age, binary = $binary")
    binary
  }

  def accept(x: Binary): Binary = {
    print(s"accept $x")
    x
  }

  def main(args: Array[String]): Unit = {
    val oldFart = accepted(42)
    oldFart.flatMap(x => accept(refineValue(x)))
//    oldFart.map(accept)
//    val doesNotCompile = accepted(121)
//    val doesNotCompile = accepted(0: Binary)
  }

}
