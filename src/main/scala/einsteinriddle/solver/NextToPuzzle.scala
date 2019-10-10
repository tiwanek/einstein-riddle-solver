package einsteinriddle.solver

case class NextToPuzzle(items: Seq[Item], nextTo: Item) extends Puzzle {

  override def numberOfCombinations(solution: Solution): Int = indexes(solution).length

  override def applySolutionWithNumber(solution: Solution, number: Int): Option[Solution] = {
    val solutions = indexes(solution)
    val (compoundPosition, nextToPosition) = solutions(number)
    val solutionWithItems = items.foldLeft(Option(solution)) {
      case (solution, item) => solution.get.applyItem(item, compoundPosition)
    }
    solutionWithItems.flatMap(_.applyItem(nextTo, nextToPosition))
  }

  private def indexes(solution: Solution): Seq[(Int, Int)] = {
    (0 until solution.length).flatMap { position =>
      val compoundFit = items.forall(item => solution.canPlace(item, position))
      if (compoundFit) {
        val leftEmpty =
          position > 0 && solution.canPlace(nextTo, position - 1)
        val rightEmpty =
          position + 1 < solution.length && solution.canPlace(nextTo, position + 1)
        (leftEmpty, rightEmpty) match {
          case (true, true) => Seq((position, position - 1), (position, position + 1))
          case (true, false) => Seq((position, position - 1))
          case (false, true) => Seq((position, position + 1))
          case (false, false) => Seq.empty
        }
      } else {
        Seq.empty
      }
    }
  }
}
