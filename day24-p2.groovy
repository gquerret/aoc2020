moves = ['nw': [0, 1, -1], 'ne': [1, 0, -1], 'e': [1, -1, 0], 'se': [0, -1, 1], 'sw': [-1, 0, 1], 'w': [-1, 1, 0]]

def pos = []
new File('day24.txt').each { it ->
  def tmp = readLine(it)
  pos.add(tmp)
}

def blackTiles = [] as Set
pos.each { it ->
  if (!blackTiles.contains(it)) {
    blackTiles.add(it)
  } else {
    blackTiles.remove(it)
  }
}

for (int zz = 0; zz < 101; zz++) {
  println "Day ${zz} - NumBlacks: ${blackTiles.size()}"
  blackTiles = dayFlip(blackTiles)
}

def readLine(String str) {
  def mv = []
  def zz = 0
  while (zz < str.length()) {
    if ((str[zz] == 'e') || (str[zz] == 'w'))
      mv.add(str[zz])
    else 
      mv.add(str.substring(zz, zz++ + 2))
    zz++
  }

  def currPos = [0, 0, 0]
  mv.each { it ->
    def tmp = moves.get(it)
    currPos[0] += tmp[0]
    currPos[1] += tmp[1]
    currPos[2] += tmp[2]
  }
  return currPos
}

def dayFlip(Set blackTiles) {
  def rslt = [] as Set
  blackTiles.each { key ->
    def numBlacks = numBlackTiles(blackTiles, key)
    if ((numBlacks == 1) || (numBlacks == 2)) {
      rslt.add(key)
    }

    for (int zz = 0; zz < 6; zz++) {
      def posX = [ key[0] + moves.values()[zz][0], key[1] + moves.values()[zz][1], key[2] + moves.values()[zz][2]]
      if (!blackTiles.contains(posX) && (numBlackTiles(blackTiles, posX) == 2)) {
        rslt.add(posX)
      }
    }
  }

  return rslt
}

def numBlackTiles(blackTiles, pos) {
  def rslt = 0

  moves.each { k, v ->
    rslt = rslt + (blackTiles.contains([ v[0] + pos[0], v[1] + pos[1], v[2] + pos[2]]) ? 1 : 0)
  }
  return rslt
}
