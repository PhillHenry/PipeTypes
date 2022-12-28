package uk.co.odinconsultants.pipetypes.data.matrix
import uk.co.odinconsultants.pipetypes.data.matrix.Matrix.*

import scala.compiletime.testing.typeCheckErrors

class MatrixSpec extends munit.FunSuite:
  test("Those should not compile") {
    assertEquals(typeCheckErrors("Matrix[1, -2]").map(_.message), List(ValidDimensionMsg))
    assertEquals(typeCheckErrors("Matrix[-1, 2]").map(_.message), List(ValidDimensionMsg))
    assertEquals(typeCheckErrors("invert(Matrix[1, 2])").size, 1)
  }
  test("These shoule compile") {
    invert(transpose(Matrix[2, 2]))
  }
