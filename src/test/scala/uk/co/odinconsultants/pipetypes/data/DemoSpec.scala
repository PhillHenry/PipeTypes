package uk.co.odinconsultants.pipetypes.data

import scala.compiletime.testing.typeCheckErrors
import uk.co.odinconsultants.pipetypes.safety.Types._

class DemoSpec extends munit.FunSuite:
  test("Those should not compile") {
    assertEquals(typeCheckErrors("val x: Validated[LowerThan[10]] = 16").map(_.message),
      List("Validation failed: 16 < 10"))
    assertEquals(typeCheckErrors("val x: ValidatedLong[LowerThanLong[10L]] = 16L").map(_.message),
      List("Validation failed: 16L < 10L"))
  }
  test("Those should compile") {
    val a: Validated[LowerThan[10]] = 6
  }
  test("Those should compile (Long)") {
    val a: ValidatedLong[LowerThanLong[10L]] = 6L
  }
  test("Those should compile (Double)") {
    val a: ValidatedDouble[LowerThanDouble[10d]] = 6d
  }
  test("'And' clause") {
    type NonZeroDouble = ValidatedDouble[GreaterThanDouble[0d] Or LowerThanDouble[0d]]

    def inverse_refined(x: NonZeroDouble): Double = 1d / x.t

    assert(inverse_refined(2d) == 0.5)
  }

