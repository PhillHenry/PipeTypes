package uk.co.odinconsultants.pipetypes.data

import scala.compiletime.testing.typeCheckErrors
import uk.co.odinconsultants.pipetypes.safety.Types._

class DemoSpec extends munit.FunSuite:
  test("Those should not compile") {
    val errs = typeCheckErrors("val x: Validated[LowerThan[10]] = 16")
    assertEquals(errs.map(_.message), List("Validation failed: 16 < 10"))
  }
  test("Those should compile") {
    val a: Validated[LowerThan[10]] = 6
  }
  test("Those should compile (Long)") {
    val a: ValidatedLong[LowerThanLong[10L]] = 6L
  }

