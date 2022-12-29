package uk.co.odinconsultants.pipetypes.data.matrix
import uk.co.odinconsultants.pipetypes.data.matrix.Matrix.*

import scala.compiletime.testing.typeCheckErrors

class MatrixSpec extends munit.FunSuite:
  val xs = List(1d, 2d, 3d, 4d)
  test("Those should not compile") {
//    assertEquals(typeCheckErrors("invert(Matrix[2, 2](List(1d, 2d)))").map(_.message), List(InvalidCoefficientsMsg))
    assertEquals(typeCheckErrors("Matrix[1, -2](List(1d, 2d))").map(_.message), List(InvalidDimensionMsg))
    assertEquals(typeCheckErrors("Matrix[-1, 2](List(1d, 2d))").map(_.message), List(InvalidDimensionMsg))
    assertEquals(typeCheckErrors("invert(Matrix[1, 2](List(1d, 2d)))").size, 1)
  }
  test("These should compile") {
    invert(transpose(Matrix[2, 2](xs)))
  }
