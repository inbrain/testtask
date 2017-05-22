package testtask

sealed trait Elem

case class Fun(name: String, params: List[Elem]) extends Elem {
  require(name != "decode" || (params.size >= 4  && params.size % 2 == 0), "Malformed decode function")
}

case class Var(name: String) extends Elem

case class Num(value: String) extends Elem