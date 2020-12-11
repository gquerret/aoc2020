def width = -1
def height = 2
def rslt = ""
new File('day11.txt').each { it ->
  if (width == -1) {
    width = it.length() + 2
    rslt = "."*width
  }
  rslt += "." + it + "."
  height++
}
rslt += "."*width

long timer = System.currentTimeMillis()
def prevOcc = -1
def numOcc = rslt.count('#')
while (prevOcc != numOcc) {
  // display(rslt, width, height)
  println "Occupied seats - Before ${prevOcc}"
  def newRslt = shuffle(rslt, width, height)
  // display(newRslt, width, height)
  prevOcc = numOcc
  numOcc = newRslt.count('#')
  println "Occupied seats - After ${numOcc}"
  rslt = newRslt
}
println "End: ${numOcc} -- ${System.currentTimeMillis() - timer} ms"

def int occupiedSeats(list, pos, width) {
  def occ = (list.charAt(pos - width - 1) == '#') ? 1 : 0
  occ += list.charAt(pos - width ) == '#' ? 1 : 0
  occ += list.charAt(pos - width + 1) == '#' ? 1 : 0
  occ += list.charAt(pos - 1) == '#' ? 1 : 0
  occ += list.charAt(pos + 1) == '#' ? 1 : 0
  occ += list.charAt(pos + width -1) == '#' ? 1 : 0
  occ += list.charAt(pos + width) == '#' ? 1 : 0
  occ += list.charAt(pos + width + 1) == '#' ? 1 : 0

  return occ
}

def String shuffle(lst, width, height) {
  def rslt = ""
  for (int zz = 0; zz < lst.length(); zz++) {
    def c = lst.charAt(zz)
    if (c == '.') {
      rslt += "."
    } else if (c == 'L') {
      if (occupiedSeats(lst, zz, width) == 0) {
        rslt += "#"
      } else {
        rslt += "L"
      }
    } else if (c == '#') {
      if (occupiedSeats(lst, zz, width) >= 4) {
        rslt += "L"
      } else {
        rslt += "#"
      }
    }
  }
  return rslt
}

def void display(lst, width, height) {
  for (int zz = 0; zz < height; zz++) {
    println lst.substring(width * zz, width * (zz + 1))
  }
}