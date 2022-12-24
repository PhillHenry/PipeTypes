package uk.co.odinconsultants.pipetypes.data.matrix

import io.github.iltotore.iron.*
import io.github.iltotore.iron.constraint.all.{DescribedAs, *}
import uk.co.odinconsultants.pipetypes.data.Matrix

type Dimension = Int :| DescribedAs[
  Greater[0],
  "Dimensions of a matrix must be > 0"
]

class Matrix[X <: Dimension, Y <: Dimension](x: X, y: Y)

object ExampleMatrixMultiplicationScala3 {

  def main(args: Array[String]): Unit = {
    println("hello, world")
    val _3x2 = new Matrix(3, 2)
  }

}
