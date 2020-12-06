def list = []
def subList = [ ] as Set

new File('day06.txt').collect { it -> 
  if (it.isEmpty()) {
    list.add(subList)
    subList = [ ] as Set
  } else {
    it.each { c -> subList.add(c) }
  }
}
list.add(subList)

def count = 0
list.each { it -> count += it.size() }
println "Count ${count}"
