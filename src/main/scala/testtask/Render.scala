package testtask

object Render extends ((Elem) ⇒ String) {
  //not tail recursive which is a flaw
  def apply(elem: Elem): String = {
    elem match {
      case Fun(name, params) ⇒ s"$name(${params.map(Render.apply).mkString(", ")})"
      case Var(name) ⇒ name
      case Num(value) ⇒ value.toString
    }
  }
}
