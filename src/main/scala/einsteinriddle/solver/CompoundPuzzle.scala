package einsteinriddle.solver

case class CompoundPuzzle(items: Seq[Item]) extends Puzzle {

  override def numberOfCombinations(solution: Solution): Int = {
    (0 until solution.length).count(position => items.forall(item => solution.canPlace(item, position)))
  }

  override def applySolutionWithNumber(solution: Solution, number: Int): Option[Solution] = {
    val positions = (0 until solution.length).filter { index =>
      items.forall(item => solution.canPlace(item, index))
    }
    val position = positions(number)
    items.foldLeft(Option(solution)) {
      case (solution, item) => solution.get.applyItem(item, position)
    }
  }
}
