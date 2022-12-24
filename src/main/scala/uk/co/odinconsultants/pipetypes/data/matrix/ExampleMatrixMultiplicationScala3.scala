package uk.co.odinconsultants.pipetypes.data.matrix

import io.github.iltotore.iron.*
import io.github.iltotore.iron.constraint.all.{DescribedAs, *}
import uk.co.odinconsultants.pipetypes.data.Matrix

type DimensionType = Int :| DescribedAs[
  Greater[0],
  "Dimensions of a matrix must be > 0"
]

type Dim = Int & Singleton

class Matrix[X <: Dim, Y <: Dim]

object ExampleMatrixMultiplicationScala3 {

  def main(args: Array[String]): Unit = {
    println("hello, world")
    new Matrix[3, 2]
  }

}
