package testtask

import org.specs2.mutable.Specification
import org.specs2.specification.Scope

class RewriterSpec extends Specification{

  "Rewriter" should {
    "comply to requirements" in new TestScope {
      testInstance.rewrite("fun(decode(test(), 1,2,  3,4,  def))") must
        beRight("fun(if(eq(test(), 1), 2, if(eq(test(), 3), 4, def)))")
      testInstance.rewrite("decode(x, 1, decode(y, 1, 2, 0), 0)") must
        beRight("if(eq(x, 1), if(eq(y, 1), 2, 0), 0)")
    }
  }
  trait TestScope extends Scope {
    val transform = Transform
    val render = Render
    val parser = new MyParser

    val testInstance = new Rewriter(render, transform, parser)
  }

}
