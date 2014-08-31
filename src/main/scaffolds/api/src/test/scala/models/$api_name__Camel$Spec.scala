package $package$

import _root_.akka.actor.{Props, ActorSystem}

class $api_name;format="Camel"$Spec extends SpecBase {
  implicit val system = ActorSystem()
  addServlet(new $api_name;format="Camel"$Api(system), "/$api_name;format="lower"$/*")
}
