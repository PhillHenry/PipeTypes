package uk.co.odinconsultants.pipetypes.data.matrix
import scala.compiletime.{erasedValue, constValue, error}

type D = Int & Singleton
class Matrix[X <: D, Y <: D]

object Matrix {

  opaque type M[X <: D, Y <: D] = Matrix[X, Y]
  transparent inline def apply[X <: D, Y <: D]: M[X, Y] = inline erasedValue[X] match
    case x: D if x <= 0 => error("boom!")
    case _              =>
      inline erasedValue[Y] match
        case y: D if y <= 0 => error("boom!")
        case _              => new Matrix[X, Y]

  def main(args: Array[String]): Unit = {
    println("hello, world")
    Matrix[1, 2]
  }

}
