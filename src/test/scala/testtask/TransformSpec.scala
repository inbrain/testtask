package testtask

import org.specs2.mutable.Specification

class TransformSpec extends Specification{

  "Transformer" should {
    "transform basic" in {
      val (expr, s, r, default) = (Var("expr"), Var("s"), Var("r"), Var("default"))
      val initial = Fun("decode", List(expr, s, r, default))
      val res = Transform(initial)
      res mustEqual Fun("if", List(Fun("eq", List(expr, s)), r, default))
    }

    "transfrom example 1" in {
      val (test, one, two, three, four, default) =
        (Fun("test", Nil), Num("1"), Num("2"), Num("3"), Num("4"), Var("def"))
      val initial = Fun("fun", List(Fun("decode", List(test, one, two, three, four, default))))
      val res = Transform(initial)
      res mustEqual Fun("fun", List(Fun("if",
        List(Fun("eq", List(test, one)), two,
          Fun("if", List(Fun("eq", List(test, three)), four, default))))))
      Render(res) mustEqual "fun(if(eq(test(), 1), 2, if(eq(test(), 3), 4, def)))"
    }

    "transform example 2" in {
      val (x, y, one, two, zero) = (Var("x"), Var("y"), Num("1"), Num("2"), Num("0"))
      val initial = Fun("decode", List(x, one, Fun("decode", List(y, one, two, zero)), zero))
      val res = Transform(initial)
      res mustEqual Fun("if",
        List(Fun("eq", List(x, one)), Fun("if",
          List(Fun("eq", List(y, one)), two, zero)), zero))
      Render(res) mustEqual "if(eq(x, 1), if(eq(y, 1), 2, 0), 0)"
    }
  }
}
