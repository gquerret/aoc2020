new File('Day19.g4').withWriter('UTF-8') { writer ->
  writer.writeLine "grammar Day19;"
  new File('day19-grammar.txt').each { it ->
    String lpart = it.split(':')[0]
    String rpart = it.split(':')[1]
    writer.write "rule${lpart}:"
    if (rpart.indexOf('"') != -1) {
      writer.writeLine "${rpart.replace('"', '\'')};"
    } else {
      rpart.split(' ').each { it2 ->
        if ((it2 == '') || (it2 == ' '))
          writer.write ""
        else if (it2 == '|')
          writer.write " ${it2}"
        else
          writer.write " rule${it2}"
      }
      if (lpart == '0')
        writer.write " EOF"
      // Part 2: uncomment those lines
      if (lpart == '8')
        writer.write " | rule42 rule8"
      if (lpart == '11')
        writer.write " | rule42 rule11 rule31"
      writer.writeLine ";"
    }
  }
}
