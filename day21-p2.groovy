def food = []
def ingredients = [] as Set
def allergens = [] as Set

new File('day21.txt').each { it ->
  int brkt = it.indexOf('(')
  def x1 = []
  def x2 = []
  it.substring(0, brkt - 1).split(' ').each { it2 ->
    ingredients.add(it2.trim())
    x1.add(it2.trim())
  }
  it.substring(brkt + 10, it.length() - 1).split(',').each { it2 ->
    allergens.add(it2.trim())
    x2.add(it2.trim())
  }
  food.add(new Food(x1, x2))
}

println "Ingredients $ingredients"
println "Allergens $allergens"

def allergenMap = [:]
def ingFound = []
allergens.each { it ->
  println "Allergen $it"
  def tmp = ingredients.clone()
  food.each { it2 ->
    if (it2.allergens.contains(it)) {
      tmp = tmp.intersect(it2.ingredients)
    }
  }
  allergenMap.put(it, tmp)
  if (tmp.size() == 1) {
    ingFound.add(tmp[0])
    println "Found ${tmp[0]} for $it"
  }
}
while (ingFound.size() < allergens.size()) {
  println "Again..."
  allergenMap.each { k, v ->
    if (v.size() > 1) {
      v.removeAll(ingFound)
      if (v.size() == 1) {
        println "Found ${v[0]} for $k"
        ingFound.add(v[0])
      }
    }
  }
}

def rslt = ""
allergenMap.keySet().sort().each { it ->
  println "XXX $it"
  rslt += (rslt.length() == 0 ? "" : ",") + allergenMap[it][0]
}
println "Final $rslt"

class Food {
  List ingredients, allergens

  public Food(List ingredients, List allergens) {
    this.ingredients = ingredients
    this.allergens = allergens
  }
}
