def deck1 = []
def deck2 = []
def currentList

new File('day22.txt').each { it ->
  if (it == "Player 1:")
    currentList = deck1
  else if (it == "Player 2:")
    currentList = deck2
  else if (it != "")
    currentList.add(it.toInteger())
}

GameWinner rslt = playGame(1, deck1, deck2)
println "${rslt.winner} --- ${getScore(rslt.winner == 1 ? rslt.deck1 : rslt.deck2)}"

def GameWinner playGame(int level, List deck1, List deck2) {
  println "Starting game level $level with $deck1 and $deck2"

  List history1 = [], history2 = []

  def gameWon = false
  while (!gameWon) {
    String s1 = deck2str(deck1), s2 = deck2str(deck2)
    if (history1.contains(s1) || history2.contains(s2)) {
      // println "Safety net !!!"
      return new GameWinner(1, deck1, deck2, 0)
    }
    history1.add(s1)
    history2.add(s2)

    Integer c1 = deck1.remove(0)
    Integer c2 = deck2.remove(0)
    if ((deck1.size() >= c1) && (deck2.size() >= c2)) {
      GameWinner subGame = playGame(level + 1, deck1.take(c1).clone(), deck2.take(c2).clone())
      if (subGame.winner == 1) {
        deck1.add(c1)
        deck1.add(c2)
      } else if (subGame.winner == 2) {
        deck2.add(c2)
        deck2.add(c1)
      }
    } else {
      if (c1 > c2) {
        deck1.add(c1)
        deck1.add(c2)
      } else {
        deck2.add(c2)
        deck2.add(c1)
      }
    }
    gameWon = deck1.isEmpty() || deck2.isEmpty()
  }
  
  return new GameWinner(deck1.isEmpty() ? 2 : 1, deck1, deck2, 0)
}

def String deck2str(List deck) {
  def rslt = ""
  deck.each { it -> rslt += (rslt.length() == 0 ? "" : ",") + it }
  return rslt
}

def int getScore(List deck) {
  def mult = deck.size()
  def rslt = 0
  deck.each { it ->
    rslt += mult-- * it
  }
  return rslt
}

class GameWinner {
  int winner
  List deck1, deck2
  long score

  public GameWinner(winner, deck1, deck2, score) {
    this.winner = winner
    this.deck1 = deck1
    this.deck2 = deck2
    this.score = score
  }
}
