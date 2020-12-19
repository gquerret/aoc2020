String tmp = ""
int width = -1
new File('day17.txt').each { it ->
  if (width == -1)
    width = it.length() + 4
  tmp += ".." + it + ".."
}
tmp = "." * (width * 2) + tmp + "." * (width * 2)
tmp = "." * (width * width * 2) + tmp + "." * (width * width * 2)
Cube cube = new Cube(tmp, width, width, 5)

for (int zz = 1; zz <= 6; zz++) {
  println "Pass #${zz} - Apply"
  cube = cube.apply()
  println "Pass #${zz} - Active ${cube.numActive()}"
}

class Cube {
  String map
  int xm, ym, zm

  // Initial cube, one level
  public Cube(String str, int xm, int ym, int zm) {
    println "New Cube ${xm}*${ym}*${zm} : ${str.size()}"
    this.xm = xm
    this.ym = ym
    this.zm = zm
    this.map = str
  }

  int numActive() {
    return map.count('#')
  }

  int activeNeighborsLevel(int pos) {
    def rslt = map.charAt(pos - xm - 1) == '#' ? 1 : 0
    rslt += map.charAt(pos - xm) == '#' ? 1 : 0
    rslt += map.charAt(pos - xm + 1) == '#' ? 1 : 0
    rslt += map.charAt(pos - 1) == '#' ? 1 : 0
    rslt += map.charAt(pos + 1) == '#' ? 1 : 0
    rslt += map.charAt(pos + xm - 1) == '#' ? 1 : 0
    rslt += map.charAt(pos + xm) == '#' ? 1 : 0
    rslt += map.charAt(pos + xm + 1) == '#' ? 1 : 0

    return rslt
  }

  int activeNeighbors(int pos) {
    return activeNeighborsLevel(pos) + activeNeighborsLevel(pos + (xm * ym)) + activeNeighborsLevel(pos - (xm * ym)) + ( map.charAt(pos + (xm * ym)) == '#' ? 1 : 0 ) + ( map.charAt(pos - (xm * ym)) == '#' ? 1 : 0 )
  }

  Cube apply() {
    def newXm = xm + 2 // newYm is identical
    // 2 empty levels
    def tmp = "." * (newXm * newXm * 2)
    for (int z1 = 1; z1 < zm - 1; z1++) {
      // 2 empty lines
      tmp += "." * (newXm * 2)
      for (int x1 = 1; x1 < xm - 1; x1++) {
        // 2 empty columns
        tmp += ".."
        for (int y1 = 1; y1 < ym - 1; y1++) {
          def currPos = z1 * (ym * xm) + y1 * xm + x1
          def numActiveNeighbors = activeNeighbors(currPos)
          if (map.charAt(currPos) == '.') {
            tmp += (numActiveNeighbors == 3 ? '#' : '.')
          } else {
            tmp += ((numActiveNeighbors == 2) || (numActiveNeighbors == 3)) ? '#' : '.'
          }
        }
        // 2 empty columns
        tmp += ".."
      }
      // 2 empty lines
      tmp += "." * (newXm * 2)
    }
    // 2 empty levels
    tmp += "." * (newXm * newXm * 2)

    return new Cube(tmp, xm + 2, ym + 2, zm + 2)
  }
}
