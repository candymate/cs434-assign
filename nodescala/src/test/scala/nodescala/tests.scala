package nodescala

import scala.language.postfixOps
import scala.util.{Try, Success, Failure}
import scala.collection._
import scala.concurrent._
import ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.async.Async.{async, await}
import org.scalatest._
import NodeScala._
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class NodeScalaSuite extends FunSuite {

  test("A Future should always be completed") {
    val always = Future.always(517)

    assert(Await.result(always, 0 nanos) == 517)
  }
  test("A Future should never be completed") {
    val never = Future.never[Int]

    try {
      Await.result(never, 1 second)
      assert(false)
    } catch {
      case t: TimeoutException => // ok!
    }
  }
  test("Future.delay") {
    try {
      Await.result(Future.delay(Duration(2000, "millis")), 1 second)
      assert(false)
    } catch {
      case t: TimeoutException => // ok!
    }
  }

  test("Future.all") {
    val never = Future.never[Int]
    val always = Future.always(517)
    val always2 = Future.always(520)
    val delayedAlways = Future {
      Thread.sleep(1000)
      540
    }

    try {
      Await.result(Future.all(List(never, always)), 1 second)
      assert(false)
    } catch {
      case t: TimeoutException => // ok!
    }

    assert(Await.result(Future.all(List(always, always2)), 1 second) == List(517, 520))

    // check if futures run in parallel
    try {
      val res = Await.result(Future.all(List(delayedAlways, delayedAlways, delayedAlways)), 2 second)
      assert(res == List(540, 540, 540))
    } catch {
      case t: TimeoutException => // ok!
    }
  }

  test("Future.any") {
    val never = Future.never[Int]
    val always = Future.always(517)
    val delayedAlways = Future {
      Thread.sleep(2000)
      540
    }

    assert(Await.result(Future.any(List(delayedAlways, always)), 3 second) == 517)
    try {
      Await.result(Future.all(List(never, delayedAlways)), 1 second)
      assert(false)
    } catch {
      case t: TimeoutException => // ok!
    }
  }

  
  
  class DummyExchange(val request: Request) extends Exchange {
    @volatile var response = ""
    val loaded = Promise[String]()
    def write(s: String) {
      response += s
    }
    def close() {
      loaded.success(response)
    }
  }

  class DummyListener(val port: Int, val relativePath: String) extends NodeScala.Listener {
    self =>

    @volatile private var started = false
    var handler: Exchange => Unit = null

    def createContext(h: Exchange => Unit) = this.synchronized {
      assert(started, "is server started?")
      handler = h
    }

    def removeContext() = this.synchronized {
      assert(started, "is server started?")
      handler = null
    }

    def start() = self.synchronized {
      started = true
      new Subscription {
        def unsubscribe() = self.synchronized {
          started = false
        }
      }
    }

    def emit(req: Request) = {
      val exchange = new DummyExchange(req)
      if (handler != null) handler(exchange)
      exchange
    }
  }

  class DummyServer(val port: Int) extends NodeScala {
    self =>
    val listeners = mutable.Map[String, DummyListener]()

    def createListener(relativePath: String) = {
      val l = new DummyListener(port, relativePath)
      listeners(relativePath) = l
      l
    }

    def emit(relativePath: String, req: Request) = this.synchronized {
      val l = listeners(relativePath)
      l.emit(req)
    }
  }
  test("Server should serve requests") {
    val dummy = new DummyServer(8191)
    val dummySubscription = dummy.start("/testDir") {
      request => for (kv <- request.iterator) yield (kv + "\n").toString
    }

    // wait until server is really installed
    Thread.sleep(500)

    def test(req: Request) {
      val webpage = dummy.emit("/testDir", req)
      val content = Await.result(webpage.loaded.future, 1 second)
      val expected = (for (kv <- req.iterator) yield (kv + "\n").toString).mkString
      assert(content == expected, s"'$content' vs. '$expected'")
    }

    test(immutable.Map("StrangeRequest" -> List("Does it work?")))
    test(immutable.Map("StrangeRequest" -> List("It works!")))
    test(immutable.Map("WorksForThree" -> List("Always works. Trust me.")))

    dummySubscription.unsubscribe()
  }

}




