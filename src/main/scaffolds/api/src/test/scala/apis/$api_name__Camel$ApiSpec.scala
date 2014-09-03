package $package$

import _root_.akka.actor.{Props, ActorSystem}

class $api_name;format="Camel"$ApiSpec extends SpecBase {
  implicit val system = ActorSystem()
  addServlet(new $api_name;format="Camel"$Api(system), "/$api_name;format="lower"s$/*")

  test("GET /$api_name;format="lower"$s") {
    get("/$api_name;format="lower"$s") (pending)
  }
}
