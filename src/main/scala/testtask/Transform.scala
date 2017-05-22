package testtask

object Transform extends ((Elem) ⇒ Elem){
  def apply(elem: Elem): Elem = {

    elem match {

      case Fun("decode", expr :: rest) ⇒
        rest.foldRight[(Option[Elem], Option[Elem])]((None, None)) {
          case (current, (None, None)) ⇒ (None, Some(Transform(current)))
          case (current, (None, Some(defaultE))) ⇒ (Some(Transform(current)), Some(defaultE))
          case (current, (Some(comparison), Some(defaultE))) ⇒
            val ifFunc = Fun("if", List(Fun("eq", List(expr, current)), comparison, defaultE))
            (None, Some(ifFunc))
        }._2.get

      case Fun(name, elems) ⇒ Fun(name, elems.map(Transform.apply))
      case other ⇒ other
    }
  }
}
