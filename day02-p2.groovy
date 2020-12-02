def list = new File('day02.txt').collect { it -> 
  def split = it.split(' ')
  return [ password: split[2], letter: split[1].substring(0, 1), pos1: Integer.parseInt(split[0].split('-')[0]), pos2: Integer.parseInt(split[0].split('-')[1]) ]
}

def count = 0
list.each { it ->
  def chr1OK = it.password.charAt(it.pos1 - 1) == it.letter
  def chr2OK = it.password.charAt(it.pos2 - 1) == it.letter

  if (chr1OK ^ chr2OK)
    count++
}
println count
