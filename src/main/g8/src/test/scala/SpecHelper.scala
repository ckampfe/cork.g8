package $package$

import org.scalatra.test.scalatest._
import org.scalatest.FunSuiteLike
import com.typesafe.config._
import environment._

abstract class SpecBase extends ScalatraSuite
  with FunSuiteLike
  with DatabaseConnector
{
  val env = new Env("application-test")
  init(env)
}
