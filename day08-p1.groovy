def list = new File('day08.txt').collect { it -> it }

def currIdx = 0
def currVal = 0
def execd = []

while (true) {
  if (execd.contains(currIdx)) {
    println "Accumulator: ${currVal}"
    System.exit(0)
  } else {
    execd.add(currIdx)
  }

  String[] splt = list.get(currIdx).split(' ')
  if (splt[0] == 'nop') {
    currIdx++
  } else if (splt[0] == 'acc') {
    currIdx++
    currVal += splt[1].toInteger()
  } else if (splt[0] == 'jmp') {
    currIdx += splt[1].toInteger()
  }
}
