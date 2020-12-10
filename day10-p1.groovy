def list = new File('day10.txt').collect { it -> it.toInteger() }
list.sort()

def numOne = 0
def numThree = 1
def last = 0
list.each { it ->
  if ((it - last) == 1)
    numOne++
  else if ((it - last) == 3)
    numThree++
  else
    println "Bug..."
  last = it
}

println "1-jolt ${numOne} - 3-jolt ${numThree}"
println "Result: ${numOne * numThree}"
