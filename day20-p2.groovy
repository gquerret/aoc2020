NUM_LINES = 12

def tiles = []

def currTileNum
def currTileText = []
new File('day20.txt').each { it ->
  if (it.startsWith("Tile ")) {
    currTileNum = it.substring(5, it.length() - 1).toInteger()
    currTileText = []
  } else if (it.trim() == "") {
    tiles.add(new Tile(currTileNum, currTileText))
  } else {
    currTileText.add(it)
  }
}
def tilesMap= [:]
tiles.each { tile -> tilesMap.put(tile.num, tile) }

// Each border has a unique neighbor ; store neighbor's tile num for each each tile border
tiles.each { tile ->
  def uniq = 0
  tile.getBorders().eachWithIndex { border, index ->
    // println "Tile ${tile.num} - Border $border"
    def occ = 0
    tiles.findAll({ tile2 -> (tile != tile2) }).each { tile2 ->
      tile2.getBorders().each { it2 -> 
        if ((it2 == border) || (it2 == border.reverse())) {
          // println "Found $it2 on ${tile2.num}"
          tile.neighbors[index] = tile2.num
        }
      }
    }
  }
  tile.neighbors.each { it ->
    if (it != 0)
      tile.uniq++
  }
}

// tiles.each { println it }
// println "--------------------"

// First corner tile set to bottom / left
Tile[][] maze = new Tile[NUM_LINES][NUM_LINES]
maze[0][0] = tiles.findAll( { it -> (it.uniq == 2) } ).first()
// Rotate first tile until we have it in the right position
while ((maze[0][0].neighbors[2] != 0) || (maze[0][0].neighbors[3] != 0)) {
  maze[0][0] = maze[0][0].rotate()
}
// println "[0][0] -> ${maze[0][0]}"

// Then solve remaining
for (int index = 0; index < NUM_LINES; index++) {
  if (index > 0) {
    Tile bottomTile = maze[index][index - 1]
    maze[index][index] = bottomTile.getNeighbor(0, tilesMap.get(bottomTile.neighbors[0]))
    // println "[${index}][${index}] -> ${maze[index][index]}"
  }

  for (int zz = index + 1; zz < NUM_LINES; zz++) {
    // Horizontal neighbor
    Tile leftTile = maze[zz - 1][index]
    maze[zz][index] = leftTile.getNeighbor(1, tilesMap.get(leftTile.neighbors[1]))

    // Vertical neighbor
    Tile bottomTile = maze[index][zz - 1]
    maze[index][zz] = bottomTile.getNeighbor(0, tilesMap.get(bottomTile.neighbors[0]))

    // println "[${zz}][${index}] -> ${maze[zz][index]}"
    // println "[${index}][${zz}] -> ${maze[index][zz]}"
  }
}

def newTile = []
for (int yy = 0; yy < NUM_LINES; yy++) {
  for (int innerY = 8; innerY >= 1; innerY--) {
    def line = ""
    for (int xx = 0; xx < NUM_LINES; xx++) {
      line += maze[xx][yy].tile[innerY].substring(1, 9)
    }
    newTile.add(line)
  }
}

Tile water = new Tile(0, newTile)
water.eraseSeaMonster()
water = water.rotate()
water.eraseSeaMonster()
water = water.rotate()
water.eraseSeaMonster()
water = water.rotate()
water.eraseSeaMonster()

water = water.rotate()
water = water.flipY()
water.eraseSeaMonster()
water = water.rotate()
water.eraseSeaMonster()
water = water.rotate()
water.eraseSeaMonster()
water = water.rotate()
water.eraseSeaMonster()

println water
println "Roughness ${water.getWaterRoughness()}"

class Tile {
  int num
  String[] tile
  int[] neighbors = [ 0, 0, 0, 0 ]
  int uniq

  public Tile(int num, List<String> str) {
    this.num = num
    this.tile = str
  }

  String getTop() {
    return tile[0]
  }
  String getBottom() {
    return tile[tile.length - 1]
  }
  String getLeft() {
    return tile.collect { it[0] }.join('')
  }
  String getRight() {
    return tile.collect { it[tile.length - 1] }.join('')
  }
  String[] getBorders() {
    return [ getTop(), getRight(), getBottom(), getLeft() ]
  }

  public long getWaterRoughness() {
    def rslt = 0L
    tile.each { rslt += it.count('#') }
    return rslt
  }

  public Tile rotate() {
    def tmp = []
    for (int xx = 0; xx <= tile.length - 1; xx++) {
      def str = ""
      for (int zz = tile.length - 1; zz >= 0; zz--) {
        str += tile[zz][xx]
      }
      tmp.add(str)
    }
    Tile t = new Tile(this.num, tmp)
    t.neighbors = [ this.neighbors[3], this.neighbors[0], this.neighbors[1], this.neighbors[2] ]
    return t
  }

