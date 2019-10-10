package einsteinriddle.solver

case class PlacedPuzzle(item: Item, position: Int) extends Puzzle {

  override def numberOfCombinations(solution: Solution): Int =
    if (solution.canPlace(item, position)) 1 else 0

  override def applySolutionWithNumber(solution: Solution, number: Int): Option[Solution] = {
    solution.applyItem(item, position)
  }
}
