package recfun
import common._

object Main {
  def main(args: Array[String]) {
    println("Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(pascal(col, row) + " ")
      println()
    }
  }

  /**
   * Exercise 1
   */
  def pascal(c: Int, r: Int): Int = {
    if (c < 0 || r < 0) throw new IllegalArgumentException
    else if (c == 0 || r == 0 || c == r) 1
    else pascal(c-1, r-1) + pascal(c, r-1)
  }

  /**
   * Exercise 2
   */
  def balance(chars: List[Char]): Boolean = {
    def balance_r(chars_r: List[Char], aux: Int): Int = {
      if (chars_r.isEmpty) aux
      else if (chars_r.head == '(') balance_r(chars_r.tail, aux+1)
      else if (chars_r.head == ')') {
        if (aux > 0) balance_r(chars_r.tail, aux-1)
        else -1
      }
      else balance_r(chars_r.tail, aux)
    }
    if (chars.isEmpty) true
    else if (balance_r(chars, 0) == 0) true
    else false
  }

  /**
   * Exercise 3
   */
  def countChange(money: Int, coins: List[Int]): Int = {
    def countChange_r(money_r: Int, coins_s: List[Int]): Int = {
      if (coins_s.isEmpty) 0
      else if (money_r < 0) 0
      else if (money_r == 0) 1
      else countChange_r(money_r - coins_s.head, coins_s) + countChange_r(money_r, coins_s.tail)
    }
    if (coins.isEmpty) 0
    else if (money < 0) 0
    else if (money == 0) 1
    else countChange_r(money, coins.sortWith(_ > _)) 
  }
}
