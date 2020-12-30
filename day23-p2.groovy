MAX_VAL = 1000000

def firstCup
def lastCup
def initCup = []
def highestVal = -1
new File('day23.txt').text.each { it ->
  Cup cup = new Cup(it.toLong())
  initCup[cup.value] = cup
  if (highestVal < cup.value)
    highestVal = cup.value
  if (lastCup != null) {
    lastCup.next = cup
  }
  if (firstCup == null)
    firstCup = cup
  lastCup = cup
}
for (int zz = highestVal + 1; zz <= MAX_VAL; zz++) {
  Cup cup = new Cup(zz)
  cup.prevValCup = lastCup
  lastCup.next = cup
  lastCup = lastCup.next
}
lastCup.next = firstCup

Cup currCup = firstCup
for (int zz = 0; zz < highestVal + 1; zz++) {
  if (currCup.value == 1)
    currCup.prevValCup = lastCup
  else
    currCup.prevValCup = initCup[currCup.value - 1]
  currCup = currCup.next
}

for (int zz = 0; zz <= 10000000 - 1; zz++) {
  println "-- Move ${zz + 1} --"
  Cup take = firstCup.next
  firstCup.next = firstCup.next.next.next.next
  take.next.next.next = null
  Cup destination = firstCup.getDestination(take.value, take.next.value, take.next.next.value)
  take.next.next.next = destination.next
  destination.next = take

  firstCup = firstCup.next
}
println "Result ${firstCup.getResult()}"

class Cup {
  long value
  Cup next
  Cup prevValCup

  public Cup(long value) {
    this.value = value;
  }

  public String toString() {
    return "${value} "
  }

  public long getResult() {
    Cup cupOne = this
    while (cupOne.value != 1) {
      cupOne = cupOne.next
    }
    println "${cupOne.next.value} * ${cupOne.next.next.value}"
    return cupOne.next.value * cupOne.next.next.value
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

  public Cup getDestination(long val1, long val2, long val3) {
    Cup prevCup = this.prevValCup
    while ((prevCup.value == val1) || (prevCup.value == val2) || (prevCup.value == val3)) {
      prevCup = prevCup.prevValCup
    }
    return prevCup
  }
}
