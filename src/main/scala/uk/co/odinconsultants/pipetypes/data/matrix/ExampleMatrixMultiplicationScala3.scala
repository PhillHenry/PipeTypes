package uk.co.odinconsultants.pipetypes.data.matrix

import io.github.iltotore.iron.*
import io.github.iltotore.iron.constraint.all.{DescribedAs, *}
import scala.compiletime.{erasedValue, constValue, error}

type DimensionType = Int :| DescribedAs[
  Greater[0],
  "Dimensions of a matrix must be > 0"
]

type Dim = Int & Singleton

class Matrix[X <: Dim, Y <: Dim]

object MatrixOps {
  class ValidMatrix[X <: Dim, Y <: Dim]()
  opaque type Validated[X <: Dim, Y <: Dim] = ValidMatrix[X, Y]

  transparent implicit inline def validate[X <: Dim, Y <: Dim](m: Matrix[X, Y]): Validated[X, Y]
  = {
    inline erasedValue[X] match
      case x: Dim if x <= 0 => error("boom!")
      case _ => new ValidMatrix[X, Y]
  }

  def mustBeValid[X <: Dim, Y <: Dim](x: Validated[X, Y]): Unit = println(x)
}

object ExampleMatrixMultiplicationScala3 {


  def main(args: Array[String]): Unit = {
    println("hello, world")
    val invalid = new Matrix[-3, -2]
    val valid = new Matrix[3, 2]
    import MatrixOps.*
    import MatrixOps.validate
    mustBeValid(valid)
//    mustBeValid(invalid) // boom!
  }

}
