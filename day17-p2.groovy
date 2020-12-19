String tmp = ""
int width = -1
new File('day17.txt').each { it ->
  if (width == -1)
    width = it.length() + 4
  tmp += ".." + it + ".."
}
tmp = "." * (width * 2) + tmp + "." * (width * 2)
tmp = "." * (width * width * 2) + tmp + "." * (width * width * 2)
tmp = "." * (width * width * 5 * 2) + tmp + "." * (width * width * 5 * 2)
Hypercube hcube = new Hypercube(tmp, width, width, 5, 5)
hcube.display()

for (int zz = 1; zz <= 6; zz++) {
  println "Pass #${zz} - Apply"
  hcube = hcube.apply()
  hcube.display()
  println "Pass #${zz} - Active ${hcube.numActive()}"
}

class Hypercube {
  String map
  int xm, ym, zm, wm

  // Initial cube, one level
  public Hypercube(String str, int xm, int ym, int zm, int wm) {
    println "New Hypercube ${xm}*${ym}*${zm}*${wm} : ${str.size()}"
    this.xm = xm
    this.ym = ym
    this.zm = zm
    this.wm = wm
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
    rslt += map.charAt(pos) == '#' ? 1 : 0
    rslt += map.charAt(pos + 1) == '#' ? 1 : 0
    rslt += map.charAt(pos + xm - 1) == '#' ? 1 : 0
    rslt += map.charAt(pos + xm) == '#' ? 1 : 0
    rslt += map.charAt(pos + xm + 1) == '#' ? 1 : 0

    return rslt
  }

  int activeNeighborsCube(int pos) {
    return activeNeighborsLevel(pos) + activeNeighborsLevel(pos + (xm * ym)) + activeNeighborsLevel(pos - (xm * ym))
  }

  int activeNeighbors(int pos) {
    def cubeSz = xm * ym * zm
    return activeNeighborsCube(pos) + activeNeighborsCube(pos + cubeSz) + activeNeighborsCube(pos - cubeSz) - ( map.charAt(pos) == '#' ? 1 : 0 )
  }

  Hypercube apply() {
    def newXm = xm + 2 // newYm is identical
    def newZm = zm + 2

    // 2 empty cubes
    def tmp = "." * (newXm * newXm * newZm * 2)
    for (int w1 = 1; w1 < wm - 1; w1++) {
      // 2 empty levels
      tmp += "." * (newXm * newXm * 2)
      for (int z1 = 1; z1 < zm - 1; z1++) {
        // 2 empty lines
        tmp += "." * (newXm * 2)
        for (int x1 = 1; x1 < xm - 1; x1++) {
          // 2 empty columns
          tmp += ".."
          for (int y1 = 1; y1 < ym - 1; y1++) {
            def currPos = w1 * (xm * ym * zm) + z1 * (ym * xm) + x1 * xm + y1
            def numActiveNeighbors = activeNeighbors(currPos)
            // println "Pos ${currPos} : ${numActiveNeighbors}"
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
    }
    // 2 empty cubes
    tmp += "." * (newXm * newXm * newZm * 2)

    return new Hypercube(tmp, xm + 2, ym + 2, zm + 2, wm + 2)
  }

  void display() {
    def delta = (wm - 1) / 2
    for (int w1 = 2; w1 < wm - 2; w1++) {
      for (int z1 = 2; z1 < zm - 2; z1++) {
        println "W${w1 - delta} Z${z1 - delta}"
        for (int x1 = 2; x1 < xm - 2; x1++) {
          println map.substring(w1 * (xm * ym * zm) + z1 * (xm * ym) + x1 * xm + 2, w1 * (xm * ym * zm) + z1 * (xm * ym) + (x1 + 1) * xm - 2)
        }
      }
    }
  }
}
