def initList = new File('day08.txt').collect { it -> it }
def currChg = 0
println initList

while (true) {
  println "Change program at index ${currChg}"
  if (initList.get(currChg).startsWith('acc')) {
    currChg++
    continue
  }
  def list = []
  list.addAll(initList)
  String str = list.remove(currChg)
  if (str.startsWith('nop')) {
    list.add(currChg, str.replace('nop', 'jmp'))
  } else {
    list.add(currChg, str.replace('jmp', 'nop'))
  }
  currChg++
  println list

  Result rslt = execProgram(list)
  if (rslt.success) {
    println "SUCCESS ! Accum ${rslt.accumulator}"
    System.exit(0)
  } else {
    println "Failure..."
  }
}

def Result execProgram(list) {
  def currIdx = 0
  def currVal = 0
  def execd = []

  while (true) {
    if (currIdx >= list.size()) {
      println "End of program"
      return new Result(true, currVal)
    }

    if (execd.contains(currIdx)) {
      println "Infinite loop - Accumulator: ${currVal}"
      return new Result(false, currVal)
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
}

class Result {
  boolean success
  int accumulator

  public Result(boolean success, int accumulator) {
    this.success = success
    this.accumulator = accumulator
  }
}