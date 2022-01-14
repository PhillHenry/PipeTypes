package uk.co.odinconsultants.pipetypes.data


//import io.github.iltotore.iron.constraint.*
//import io.github.iltotore.iron.numeric.constraint.{Greater, Less}
//import io.github.iltotore.iron.{constrainedToValue, refineConstrained, *}

import scala.math.Ordered.orderingToOrdered
import scala.math.Ordering.Implicits.infixOrderingOps

//You can specify constraints you want if needed but be sure to import `given` or the given instance of your constraint
import io.github.iltotore.iron.*, constraint.{given, *}

//If you have other Iron modules like numeric:
import numeric.constraint.{given, *}

//type >[A, B] = A / Greater[B]
//type <[A, B] = A / Less[B]

object TestTypes {

//  case class RefinedPatient(age: Age)
//  type Age = Closed[0, 120]
//  type Binary = Int Refined Closed[0, 1]

//  type Aged = Int / (-1 < ?? < 120)
  type Aged = Int  / (Greater[-1] && Less[120])
  type Binaryd = Int / (-1 < ?? < 2)

//  def accept(age: Age): Unit = println(s"age = $age")
  def accepted(age: Aged): Unit = {
    val oldFartThreshold: Aged = 40
    type <[A, B] = A / Less[B]
    val binary = age.map { age =>
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
//    val a: Int Refined Greater[5] = 10
//    val binary: Binary = 1
//    val age: Age = 49
    //    accept(binary)
    val age: Aged = 42
    val oldFart = accepted(age)
  }

}
