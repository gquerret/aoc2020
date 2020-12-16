def input = new File('day16.txt').collect { it }
def yourTicketIndex = input.findIndexOf( { it == 'your ticket:' })
def nearbyTicketIndex = input.findIndexOf( { it == 'nearby tickets:' })

// Array of ListOfInterval
def validSeats = []

// Array of integers
def myTicket = []
// Array of array of integers
def nearbyTickets = []

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

println validSeats
println "-"
println fullInterval
println "-"
println myTicket
println "-"
println nearbyTickets

List result = []
nearbyTickets.each { it ->
  println "Nearby Ticket ${it}"
  it.each { it2 ->
    if (!fullInterval.isInInterval(it2))
      result.add(it2)
  }
}
println result
println result.sum()

class ListOfInterval {
  String name
  List intervals = []

  public ListOfInterval(String name) { this.name = name }

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