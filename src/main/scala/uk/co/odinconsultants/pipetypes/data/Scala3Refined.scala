package uk.co.odinconsultants.pipetypes.data

import scala.compiletime.{erasedValue, constValue, error}
import scala.compiletime.ops.int.ToString
//import uk.co.odinconsultants.pipetypes.safety.Types.{And, GreaterThan, LowerThan, Validated}
import uk.co.odinconsultants.pipetypes.safety.Types._

object Demo:

  type Age    = Validated[GreaterThan[-1] And LowerThan[120]]
  type Binary = Validated[GreaterThan[-1] And LowerThan[2]]

  def accepts(x: Age) = println(s"accepted age = $x")

  type Number = Int & Singleton
  val a: Validated[LowerThan[10]] = 6
  
  // val x: Validated[LowerThan[10]] = 16 // Validation fails with:
  // Validation failed: 16 < 10

  val b: Validated[GreaterThan[5] And LowerThan[10]] = 6
  // val y: Validated[GreaterThan[5] And LowerThan[10]] = 1 // Validation fails with:
  // Validation failed: 1 > 5
  // val z: Validated[GreaterThan[5] And LowerThan[10]] = 16 // Validation fails with:
  // Validation failed: 16 < 10

  accepts(10)
//  accepts(b) // doesn't compile even though binary in in range
//  accepts(1: Binary) // doesn't compile even though binary in in range

//  type NonZeroDouble = Validated[GreaterThanDouble[0d] And LowerThanDouble[0d]]
//  def inverse_refined_double(x: NonZeroDouble): Double = 1d / x

  type NonZeroInt = Validated[GreaterThan[0] Or LowerThan[0]]
  def inverse_refined_int(x: NonZeroInt): Double = 1d / x.asInstanceOf[Double]
  inverse_refined_int(1)

  type NonZeroDouble = Validated[GreaterThanDouble[0d] Or LowerThanDouble[0d]]
  def inverse_refined(x: NonZeroDouble): Double = 1d / x.asInstanceOf[Double]
  inverse_refined(2.0)

  @main def hello() = println("Hello, world")
