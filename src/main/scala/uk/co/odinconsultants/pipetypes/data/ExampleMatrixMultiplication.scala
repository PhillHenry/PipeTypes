package uk.co.odinconsultants.pipetypes.data

type Dimension = Int & Singleton

case class Matrix[T <: Dimension, U <: Dimension](data: List[Double])

trait MatrixMultiplication {
  def multiply[T <: Dimension, U <: Dimension, V <: Dimension]
    (x: Matrix[T, U], y: Matrix[U, V]): Matrix[T, V]
}

object ExampleMatrixMultiplication {

  def main(array: Array[String]): Unit = matrixMultiplicationExample(new MatrixMultiplication {
    override def multiply[T <: Dimension, U <: Dimension, V <: Dimension](x: Matrix[T, U],
                                                                          y: Matrix[U, V])
    : Matrix[T, V] = ???
  })

  def matrixMultiplicationExample(ops: MatrixMultiplication) = {
    val _3x7 = Matrix[3, 7](List())
    val _7x4 = Matrix[7, 4](List())
    val _3x5 = Matrix[3, 5](List())

    ops.multiply(_3x7, _7x4)
//    ops.multiply(_3x7, _3x5) // doesn't compile - as expected
  }

}
