def list = new File('day10.txt').collect { it -> it.toInteger() }
list.add(0)
list.add(list.max() + 3)
list.sort()

def map = [:]
map.put(0, 1L)
def currIdx = 1

list.drop(1).each { it ->
  def currVal = list.get(currIdx)
  def prev1 = list.get(currIdx - 1)
  def prev2 = currIdx >= 2 ? list.get(currIdx - 2) : -100
  def prev3 = currIdx >= 3 ? list.get(currIdx - 3) : -100
  println "Index ${currIdx} CurrVal ${currVal} Prev ${prev1} ${prev2} ${prev3}"

  def paths = (prev1 >= currVal - 3 ? map.get(prev1) : 0) + (prev2 >= currVal - 3 ? map.get(prev2) : 0) + (prev3 >= currVal - 3 ? map.get(prev3) : 0)
  map.put(currVal, paths)

  currIdx++
}

println "Result: ${map.get(list.max())}"
