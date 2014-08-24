package $package$

import org.scalatra.test.scalatest._
import org.scalatest.FunSuiteLike
import com.typesafe.config._
import environment._

abstract class SpecBase extends ScalatraSuite
  with FunSuiteLike
  with DatabaseConnector
{
  environment.Env.Config = ConfigFactory.load("application-test")
}
