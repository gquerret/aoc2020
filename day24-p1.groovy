def pos = []
new File('day24.txt').each { it ->
  def tmp = readLine(it)
  pos.add(tmp)
  println "CurrPos ${tmp}"
}

def colors = [:]
pos.each { it ->
  def key = "${it[0]};${it[1]};${it[2]}"
  def tmp = colors.get(key)
  if ((tmp == null) || (tmp == 'W')) {
    // Turns to black
    colors.put(key, 'B') 
  } else if (tmp == 'B') {
    colors.put(key, 'W') 
  }
  println "Turn ${key} to ${colors.get(key)}"
}

def numBlacks = 0
colors.each { k, v ->
  println "Key ${k}: ${v}"
  if (v == 'B') numBlacks++
}
println "NumBlacks: ${numBlacks}"


def readLine(String str) {
  def moves = ['nw': [0, 1, -1], 'ne': [1, 0, -1], 'e': [1, -1, 0], 'se': [0, -1, 1], 'sw': [-1, 0, 1], 'w': [-1, 1, 0]]
  def mv = []
  def zz = 0
  while (zz < str.length()) {
    if ((str[zz] == 'e') || (str[zz] == 'w'))
      mv.add(str[zz])
    else 
      mv.add(str.substring(zz, zz++ + 2))
    zz++
  }
  println mv
  def currPos = [0, 0, 0]
  mv.each { it ->
    def tmp = moves.get(it)
    currPos[0] += tmp[0]
    currPos[1] += tmp[1]
    currPos[2] += tmp[2]
  }
  return currPos
}