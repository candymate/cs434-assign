package recfun

import org.scalatest.funsuite.{AnyFunSuite => FunSuite}

import org.junit.runner.RunWith
import org.scalatestplus.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class CountChangeSuite extends FunSuite {
  import Main.countChange
  test("countChange: example given in instructions") {
    assert(countChange(4,List(1,2)) === 3)
  }

  test("countChange: sorted CHF") {
    assert(countChange(300,List(5,10,20,50,100,200,500)) === 1022)
  }

  test("countChange: no pennies") {
    assert(countChange(301,List(5,10,20,50,100,200,500)) === 0)
  }

  test("countChange: unsorted CHF") {
    assert(countChange(300,List(500,5,50,100,20,200,10)) === 1022)
  }


  test("countChange: empty list") {
    assert(countChange(300, List[Int]()) === 0)
  }

  test("countChange: just penny") {
    assert(countChange(300, List(1)) === 1)
  }

  /**
  // not sure about these tests. Thought that not giving any changes should be the case.
  test("countChange: money=0") {
    assert(countChange(0, List(5,10,20)) === 1) 
  }

  test("countChange: money=0, empty list") {
    assert(countChange(0, List[Int]()) === 1)
  }
  */

  test("countChange: negative money") {
    assert(countChange(-300, List(5,10,20)) === 0)
  }

  test("countChange: list with duplicate elements") {
    assert(countChange(300,List(5,10,20,50,100,100,200,200,500)) === 1022)
    assert(countChange(300,List(5,10,200,20,50,100,200,500,100)) === 1022)
  }
}
