def PREAMBLE_SZ = 25
def list = new File('day09.txt').collect { it -> it.toLong() }

for (int zz = PREAMBLE_SZ; zz < list.size(); zz++) {
  def init = zz - PREAMBLE_SZ
  def found = false
  while (!found) {
    for (int xx = init + 1; !found && xx < zz; xx++) {
      // println "Combined ${init} with ${xx}: ${list.get(init)} + ${list.get(xx)}"
      if (list.get(init) + list.get(xx) == list.get(zz)) {
        found = true
      }
    }
    init++;
    if ((init == zz) && !found) {
      println "Invalid index: ${zz} --> ${list.get(zz)}"
      System.exit(0)
    }
  }
}
