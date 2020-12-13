def list = new File('day13.txt').collect { it -> it }

def initTs = list[0].toInteger()
def list2 = list[1].split(',').findAll { it -> it != 'x'}.collect {it -> it.toInteger() }

println "Timestamp: ${initTs}"
println "List: ${list2}"

def minWait = -1
def bus = -1
list2.each { it ->
  def num = (int) (initTs / it)
  def over = (num + 1) * it - initTs
  if ((minWait == -1) || (over < minWait)) {
    minWait = over
    bus = it
  }
}
println "Bus ${bus} - Wait ${minWait} - Result ${bus * minWait}"
