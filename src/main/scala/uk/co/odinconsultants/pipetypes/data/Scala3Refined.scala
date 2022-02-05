package uk.co.odinconsultants.pipetypes.data

import scala.compiletime.{erasedValue, constValue, error}
import scala.compiletime.ops.int.ToString

sealed trait Pred
class And[A <: Pred, B <: Pred] extends Pred
class Leaf extends Pred
class LowerThan[T <: Int & Singleton] extends Leaf
class GreaterThan[T <: Int & Singleton] extends Leaf

trait Validated[E <: Pred]

/**
 * Shamelessly stolen from Michel Sitko's excellent blog post her:
 * https://msitko.pl/blog/build-your-own-refinement-types-in-scala3.html
 */
implicit inline def mkValidated[V <: Int & Singleton, E <: Pred](v: V): Validated[E] =
  inline erasedValue[E] match
    case _: LowerThan[t] =>
      inline if constValue[V] < constValue[t]
        then new Validated[E] {}
        else
          inline val vs = constValue[ToString[V]]
          inline val limit = constValue[ToString[t]]
          error("Validation failed: " + vs + " < " + limit)
    case _: GreaterThan[t] =>
      inline if constValue[V] > constValue[t]
        then new Validated[E] {}
        else
          inline val vs = constValue[ToString[V]]
          inline val limit = constValue[ToString[t]]
          error("Validation failed: " + vs + " > " + limit)
    case _: And[a, b] =>
      inline mkValidated[V, a](v) match
        case _: Validated[_] =>
          inline mkValidated[V, b](v) match
            case _: Validated[_] => new Validated[E] {}


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
