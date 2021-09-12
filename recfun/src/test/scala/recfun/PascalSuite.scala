package recfun

import org.scalatest.funsuite.{AnyFunSuite => FunSuite}

import org.junit.runner.RunWith
import org.scalatestplus.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class PascalSuite extends FunSuite {
  import Main.pascal
  test("pascal: col=0,row=2") {
    assert(pascal(0,2) === 1)
  }

  test("pascal: col=1,row=2") {
    assert(pascal(1,2) === 2)
  }

  test("pascal: col=1,row=3") {
    assert(pascal(1,3) === 3)
  }


  test("pascal: col=0,row=0") {
    assert(pascal(0,0) === 1)
  }

  test("pascal: col=2,row=1(col oob)") {
    assertThrows[IllegalArgumentException] {
      pascal(2,1)
    }
  }

  test("pascal: col=-1,row=3(invalid col)") {
    assertThrows[IllegalArgumentException] {
      pascal(-1,3)
    }
  }
  
  test("pascal: col=0,row=-1(invalid row)") {
    assertThrows[IllegalArgumentException] {
      pascal(0,-1)
    }
  }
}
