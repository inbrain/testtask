package testtask

import scala.util.parsing.combinator.syntactical.MyStandardTokenParsers

class MyParser extends MyStandardTokenParsers {
  lexical.delimiters += ("(", ")", ",")

  private def variable: Parser[Var] = ident ^^ {
    case x ⇒ Var(x)
  }

  private def num: Parser[Num] = numericLit ^^ {
    case x ⇒ Num(x)
  }

  private def fun: Parser[Fun] = ident ~ "(" ~ repsep(expr, ",") ~ ")" ^? ({
    case name ~ _ ~ params ~ _ if name != "decode" || (params.size >= 4 && params.size % 2 == 0) ⇒
      Fun(name, params)
  }, f ⇒ s"Malformed decode function")

  private def expr: Parser[testtask.Elem] = fun | variable |  num

  def parse(input: String): Either[String, testtask.Elem] = {
    phrase(expr)(new lexical.Scanner(input)) match {
      case Success(result, _) ⇒ Right(result)
      case Failure(msg, _) ⇒ Left(msg)
      case Error(msg, _) ⇒ Left(msg)
    }
  }

}
