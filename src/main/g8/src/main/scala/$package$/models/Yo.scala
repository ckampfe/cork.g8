import org.scalatra._
import scalikejdbc._, SQLInterpolation._
import com.github.nscala_time.time.Imports._


case class Yo(
  id: Int,
  greeting: String
)

object Yo extends SQLSyntaxSupport[Yo] {
  implicit val session = AutoSession
}
