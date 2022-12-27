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


  class ValidD[Dim]
  type D = ValidD[Dim]
  class Matrix[X <: D, Y <: D]
  transparent implicit inline def toValid[X <: Dim](m: X): ValidD[X] = {
    inline if (m <= 0) error("boom!") else new ValidD[X]
  }
  def mustBeValid(x: D): Unit = println(x)

  def squareMatrix[X <: D](x: X): Matrix[X, X] = new Matrix[X, X]
}

object ExampleMatrixMultiplicationScala3 {


  def main(args: Array[String]): Unit = {
    println("hello, world")
    import MatrixOps.*
    import MatrixOps.toValid
    //    val invalid = new Matrix[-3, -2]
//    val valid = new Matrix[3, 2]
    squareMatrix(2)
//    squareMatrix(-2) // boom!
    mustBeValid(1)
//    mustBeValid(-1) // boom!
//    mustBeValid(valid)
//    mustBeValid(invalid) // boom!
  }

}
