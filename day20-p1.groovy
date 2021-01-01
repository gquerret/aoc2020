def tiles = [:]

def currTileNum
def currTileText = []
new File('day20.txt').each { it ->
  if (it.startsWith("Tile ")) {
    currTileNum = it.substring(5, it.length() - 1).toInteger()
    currTileText = []
  } else if (it.trim() == "") {
    def topBorder = currTileText[0]
    def bottomBorder = currTileText[9]
    def leftBorder = "", rightBorder = ""
    for (int zz = 0; zz < 10; zz++) {
        leftBorder += currTileText[zz][0]
        rightBorder += currTileText[zz][9]
    }
    tiles.put(currTileNum, [topBorder, rightBorder, bottomBorder, leftBorder])
  } else {
    currTileText.add(it)
  }
}

// tiles.each { k, v -> println "Tile $k: $v"}
println "Size ${tiles.keySet().size()}"

def uniqBorders = [:]
tiles.each { k, v ->
  def uniq = 0
  v.each { it ->
    def occ = 0
    tiles.each { k2, v2 ->
      v2.each { it2 -> 
        if ((it2 == it) || (it2 == it.reverse()))
            occ++
      }
    }
    if (occ == 1) uniq++
  }
  uniqBorders.put(k, uniq)
  println "Tile $k has $uniq unique borders"
}

def rslt = 1L
uniqBorders.each { k, v ->
    if (v == 2) {
        rslt *= k
    }
}
println "Result $rslt"
