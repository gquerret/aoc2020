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

def gameWon = false
while (!gameWon) {
  Integer c1 = deck1.remove(0)
  Integer c2 = deck2.remove(0)
  if (c1 > c2) {
    deck1.add(c1)
    deck1.add(c2)
  } else {
    deck2.add(c2)
    deck2.add(c1)
  }
  gameWon = deck1.isEmpty() || deck2.isEmpty()
}
println deck1
println deck2

def winningDeck = deck1.isEmpty() ? deck2 : deck1
def mult = winningDeck.size()
def rslt = 0
winningDeck.each { it ->
  println "${mult} * ${it}"
  rslt += mult-- * it
}
println rslt
