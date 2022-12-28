package uk.co.odinconsultants.pipetypes.data.matrix
import scala.compiletime.{erasedValue, constValue, error}

type D = Int & Singleton
class Matrix[X <: D, Y <: D]

object Matrix {
  inline val ValidDimensionMsg = "Matrix dimensions must be > 0"

  opaque type M[X <: D, Y <: D] = Matrix[X, Y]

  transparent inline def apply[X <: D, Y <: D](xs: List[Double]): M[X, Y] = inline erasedValue[X] match
    case x: D if x <= 0 => error(ValidDimensionMsg)
    case _              =>
      inline erasedValue[Y] match
        case y: D if y <= 0 => error(ValidDimensionMsg)
        case _              => new Matrix[X, Y]

  transparent inline def invert[X <: D](m: M[X, X]): M[X, X] = m // TODO the actual inversion
  transparent inline def transpose[X <: D, Y <: D](m: M[X, Y]): M[Y, X] = new Matrix[Y, X] // TODO the actual transpose

}
