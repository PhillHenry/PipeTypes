package uk.co.odinconsultants.pipetypes.data

import eu.timepit.refined._
import eu.timepit.refined.api.Refined
import eu.timepit.refined.auto._
import eu.timepit.refined.numeric._

case class RefinedPatient(age: Int Refined Greater[5])
