package einsteinriddle.solver

case class DirectedPuzzle(items: Seq[Item], nextTo: Item, direction: Direction.Value) extends Puzzle {

  override def numberOfCombinations(solution: Solution): Int = indexes(solution).size

  override def applySolutionWithNumber(solution: Solution, number: Int): Option[Solution] = {
    val solutions = indexes(solution)
    val (compoundPosition, nextToPosition) = solutions(number)
    val solutionWithItems = items.foldLeft(Option(solution)) {
      case (solution, item) => solution.get.applyItem(item, compoundPosition)
    }
    solutionWithItems.flatMap(_.applyItem(nextTo, nextToPosition))
  }

  private def indexes(solution: Solution): Seq[(Int, Int)] = (0 until solution.length).flatMap { position =>
    val compoundFit = items.forall(item => solution.canPlace(item, position))
    if (compoundFit) {
      direction match {
        case Direction.Left =>
          if (position > 0 && solution.canPlace(nextTo, position - 1)) {
            Some(position, position - 1)
          } else {
            None
          }
        case Direction.Right =>
          if (position + 1 < solution.length && solution.canPlace(nextTo, position + 1)) {
            Some(position, position + 1)
          } else {
            None
          }
      }
    } else {
      None
    }
  }
}
