package uk.co.odinconsultants.pipetypes.safety

import scala.compiletime.{erasedValue, constValue, error}
import scala.compiletime.ops.int.ToString


object Types {

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

}
