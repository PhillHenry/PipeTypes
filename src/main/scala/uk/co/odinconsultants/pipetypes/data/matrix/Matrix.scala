package uk.co.odinconsultants.pipetypes.data.matrix
import scala.compiletime.{erasedValue, constValue, error}
//import io.github.iltotore.iron.*, constraint.*, iterable.constraint.*

type D = Int & Singleton
class Matrix[X <: D, Y <: D]

object Matrix {
  inline val InvalidDimensionMsg    = "Matrix dimensions must be > 0"
  inline val InvalidCoefficientsMsg = "Number of coefficients incompatible with dimensions"

  opaque type M[X <: D, Y <: D] = Matrix[X, Y]

  transparent inline def apply[X <: D, Y <: D](inline xs: List[Double]): M[X, Y] =
    import scala.compiletime.*
    import scala.compiletime.ops.int.*
    inline erasedValue[X] match
      case x: D if x <= 0 => error(InvalidDimensionMsg)
      case x              =>
        inline erasedValue[Y] match
          case y: D if y <= 0                     => error(InvalidDimensionMsg)
          // "inline match can only work on the static type of a value. List("a", "b") has the
          // type List[String] which can not be further inspected"
          // https://github.com/lampepfl/dotty/issues/14917
          case y: D if (x * y) != checkLength(xs) =>
            error(
              "Number of coefficients incompatible with dimensions"
            ) // "Cannot reduce inline match..."
          case _                                  => new Matrix[X, Y]
  transparent inline def checkLength(inline xs: List[Double]): Int               =
    ${ checkLengthC('xs) }
  import scala.quoted._
  def checkLengthC(v: Expr[List[Double]])(using Quotes): Expr[Int] =
//    val res = v.valueOrError.startsWith(pred.valueOrError)
    Expr(v.valueOrAbort.size)

  transparent inline def invert[X <: D](m: M[X, X]): M[X, X]            = m // TODO the actual inversion
  transparent inline def transpose[X <: D, Y <: D](m: M[X, Y]): M[Y, X] =
    new Matrix[Y, X] // TODO the actual transpose

}
