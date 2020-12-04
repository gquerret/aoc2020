def list = []
def list2 = []
def lastLine = ""

new File('day04.txt').eachLine { line ->
  if (line.isEmpty()) {
    list.add(lastLine)
    lastLine = ""
  } else {
    lastLine = lastLine + (lastLine.isEmpty() ? "" : " ") + line
  }
}
list.add(lastLine)

list.each { it ->
  def passport = [:]
  it.split(" ").each { entry ->
    passport.put(entry.split(':')[0], entry.split(':')[1])
  }
  list2.add(passport)
}

list2.each { it -> println it }
println list2.count { it -> (it.byr != null) && (it.iyr != null) && (it.eyr != null) && (it.hgt != null) && (it.hcl != null) && (it.ecl != null) && (it.pid != null) }
