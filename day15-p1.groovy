def list = new File('day15.txt').collect { it.toInteger() } 

def currentPos = list.size()
while (currentPos < 2021) {
  def previousVal = list[currentPos - 1]
  def previousPos = currentPos - 2
  while ((previousPos >= 0) && (list[previousPos] != previousVal)) {
    previousPos--
  }
  println "Turn ${currentPos + 1} - Val ${previousVal} - Pos ${previousPos}"
  if (previousPos == -1) {
    list[currentPos] = 0
  } else {
    list[currentPos] = currentPos - previousPos - 1
  }
  currentPos++
}
println "Pos 2020 ${list[2019]}"
