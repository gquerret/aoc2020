def firstCup
def lastCup
new File('day23.txt').text.each { it ->
  Cup cup = new Cup(it.toInteger())
  if (lastCup != null)
    lastCup.next = cup
  if (firstCup == null)
    firstCup = cup
  lastCup = cup
}
lastCup.next = firstCup

for (int zz = 0; zz <= 99; zz++) {
  println "-- Move ${zz + 1} --"
  println "cups: ${firstCup.getList()}"
  Cup take = firstCup.next
  firstCup.next = firstCup.next.next.next.next
  take.next.next.next = null
  println "pick up: ${take.getList()}"
  Cup destination = firstCup.getDestination()
  println "destination: ${destination}"
  take.next.next.next = destination.next
  destination.next = take
  firstCup = firstCup.next
}
println "Result ${firstCup.getResult()}"

class Cup {
  int value
  Cup next

  public Cup(int value) {
    this.value = value;
  }

  public String toString() {
    return "${value} "
  }

  public String getResult() {
    Cup cupOne = this
    while (cupOne.value != 1) {
      cupOne = cupOne.next
    }
    Cup currCup = cupOne.next
    def rslt = ""
    while (currCup != cupOne) {
      rslt += currCup.value
      currCup = currCup.next
    }
    return rslt
  }

  public String getList() {
    def rslt = this.toString()
    Cup c = this.next
    while ((c != this) && (c != null)) {
      rslt += c.toString()
      c = c.next
    }
    return rslt
  }

  public Cup getDestination() {
    int currVal = this.value
    while (true) {
      currVal = ((currVal + 7) % 9) + 1
      Cup currCup = this.next
      while (currCup != this) {
        if (currCup.value == currVal)
          return currCup
        currCup = currCup.next
      }
    }
  }
}
