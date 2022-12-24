package uk.co.odinconsultants.pipetypes.data.matrix

import io.github.iltotore.iron.*
import io.github.iltotore.iron.constraint.all.{DescribedAs, *}
import uk.co.odinconsultants.pipetypes.data.Matrix
import scala.compiletime.{erasedValue, constValue, error}

type DimensionType = Int :| DescribedAs[
  Greater[0],
  "Dimensions of a matrix must be > 0"
]

type Dim = Int & Singleton

class Matrix[X <: Dim, Y <: Dim]

object ExampleMatrixMultiplicationScala3 {

  class ValidShape[X <: Dim, Y <: Dim]()

  transparent implicit inline def validate[X <: Dim, Y <: Dim](m: Matrix[X, Y]): ValidShape[X, Y] = {
    inline erasedValue[X] match
      case x: Dim if x <= 0 => error("boom!")
      case _ => new ValidShape[X, Y]
  }

  def itWorks[X <: Dim, Y <: Dim](x: ValidShape[X, Y]): Unit = println(x)

  def main(args: Array[String]): Unit = {
    println("hello, world")
    itWorks(new Matrix[3, 2])
//    val x = new Matrix[-3, -2]
//    itWorks(x)
  }

}
