def map = [:]
def mask = ""

new File('day14.txt').each { line -> 
  if (line.startsWith('mask = ')) {
    mask = line.substring(line.indexOf('=') + 2)
    println "New mask ${mask}"
    println ""
  } else {
    def pos = line.substring(line.indexOf('[') + 1, line.indexOf(']')).toInteger()
    def binPos = Long.toBinaryString(pos)
    binPos = '0'*(36 - binPos.length()) + binPos
    def value = line.substring(line.indexOf('=') + 1).toLong()
    println "Position ${binPos} -- ${pos} -- ${value}"
    def masked = ""
    def cnt = 0
    for (int zz = 0; zz < mask.length(); zz++) {
      if (mask.charAt(zz) == '0')
        masked += binPos.charAt(zz)
      else if (mask.charAt(zz) == '1')
        masked += "1"
      else {
        masked += (char) (65 + cnt++)
      }
    }
    println "Masked   ${masked}"

    getPermutations(0, cnt - 1).each { it ->
      def yyy = Long.parseLong(masked.replace(it), 2)
      map.put(yyy, value)
    }
  }
}
println "Sum ${map.values().sum()}"

def List getPermutations(int min, int max) {
  def letter = new String((char) (65 + min))
  if (min == max) {
    return [ [ "${letter}": "0" ], [ "${letter}": "1" ] ]
  } else {
    def list = []
    getPermutations(min + 1, max).each { it ->
      it.put("${letter}", "1")
      list.add(it)
      def tmp = it.clone()
      tmp.put("${letter}", "0")
      list.add(tmp)
    }
    return list
  }

}