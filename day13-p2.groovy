def list = new File('day13.txt').collect { it -> it }
def count = 0L
def busList = []
list[1].split(',').each { it ->
  if (it != 'x')
    busList.add(new Bus(it.toLong(), count))
  count++
}
busList.sort { bus1, bus2 -> return bus2.num - bus1.num }
println "List: ${busList}"

def currTimer = System.currentTimeMillis()
println "Initial time: ${currTimer}"

def ts = firstCommonTime(busList[0], busList[1])
def lcmX = lcm(busList[0].num, busList[1].num)
println "Starting point ${ts} - LCM ${lcmX}"
busList.drop(1).each { it ->
  ts = firstCommonTime(busList[0], it, ts, lcmX)
  lcmX = lcm(lcmX, it.num)
  println "New starting point ${ts} - LCM ${lcmX}"
}

while (true) {
  def ok = true
  for (int zz = 4; zz < busList.size(); zz++) {
    if (((ts + busList[zz].initTS) % busList[zz].num) != 0) {
      ok = false
      break
    }
  }
  if (ok) {
    println "End time: ${System.currentTimeMillis()}"
    println "TS!!! ${ts}"
    System.exit(0)
  }
  ts += lcmX
  if ((ts % 1000000000000) < lcmX) {
    println "${System.currentTimeMillis() - currTimer} - Checkpoint ${ts}"
    currTimer = System.currentTimeMillis()
  }
}

class Bus {
  long initTS
  long num
  public Bus(num, initTS) {
    this.num = num
    this.initTS = initTS
  }
  public String toString() {
    return "Bus ${num} + ${initTS}"
  }
}

def long lcm(long number1, long number2) {
    if (number1 == 0 || number2 == 0)
        return 0;
    long high = number1 > number2 ? number1 : number2;
    long low = number1 < number2 ? number1 : number2;
    long lcm = high;
    while (lcm % low != 0) {
        lcm += high;
    }
    return lcm;
}

def long firstCommonTime(Bus bus1, Bus bus2) {
  def ts = 150000000000 * bus1.num - bus1.initTS
  while (((ts + bus2.initTS) % bus2.num) != 0) {
    ts += bus1.num
  }
  return ts;
}

def long firstCommonTime(Bus bus1, Bus bus2, long minVal, long delta) {
  def ts = minVal
  while (((ts + bus2.initTS) % bus2.num) != 0) {
    ts += delta
  }
  return ts;
}
