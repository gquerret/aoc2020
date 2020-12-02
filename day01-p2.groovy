def list = new File('day01.txt').collect { it -> Integer.parseInt(it) }

long start = System.nanoTime()
list.each { it ->
  list.each { it2 ->
    list.each { it3 ->
      if ((it + it2 + it3) == 2020) {
        println "Result: ${it} * ${it2} * ${it3} = ${it * it2 * it3}"
        println "Time: ${(System.nanoTime() - start) / 1000000} ms"
        System.exit(0)
      }
    }
  }
}
