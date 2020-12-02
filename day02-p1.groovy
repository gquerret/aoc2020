def list = new File('day02.txt').collect { it -> 
  def split = it.split(' ')
  return [ password: split[2], letter: split[1].substring(0, 1), min: Integer.parseInt(split[0].split('-')[0]), max: Integer.parseInt(split[0].split('-')[1]) ]
}

def count = 0
list.each { it ->
  def occ = it.password.count(it.letter)
  if ((occ >= it.min) && (occ <= it.max))
    count++;
}
println count
