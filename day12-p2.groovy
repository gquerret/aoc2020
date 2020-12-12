def pos = [ 0, 0 ]
def waypoint = [ 10, 1 ]

new File('day12.txt').each { it ->
  println "Pos [${pos[0]} ; ${pos[1]}] - Waypoint [${waypoint[0]} ; ${waypoint[1]}] - ${it}"
  def letter = it.substring(0, 1)
  def number = it.substring(1).toInteger()

  if (letter == 'E') {
    waypoint[0] += number
  } else if (letter == 'W') {
    waypoint[0] -= number
  } else if (letter == 'N') {
    waypoint[1] += number
  } else if (letter == 'S') {
    waypoint[1] -= number
  } else if ((letter == 'L') || (letter == 'R')) {
    def angle = letter == 'L' ? number : 360 - number
    if (angle == 90) {
      waypoint = [ - waypoint[1], waypoint[0] ]
    } else if (angle == 180) {
      waypoint = [ - waypoint[0], - waypoint[1] ]
    } else if (angle == 270) {
      waypoint = [ waypoint[1], - waypoint[0] ]
    }
  } else if (letter == 'F') {
    pos = [ pos[0] + (number * waypoint[0]), pos[1] + (number * waypoint[1])]
  }
}

println "End pos [${pos[0]} ; ${pos[1]}] - Waypoint [${waypoint[0]} ; ${waypoint[1]}]"
println "Manhattan distance: ${Math.abs(pos[0]) + Math.abs(pos[1])}"
