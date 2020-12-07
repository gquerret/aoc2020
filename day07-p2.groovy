def colorMap = [:]
new File('day07.txt').each { line ->
  def lst = line.split(' ')
  Color c1 = new Color("${lst[0]} ${lst[1]}")
  colorMap.put(c1.name, c1)

  if (lst[4] != "no") {
    for (int zz = 4; zz < lst.size(); zz += 4) {
      Color cTmp = new Color("${lst[zz + 1]} ${lst[zz + 2]}")
      c1.tmp.add([color: cTmp.name, num: lst[zz].toInteger()])
    }
  }
}

colorMap.values().each { color ->
  color.tmp.each { tmp ->
    color.contains.add([color: colorMap.get(tmp.color), num: tmp.num])
    colorMap.get(tmp.color).parents.add(color)
  }
  color.tmp.clear()
}

println colorMap.get('shiny gold').numBags() - 1

class Color {
  String name
  List tmp = []
  List contains = []
  Set parents = []

  def Color(String name) { this.name = name }

  def Set allParentPaths() {
    def rslt = [] as Set
    parents.each { it ->
      rslt.add(it)
      rslt.addAll(it.allParentPaths())
    }
    return rslt
  }

  def int numBags() {
    def rslt = 1
    contains.each { it ->
      rslt += it.num * it.color.numBags()
    }
    return rslt
  }

  def String toString() {
    return name
  }
}
