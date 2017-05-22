package scala
package util.parsing
package combinator
package syntactical

import token._
import scala.language.implicitConversions
import java.util.concurrent.ConcurrentHashMap

trait MyStdTokenParsers extends TokenParsers {
  type Tokens <: StdTokens
  import lexical.{Keyword, NumericLit, StringLit, Identifier}

  protected val keywordCache = new ConcurrentHashMap[String, Parser[String]]()

  /** A parser which matches a single keyword token.
    *
    * @param chars    The character string making up the matched keyword.
    * @return a `Parser` that matches the given string
    */
  implicit def keyword(chars: String): Parser[String] = {
    val prevValue = keywordCache.get(chars)
    if (prevValue == null) {
      val newValue = accept(Keyword(chars)) ^^ (_.chars)
      keywordCache.putIfAbsent(chars, newValue)
      newValue
    }
    else prevValue
  }

  /** A parser which matches a numeric literal */
  def numericLit: Parser[String] =
    elem("number", _.isInstanceOf[NumericLit]) ^^ (_.chars)

  /** A parser which matches a string literal */
  def stringLit: Parser[String] =
    elem("string literal", _.isInstanceOf[StringLit]) ^^ (_.chars)

  /** A parser which matches an identifier */
  def ident: Parser[String] =
    elem("identifier", _.isInstanceOf[Identifier]) ^^ (_.chars)
}
