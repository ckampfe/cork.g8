package $package$

import org.scalatest._

abstract class SpecBase extends FunSpec
  with Matchers
  with OptionValues
  with Inside
  with Inspectors
