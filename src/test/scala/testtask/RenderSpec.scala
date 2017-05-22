package testtask

import org.specs2.mutable.Specification

class RenderSpec extends Specification {

  "Render" should {
    "properly render simple cases" in {
      Render(Var("x")) mustEqual "x"
      Render(Num("01")) mustEqual "01"
      Render(Fun("f", Nil)) mustEqual "f()"
    }
    "properly render complex cases" in {
      Render(Fun("f", List(Fun("g", List(Var("x"), Num("10")))))) mustEqual "f(g(x, 10))"
    }
  }
}
