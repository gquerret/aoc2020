def input = new File('day16.txt').collect { it }
def yourTicketIndex = input.findIndexOf( { it == 'your ticket:' })
def nearbyTicketIndex = input.findIndexOf( { it == 'nearby tickets:' })

// Array of ListOfInterval
def validSeats = []

// Array of integers
def myTicket = []
// Array of array of integers
def nearbyTickets = []
def validNearbyTickets = []

// Parse valid seats
input.take(yourTicketIndex - 1).each { it -> 
  ListOfInterval obj = new ListOfInterval(it.split(':')[0])
  it.split(':')[1].split(" or ").each { it2 ->
    obj.intervals.add(new Interval(it2.split('-')[0].toInteger(), it2.split('-')[1].toInteger()))
  }
  validSeats.add(obj)
}
// One big list is enough for part 1, but I suspect that it won't be the case in part 2
def fullInterval = new ListOfInterval('full')
validSeats.each { it -> fullInterval.intervals.addAll(it.intervals) }
fullInterval.sort()

// Parse my ticket
input.get(yourTicketIndex + 1).split(',').each { it -> myTicket.add(it.toInteger()) }
// Parse nearby tickets
input.drop(nearbyTicketIndex + 1).each { it ->
  def list = []
  it.split(',').each {
    list.add(it.toInteger())
  }
  nearbyTickets.add(list)
}

nearbyTickets.each { it ->
  def valid = true
  it.each { it2 ->
    if (!fullInterval.isInInterval(it2))
      valid = false
  }
  if (valid)
    validNearbyTickets.add(it)
}
println "Remaining nearby tickets ${validNearbyTickets.size()}"

// Init structure
validSeats.each { it.initValidPos(myTicket.size()) }
// Remove invalid positions
validNearbyTickets.each { it ->
  for (int zz = 0; zz < myTicket.size(); zz++) {
    validSeats.each { it2 ->
      if (!it2.isInInterval(it[zz]))
        it2.validPos.remove(zz)
    }
  }
}
validSeats.each { it -> println "${it.name}: ${it.validPos}"}

// Then discard positions one by one
for (int zz = 0; zz < 20; zz++) {
  println "Round #${zz}"
  validSeats.findAll( { it -> !it.validated && (it.validPos.size() == 1) } ).each { it ->
    println "${it.name} is now validated ${it.validPos}"
    it.validated = true
    validSeats.findAll( { it2 -> !it2.validated}).each { it2 -> it.validPos.each { it3 -> it2.validPos.remove(it3)} }
  }
  validSeats.each { it -> println "${it.name}: ${it.validPos}"}
}
def result = 1L
validSeats.findAll({ it -> it.name.startsWith("departure")}).each { it ->
  println "${it.name} - Pos ${it.validPos[0]} - Ticket ${myTicket[it.validPos[0]]}"
  result *= myTicket[it.validPos[0]]
}
println "Result ${result}"

class ListOfInterval {
  String name
  List intervals = []
  Set<Integer> validPos = []
  boolean validated

  public ListOfInterval(String name) { this.name = name }

  void initValidPos(int max) { for (int zz = 0; zz < max; zz++)  validPos.add(zz) }
  void sort() {
    intervals.sort({ it1, it2 -> it1.min - it2.min })
  }

  boolean isInInterval(int value) {
    return intervals.find({ it -> (value >= it.min) && (value <= it.max) }) != null
  }

  String toString() { return "${name}: ${intervals}"}
}

class Interval {
  int min
  int max
  public Interval(int min, int max) { this.min = min; this.max = max }
  String toString() { return "[${min};${max}]" }
}
