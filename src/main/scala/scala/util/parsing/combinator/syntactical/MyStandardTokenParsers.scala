package scala
package util.parsing
package combinator
package syntactical


import token._
import lexical.StdLexical
import scala.language.implicitConversions

trait MyStandardTokenParsers extends MyStdTokenParsers{
  type Tokens = StdTokens
  val lexical = new StdLexical

  //an implicit keyword function that gives a warning when a given word not is in the reserved/delimiters list
  override implicit def keyword(chars : String): Parser[String] =
    if(lexical.reserved.contains(chars) || lexical.delimiters.contains(chars)) super.keyword(chars)
    else failure("You are trying to parse \""+chars+"\", but it is neither contained in the delimiters list, nor in the reserved keyword list of your lexical object")

}






































































































































































































































































