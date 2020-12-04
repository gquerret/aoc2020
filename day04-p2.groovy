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

def validPassport(passport) {
  try {
    println passport

    if ((passport.byr == null) || (passport.iyr == null) || (passport.eyr == null) || (passport.hgt == null) || (passport.hcl == null) || (passport.ecl == null) || (passport.pid == null))
      return false
    if ((passport.byr.toInteger() < 1920) || (passport.byr.toInteger() > 2002))
      return false
    if ((passport.iyr.toInteger() < 2010) || (passport.iyr.toInteger() > 2020))
      return false
    if ((passport.eyr.toInteger() < 2020) || (passport.eyr.toInteger() > 2030))
      return false

    // height
    def matcher = passport.hgt =~ /(\d+)(\w+)/
    if (matcher[0][2] == 'cm') {
      if ((matcher[0][1].toInteger() < 150) || (matcher[0][1].toInteger() > 193))
        return false
    } else if (matcher[0][2] == 'in') {
      if ((matcher[0][1].toInteger() < 59) || (matcher[0][1].toInteger() > 76))
        return false
    } else
      return false

    def pattern = ~/#[0-9a-z]{6}/
    if (!pattern.matcher(passport.hcl).matches())
      return false

    if (!['amb', 'blu', 'brn', 'gry', 'grn', 'hzl', 'oth'].contains(passport.ecl))
      return false

    def pattern2 = ~/\d{9}/
    if (!pattern2.matcher(passport.pid).matches())
      return false

  } catch (NumberFormatException caught) {
    return false
  }

  return true
}

println list2.count { it -> validPassport(it) }
