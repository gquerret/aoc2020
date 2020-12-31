def CARD_PUB_KEY = 6930903
def DOOR_PUB_KEY = 19716708

def cardLoopSize = getLoopSize(7, CARD_PUB_KEY)
def doorLoopSize = getLoopSize(7, DOOR_PUB_KEY)

println "Loop $cardLoopSize $doorLoopSize"
println "Encryption key 1: ${execLoop(DOOR_PUB_KEY, cardLoopSize)}"
println "Encryption key 2: ${execLoop(CARD_PUB_KEY, doorLoopSize)}"

def long getLoopSize(long subjectNumber, long expectedValue) {
  def sz = 0
  def rslt = 1L
  while (rslt != expectedValue) {
    rslt = rslt * subjectNumber
    rslt = rslt % 20201227
    sz++
  }
  return sz
}

def long execLoop(long subjectNumber, long loopSz) {
  def rslt = 1
  for (int zz = 0; zz < loopSz; zz++) {
    rslt = rslt * subjectNumber
    rslt = rslt % 20201227
  }
  return rslt
}
