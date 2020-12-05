def list = new File('day05.txt').collect { it -> 
  Integer.parseInt(it.replace(['B':'1', 'R': '1', 'F': '0', 'L': '0']), 2)
}

println list.max()
