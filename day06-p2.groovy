def list = []
def subList = [ ] as Set
def newGroup = true

new File('day06.txt').collect { it -> 
  if (it.isEmpty()) {
    list.add(subList)
    subList = [ ] as Set
    newGroup = true
  } else {
    if (newGroup) {
      it.each { c -> subList.add(c) }
      newGroup = false
    } else {
      def rm = []
      subList.each { c -> if (!it.contains(c)) rm.add(c) }
      subList.removeAll(rm)
    }
  }
}
list.add(subList)

list.each { it -> println it }
def count = 0
list.each { it -> count += it.size() }
println "Count ${count}"
