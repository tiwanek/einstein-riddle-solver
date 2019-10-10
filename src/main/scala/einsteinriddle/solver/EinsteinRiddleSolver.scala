package einsteinriddle.solver

class EinsteinRiddleSolver(length: Int, types: Int, inputPuzzles: Seq[Puzzle]) {

  def solve(): Option[Solution] = {
    val solution = Solution.empty(length, types)
    applyPuzzles(solution, inputPuzzles)
  }

  private def applyPuzzles(solution: Solution, puzzles: Seq[Puzzle]): Option[Solution] = {
    if (puzzles.isEmpty) {
      Some(solution)
    } else {
      val sortedPuzzlesWithCombinations = puzzles
        .map(puzzle => (puzzle.numberOfCombinations(solution), puzzle))
        .sortBy(_._1)
      val (combinations, puzzle) = sortedPuzzlesWithCombinations.head
      applySinglePuzzle(solution, puzzle, 0, combinations, sortedPuzzlesWithCombinations.map(_._2).tail)
    }
  }

  private def applySinglePuzzle(
    solution: Solution,
    puzzle: Puzzle,
    index: Int,
    combinations: Int,
    restOfPuzzles: Seq[Puzzle]
  ): Option[Solution] = {
    if (index >= combinations) {
      println(s"No more solutions for ${puzzle}")
      None
    } else {
      println(solution)
      println(s"Applying ${index + 1} out of ${combinations} for ${puzzle}")
      val applied = puzzle.applySolutionWithNumber(solution, index)
      assert(applied.isDefined)
      val newSolution = applied.get
      val nextPuzzleSolution = applyPuzzles(newSolution, restOfPuzzles)
      nextPuzzleSolution match {
        case None => applySinglePuzzle(solution, puzzle, index + 1, combinations, restOfPuzzles)
        case Some(result) => Some(result)
      }
    }
  }
}
