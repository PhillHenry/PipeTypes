package uk.co.odinconsultants.pipetypes.safety.experimental

import scala.compiletime.{constValue, erasedValue, error}
import math.Ordered.orderingToOrdered
import math.Ordering.Implicits.infixOrderingOps

sealed trait MyPred[T]

final class GenericValidation[V <: Singleton: Numeric: Ordering](v: V) {
  case class MyValidated[E <: MyPred[V]](t: V)
  class MyLowerThan[V] extends MyPred[V]

  val numeric = implicitly[Numeric[V]]

  inline implicit def mkValidated[E <: MyPred[V]]: MyValidated[E] = {
    import scala.compiletime.*
    import scala.compiletime.ops.int.*
    inline erasedValue[E] match
      case _: MyLowerThan[t] =>
        inline if numeric.lt(constValue[V], constValue[t])
        then new MyValidated[E](erasedValue[V]) {}
        else
          reportError[V, t](" < ")
//      case _ => reportError[V, V](" ??? ")
  }
  inline def reportError[V, T <: Singleton](op: String) = {
    inline val vs = scala.compiletime.codeOf(constValue[V])
    inline val t = scala.compiletime.codeOf(constValue[T])
    error("Validation failed: " + vs + op + t)
  }
}

object MyTest {
  def main(args: Array[String]): Unit = {
    println("hello")
//    val test = new GenericValidation[Int]()
//    import test.*
//    val a: MyValidated[MyLowerThan[10]] = 9
  }
}
