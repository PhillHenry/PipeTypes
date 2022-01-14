package uk.co.odinconsultants.pipetypes.data

import scala.math.Ordered.orderingToOrdered
import scala.math.Ordering.Implicits.infixOrderingOps

import io.github.iltotore.iron.*, constraint.{given, *}
import numeric.constraint.{given, *}


object TestTypes {

  type Age = Int  / (Greater[-1] && Less[120])
  type Binary = Int / (-1 < ?? < 2)

  def accepted(age: Age) = {
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

  def main(args: Array[String]): Unit = {
    val oldFart = accepted(42)
//    val doesNotCompile = accepted(121)
//    val doesNotCompile = accepted(0: Binaryd)
  }

}
