package testtask

class Rewriter(render: Elem ⇒ String, transform: Elem ⇒ Elem, parser: MyParser) {
  def rewrite(input: String) : Either[String, String] = {
    parser.parse(input).right.map(render.compose(transform))
  }
}