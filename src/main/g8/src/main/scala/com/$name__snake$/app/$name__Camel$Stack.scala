package $package$

import org.scalatra._
import javax.servlet.http.HttpServletRequest
import collection.mutable

trait $name;format="Camel"$Stack extends ScalatraServlet {
  notFound {
    // remove content type in case it was set through an action
    contentType = null
    resourceNotFound()
  }
}
