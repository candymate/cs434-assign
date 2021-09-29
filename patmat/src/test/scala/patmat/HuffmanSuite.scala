package patmat

import org.scalatest.funsuite.{AnyFunSuite => FunSuite}

import org.junit.runner.RunWith
import org.scalatestplus.junit.JUnitRunner

import patmat.Huffman._

@RunWith(classOf[JUnitRunner])
class HuffmanSuite extends FunSuite {
  trait TestTrees {
    val t1 = Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5)
    val t2 = Fork(Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5), Leaf('d',4), List('a','b','d'), 9)
  }

  test("weight of a larger tree") {
    new TestTrees {
      assert(weight(t1) === 5)
    }
  }

  test("chars of a larger tree") {
    new TestTrees {
      assert(chars(t2) === List('a','b','d'))
    }
  }

  test("string2chars(\"hello, world\")") {
    assert(string2Chars("hello, world") === List('h', 'e', 'l', 'l', 'o', ',', ' ', 'w', 'o', 'r', 'l', 'd'))
  }

  test("makeOrderedLeafList for some frequency table") {
    assert(makeOrderedLeafList(List(('t', 2), ('e', 1), ('x', 3))) === List(Leaf('e',1), Leaf('t',2), Leaf('x',3)))
  }

  test("combine of some leaf list") {
    val leaflist = List(Leaf('e', 1), Leaf('t', 2), Leaf('x', 4))
    assert(combine(leaflist) === List(Fork(Leaf('e',1),Leaf('t',2),List('e', 't'),3), Leaf('x',4)))
  }

  test("decode and encode a very short text should be identity") {
    new TestTrees {
      assert(decode(t1, encode(t1)("ab".toList)) === "ab".toList)
    }
  }


  test("times basic tests") {
    assert(times(List('a', 'b', 'a', 'c')) === List(('a', 2), ('b', 1), ('c', 1)))
    assert(times(List('a', 'a', 'a')) === List(('a', 3)))
    assert(times(List()) === List())
    assert(times(List('e', 'd', 'c', 'b', 'a')) === List(('e', 1), ('d', 1), ('c', 1), ('b', 1), ('a', 1)))
  }

  test("singleton tests") {
    new TestTrees {
      assert(singleton(List(t1, t2)) === false)
      assert(singleton(List(t1)) === true)
      assert(singleton(List()) === false)
    }
  }

  test("until(singleton, combine)(trees)") {
    val leaflist = List(Leaf('e', 1), Leaf('t', 2), Leaf('x', 4))
    assert(until(singleton, combine)(leaflist) === 
      Fork(Fork(Leaf('e',1),Leaf('t',2),List('e', 't'),3), Leaf('x',4), List('e', 't', 'x'), 7))
  }

  test("createCodeTree basic test") {
    val testData = string2Chars("xtxetxx")
    val expected = Fork(Fork(Leaf('e',1),Leaf('t',2),List('e', 't'),3), Leaf('x',4), List('e', 't', 'x'), 7)
    assert(createCodeTree(testData) === expected)
  }

  test("decodedSecret test") {
    assert(decodedSecret.mkString("") == "huffmanestcool")
  }

  test("encode test") {
    assert(encode(frenchCode)(string2Chars("huffmanestcool")) === secret)
  }

  test("encode and decode identity test 2") {
    val plaintext = string2Chars("huffmanestcool")
    assert(decode(frenchCode, encode(frenchCode)(plaintext)) === plaintext)
  }

  test("quickEncode test") {
    assert(quickEncode(frenchCode)(string2Chars("huffmanestcool")) === secret)
  }

  test("quickEncode and decode identity tests") {
    new TestTrees {
      assert(decode(t1, quickEncode(t1)("ab".toList)) === "ab".toList)
    }
    val plaintext = string2Chars("huffmanestcool")
    assert(decode(frenchCode, quickEncode(frenchCode)(plaintext)) === plaintext)
  }
}
