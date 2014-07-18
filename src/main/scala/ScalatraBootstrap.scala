import com.cork.app._
import org.scalatra._
import javax.servlet.ServletContext
import com.cork.config.DatabaseConnector

class ScalatraBootstrap extends LifeCycle with DatabaseConnector {
  override def init(context: ServletContext) {
    context.mount(new CorkServlet, "/*")
    context.mount(new SmilesController, "/smiles/*")
  }
}
