package uk.co.odinconsultants.pipetypes.safety

import scala.compiletime.{erasedValue, constValue, error}


object Types {

  sealed trait Pred
  class And[A <: Pred, B <: Pred] extends Pred
  class Or[A <: Pred, B <: Pred] extends Pred
  class Leaf extends Pred
  class LowerThan[T <: Int & Singleton] extends Leaf
  class GreaterThan[T <: Int & Singleton] extends Leaf
  class LowerThanDouble[T <: Double & Singleton] extends Leaf
  class GreaterThanDouble[T <: Double & Singleton] extends Leaf
  case class ValidatedDouble[E <: Pred](t: Double & Singleton)

  case class Validated[E <: Pred](t: Int & Singleton)
  class LowerThanLong[T <: Long & Singleton] extends Leaf
  class GreaterThanLong[T <: Long & Singleton] extends Leaf
  case class ValidatedLong[E <: Pred](t: Long & Singleton)


  /**
   * Shamelessly stolen from Michel Sitko's excellent blog post here:
   * https://msitko.pl/blog/build-your-own-refinement-types-in-scala3.html
   */
  implicit inline def mkValidated[V <: Int & Singleton, E <: Pred](v: V): Validated[E] =
    import scala.compiletime.*
    import scala.compiletime.ops.int.*
    inline erasedValue[E] match
      case _: LowerThan[t] =>
        inline if constValue[V] < constValue[t]
        then new Validated[E](erasedValue[V]) {}
        else
          inline val vs = constValue[ToString[V]]
          inline val limit = constValue[ToString[t]]
          error("Validation failed: " + vs + " < " + limit)
      case _: GreaterThan[t] =>
        inline if constValue[V] > constValue[t]
        then new Validated[E](erasedValue[V]) {}
        else
          inline val vs = constValue[ToString[V]]
          inline val limit = constValue[ToString[t]]
          error("Validation failed: " + vs + " > " + limit)
      case _: And[a, b] =>
        inline mkValidated[V, a](v) match
          case _: Validated[_] =>
            inline mkValidated[V, b](v) match
              case _: Validated[_] => new Validated[E](erasedValue[V]) {}
      case _: Or[a, b] =>
        inline mkValidated[V, a](v) match
          case _: Validated[_] => new Validated[E](erasedValue[V]) {}
          case _: Pred =>
            inline mkValidated[V, b](v) match
              case _: Validated[_] => new Validated[E](erasedValue[V]) {}

  implicit inline def mkValidatedLong[V <: Long & Singleton, E <: Pred](v: V): ValidatedLong[E] =
    inline erasedValue[E] match
      case _: LowerThanLong[t] =>
        inline if constValue[V] < constValue[t]
        then new ValidatedLong[E](erasedValue[V]) {}
        else
          inline val vs = constValue[V]
          inline val limit = constValue[t]
          error("Validation failed: " + vs + " < " + limit)
      case _: GreaterThanLong[t] =>
        inline if constValue[V] > constValue[t]
        then new ValidatedLong[E](erasedValue[V]) {}
        else
          inline val vs = constValue[V]
          inline val limit = constValue[t]
          error("Validation failed: " + vs + " > " + limit)

  implicit inline def mkValidatedDouble[V <: Double & Singleton, E <: Pred](v: V): ValidatedDouble[E] =
    inline erasedValue[E] match
      case _: LowerThanDouble[t] =>
        inline if constValue[V] < constValue[t]
        then new ValidatedDouble[E](erasedValue[V]) {}
        else
          inline val vs = constValue[V]
          inline val limit = constValue[t]
          error("Validation failed: " + vs + " < " + limit)
      case _: GreaterThanDouble[t] =>
        inline if constValue[V] > constValue[t]
        then new ValidatedDouble[E](erasedValue[V]) {}
        else
          inline val vs = constValue[V]
          inline val limit = constValue[t]
          error("Validation failed: " + vs + " > " + limit)
}