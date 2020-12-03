def list = new File('day03.txt').collect { it }

def slopeWidth = list.get(0).length()
def slopeHeight = list.size()
def treeHit = [ 0, 0, 0, 0, 0 ]
def noHit = [ 0, 0, 0, 0, 0 ]

def DOWN = [ 1, 1, 1, 1, 2 ]
def RIGHT = [ 1, 3, 5, 7, 1 ]

for (int cnt = 0; cnt < 5; cnt++) {
  def currX = 0
  for (int k = DOWN[cnt]; k < slopeHeight; k+= DOWN[cnt]) {
    currX += RIGHT[cnt]
    if (currX >= slopeWidth)
      currX -= slopeWidth
    if (list.get(k).charAt(currX) == '#')
      treeHit[cnt]++
    else
      noHit[cnt]++
  }
  println "Tree hits ${cnt}: ${treeHit[cnt]}"
  println "No hits ${cnt}: ${noHit[cnt]}"

}
def totHit = treeHit[0] * treeHit[1] * treeHit[2] * treeHit[3] * treeHit[4]
println "Tree hits: ${totHit}"
