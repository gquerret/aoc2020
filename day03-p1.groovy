def list = new File('day03.txt').collect { it }

def slopeWidth = list.get(0).length()
def slopeHeight = list.size()
def treeHit = 0
def noHit = 0

def DOWN = 1
def RIGHT = 3

def currX = 0
for (int k = DOWN; k < slopeHeight; k+= DOWN) {
  currX += RIGHT
  if (currX >= slopeWidth)
    currX -= slopeWidth
  if (list.get(k).charAt(currX) == '#')
    treeHit++
  else
    noHit++
}

println "Tree hits: ${treeHit}"
println "No hits: ${noHit}"
