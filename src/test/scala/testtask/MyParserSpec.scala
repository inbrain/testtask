package testtask

import org.specs2.mutable.Specification

class MyParserSpec extends Specification {

  private val parser = new MyParser
  "Parser" should {
    "parse numbers" in {
      parser.parse("1") must beRight(Num("1"))
      parser.parse("001") must beRight(Num("001"))
    }

    "parse variables" in {
      parser.parse("a") must beRight(Var("a"))
      parser.parse("a1") must beRight(Var("a1"))
      parser.parse("1a") must beLeft("end of input expected")
    }

    "parse simple fun" in {
      parser.parse("f()") must beRight(Fun("f", Nil))
      parser.parse("f(1)") must beRight(Fun("f", List(Num("1"))))
      parser.parse("f(x)") must beRight(Fun("f", List(Var("x"))))
      parser.parse("f(1, x)") must beRight(Fun("f", List(Num("1"), Var("x"))))
    }
    "parse more complex fun" in {
      val res = Fun("f1", List(Num("1"), Fun("f2", List(Var("y"))), Fun("f3", List(Num("2"), Num("3")))))
      parser.parse("f1(1, f2(y), f3(2, 3))") must beRight(res)
    }

    "parse proper decode" in {
      parser.parse("decode(1, 2, 3, 4)") must
        beRight(Fun("decode", List(Num("1"), Num("2"), Num("3"), Num("4"))))
      parser.parse("decode(a, b, c, d, e, f)") must
        beRight(Fun("decode", List(Var("a"), Var("b"), Var("c"), Var("d"), Var("e"), Var("f"))))
    }

    "deny improper decode" in {
      parser.parse("decode()") must beLeft("Malformed decode function")
      parser.parse("decode(1)") must beLeft("Malformed decode function")
      parser.parse("decode(1, 2)") must beLeft("Malformed decode function")
      parser.parse("decode(1, 2, 3)") must beLeft("Malformed decode function")
      parser.parse("decode(1, 2, 3, 4, 5)") must beLeft("Malformed decode function")
    }
  }
}
