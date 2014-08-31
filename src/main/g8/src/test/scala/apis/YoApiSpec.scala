package $package$

import _root_.akka.actor.{Props, ActorSystem}

class YoApiSpec extends SpecBase {
  implicit val implicitAkka = ActorSystem()
  addServlet(new YoApi(implicitAkka), "/yos/*")

  test("GET /yos") {
    get("/yos") {
      status should be (200)
      body should include ("Yo! Welcome to Cork!")
    }
  }
}
