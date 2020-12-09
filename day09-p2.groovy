def list = new File('day09.txt').collect { it -> it.toLong() }

invalid =  findInvalidNumber(list)
println invalid
def sub = subList(list, invalid)
println sub
println "Result: " + (sub.min() + sub.max())

def long findInvalidNumber(list) {
  def PREAMBLE_SZ = 25
  for (int zz = PREAMBLE_SZ; zz < list.size(); zz++) {
    def init = zz - PREAMBLE_SZ
    def found = false
    while (!found) {
      for (int xx = init + 1; !found && xx < zz; xx++) {
        if (list.get(init) + list.get(xx) == list.get(zz)) {
          found = true
        }
      }
      init++;
      if ((init == zz) && !found) {
        return list.get(zz)
      }
    }
  }
}

def List subList(list, value) {
  def strtIndex = 0

  while (true) {
    def sum = list.get(strtIndex) + list.get(strtIndex + 1)
    def endIndex = strtIndex + 2
    while (sum < value) {
      sum += list.get(endIndex++)
    }
    if (sum == value) {
      return list.subList(strtIndex, endIndex)
    } else {
      strtIndex++
    }
  }
}
