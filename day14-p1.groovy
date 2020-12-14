def map = [:]
def maskOr = 0L
def maskAnd = 0L

new File('day14.txt').each { line -> 
  if (line.startsWith('mask = ')) {
    def tmp = line.substring(7)
    maskOr = Long.parseLong(tmp.replace('X', '0'), 2)
    maskAnd = Long.parseLong(tmp.replace('X', '1'), 2)
  } else {
    def pos = line.substring(line.indexOf('[') + 1, line.indexOf(']')).toInteger()
    def mem = map.get(pos)
    if (mem == null) {
      mem = 0L
    }
    mem = line.substring(line.indexOf('=') + 1).toLong() | maskOr
    mem = mem & maskAnd
    println "Put ${mem} at position ${pos}"
    map.put(pos, mem)
  }
}
println "Sum ${map.values().sum()}"