  public Tile flipY() {
    def tmp = []
    for (int zz = 0; zz <= tile.length - 1; zz++) {
      tmp[zz] = tile[zz].reverse()
    }
    Tile t = new Tile(this.num, tmp)
    t.neighbors = [ this.neighbors[0], this.neighbors[3], this.neighbors[2], this.neighbors[1] ]
    return t
  }

  public String toString() {
    return num + " -- " + neighbors + "\n" + tile.join("\n")
  }

  public Tile getNeighbor(int border, Tile other) {
    // println "Testing $num with ${other.num}"
    String str = getBorders()[border]
    int oppositeBorder = (border + 2) % 4
    Tile tmp = other
    // println "Testing $str with ${tmp.getBorders()[oppositeBorder]}"
    if (str == tmp.getBorders()[oppositeBorder])
      return tmp
    tmp = tmp.rotate()
    // println "Testing $str with ${tmp.getBorders()[oppositeBorder]}"
    if (str == tmp.getBorders()[oppositeBorder])
      return tmp
    tmp = tmp.rotate()
    // println "Testing $str with ${tmp.getBorders()[oppositeBorder]}"
    if (str == tmp.getBorders()[oppositeBorder])
      return tmp
    tmp = tmp.rotate()
    // println "Testing $str with ${tmp.getBorders()[oppositeBorder]}"
    if (str == tmp.getBorders()[oppositeBorder])
      return tmp
    tmp = other.flipY()
    // println "Testing $str with ${tmp.getBorders()[oppositeBorder]}"
    if (str == tmp.getBorders()[oppositeBorder])
      return tmp
    tmp = tmp.rotate()
    // println "Testing $str with ${tmp.getBorders()[oppositeBorder]}"
    if (str == tmp.getBorders()[oppositeBorder])
      return tmp
    tmp = tmp.rotate()
    // println "Testing $str with ${tmp.getBorders()[oppositeBorder]}"
    if (str == tmp.getBorders()[oppositeBorder])
      return tmp
    tmp = tmp.rotate()
    // println "Testing $str with ${tmp.getBorders()[oppositeBorder]}"
    if (str == tmp.getBorders()[oppositeBorder])
      return tmp
    tmp = tmp.rotate()
    // println "Testing $str with ${tmp.getBorders()[oppositeBorder]}"
    if (str == tmp.getBorders()[oppositeBorder])
      return tmp
  }

  public void eraseSeaMonster() {
    // Sea monster is 20 x 3 character
    for (int yy = 0; yy <= tile.length - 3; yy++) {
      for (int xx = 0; xx <= tile.length - 20; xx++) {
        if ((tile[yy][xx + 18] != '.') && (tile[yy + 1][xx] != '.') && (tile[yy + 1][xx + 5] != '.') && (tile[yy + 1][xx + 6] != '.') && (tile[yy + 1][xx + 11] != '.') && (tile[yy + 1][xx + 12] != '.') && (tile[yy + 1][xx + 17] != '.') && (tile[yy + 1][xx + 18] != '.') && (tile[yy + 1][xx + 19] != '.') && (tile[yy + 2][xx + 1] != '.') && (tile[yy + 2][xx + 4] != '.') && (tile[yy + 2][xx + 7] != '.') && (tile[yy + 2][xx + 10] != '.') && (tile[yy + 2][xx + 13] != '.') && (tile[yy + 2][xx + 16] != '.')) {
          // println "Sea monster found at X${xx}-- Y${yy} -- Erasing"
          tile[yy] = tile[yy].substring(0, xx + 18) + 'O' + tile[yy].substring(xx + 19)
          tile[yy + 1] = (xx == 0 ? "" : tile[yy + 1].substring(0, xx)) + 'O' + tile[yy + 1].substring(xx + 1, xx + 5) + "OO" + tile[yy + 1].substring(xx + 7, xx + 11) + "OO" + tile[yy + 1].substring(xx + 13, xx + 17) + "OOO" + ((xx + 20) > tile.length - 1 ? "" : tile[yy + 1].substring(xx + 20))
          tile[yy + 2] = tile[yy + 2].substring(0, xx + 1) + 'O' + tile[yy + 2].substring(xx + 2, xx + 4) + 'O' + tile[yy + 2].substring(xx + 5, xx + 7) + 'O' + tile[yy + 2].substring(xx + 8, xx + 10) + 'O' + tile[yy + 2].substring(xx + 11, xx + 13) + 'O' + tile[yy + 2].substring(xx + 14, xx + 16)  + 'O' + tile[yy + 2].substring(xx + 17)
        }
      }
    }
  }
}
