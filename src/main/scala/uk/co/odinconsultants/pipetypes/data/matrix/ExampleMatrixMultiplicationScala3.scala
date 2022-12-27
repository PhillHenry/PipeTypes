package uk.co.odinconsultants.pipetypes.data.matrix

import io.github.iltotore.iron.*
import io.github.iltotore.iron.constraint.all.{DescribedAs, *}
import scala.compiletime.{erasedValue, constValue, error}

type DimensionType = Int :| DescribedAs[
  Greater[0],
  "Dimensions of a matrix must be > 0"
]

type Dim = Int & Singleton



object MatrixOps {
  class ValidMatrix[X <: Dim, Y <: Dim]()
  opaque type Validated[X <: Dim, Y <: Dim] = ValidMatrix[X, Y]


  transparent implicit inline def validate[X <: Dim, Y <: Dim](m: Matrix[X, Y]): Validated[X, Y]
  = {
    inline erasedValue[X] match
      case x: Dim if x <= 0 => error("boom!")
      case _ => new ValidMatrix[X, Y]
  }
  class ValidD[Dim]
  class Matrix[X <: Dim, Y <: Dim]
  transparent implicit inline def toValid[X <: Dim](m: X): ValidD[X] = {
    inline if (m <= 0) error("boom!") else new ValidD[X]
  }
  def mustBeValid(x: ValidD[Dim]): Unit = println(x)
}

object ExampleMatrixMultiplicationScala3 {


  def main(args: Array[String]): Unit = {
    println("hello, world")
    import MatrixOps.*
    import MatrixOps.validate
    import MatrixOps.toValid
    //    val invalid = new Matrix[-3, -2]
    val valid = new Matrix[3, 2]
    mustBeValid(1)
//    mustBeValid(-1) // boom!
//    mustBeValid(valid)
//    mustBeValid(invalid) // boom!
  }

}
