package uk.co.odinconsultants.pipetypes.data

import scala.math.Ordered.orderingToOrdered
import scala.math.Ordering.Implicits.infixOrderingOps

import io.github.iltotore.iron.*, constraint.{given, *}
import numeric.constraint.{given, *}


object TestTypes {

  type Aged = Int  / (Greater[-1] && Less[120])
  type Binaryd = Int / (-1 < ?? < 2)

  def accepted(age: Aged) = {
    val binary = age.flatMap { age =>
      val binary: Binaryd = if (age < 40) {
        1: Binaryd
      } else {
        0: Binaryd
      }
      binary
    }
    println(s"age = $age, binary = $binary")
    binary
  }

  def main(args: Array[String]): Unit = {
    val oldFart = accepted(42)
//    val doesNotCompile = accepted(121)
  }

}
