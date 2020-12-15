def results = new int[30000010]
def lastPos = new int[30000010]
def penPos  = new int[30000010]
for (int zz = 0; zz < lastPos.length; zz++) {
  lastPos[zz] = -1
  penPos[zz] = -1
}

def currentPos = 0
new File('day15.txt').each { it ->
  results[currentPos] = it.toInteger().intValue()
  lastPos[results[currentPos]] = currentPos
  currentPos++
}

long strt = System.currentTimeMillis()
while (currentPos < 30000001) {
  def previousVal = results[currentPos - 1]
  if (lastPos[previousVal] == -1) {
    results[currentPos] = 0
  } else {
    if (penPos[previousVal] == -1) {
      results[currentPos] = 0
    } else {
      results[currentPos] = lastPos[previousVal] - penPos[previousVal]
    }
    penPos[results[currentPos]] = lastPos[results[currentPos]]
  }
  lastPos[results[currentPos]] = currentPos

  currentPos++
}
println "Answer: ${results[29999999]} in ${System.currentTimeMillis() - strt} ms"
