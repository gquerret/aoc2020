def pos = [ 0, 0 ]
def dir = 0

new File('day12.txt').each { it ->
  println "Pos [${pos[0]} ; ${pos[1]}] facing ${dir}"
  def letter = it.substring(0, 1)
  def number = it.substring(1).toInteger()

  if (letter == 'E') {
    pos[0] += number
  } else if (letter == 'W') {
    pos[0] -= number
  } else if (letter == 'N') {
    pos[1] += number
  } else if (letter == 'S') {
    pos[1] -= number
  } else if (letter == 'L') {
    dir += number
    dir %= 360
  } else if (letter == 'R') {
    dir += (360 - number)
    dir %= 360
  } else if (letter == 'F') {
    if (dir == 0) { pos[0] += number}
    else if (dir == 180) { pos[0] -= number}
    else if (dir == 90) { pos[1] += number}
    else if (dir == 270) { pos[1] -= number}
  }
}

println "End pos [${pos[0]} ; ${pos[1]}] facing ${dir}"
println "Manhattan distance: ${Math.abs(pos[0]) + Math.abs(pos[1])}"
