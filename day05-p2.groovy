def list = new File('day05.txt').collect { it -> 
  Integer.parseInt(it.replace(['B':'1', 'R': '1', 'F': '0', 'L': '0']), 2)
}
list.sort()

for (int zz = 1; zz < list.size() - 2; zz++) {
  if (list.get(zz + 1) != list.get(zz) + 1)
    println "Seat " + (list.get(zz) + 1)
}
