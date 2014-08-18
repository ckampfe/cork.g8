package $package$

import org.scalatest._
import com.typesafe.config._
import environment._

abstract class SpecBase extends FunSpec
  with Matchers
  with OptionValues
  with Inside
  with Inspectors
  with DatabaseConnector
{
  environment.Env.Config = ConfigFactory.load("application-test")
}
