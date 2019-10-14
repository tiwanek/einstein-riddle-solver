package einsteinriddle.dsl

import einsteinriddle.solver.{EinsteinRiddleSolver, Puzzle, Solution}

class EinsteinRiddleBuilder(length: Int, types: Int) {

  var inputPuzzles: Seq[Puzzle] = Seq.empty

  def addPuzzle(puzzle: Puzzle) = inputPuzzles = inputPuzzles :+ puzzle

  def solve(): Option[Solution] = {
    val solver = new EinsteinRiddleSolver(length, types, inputPuzzles)
    solver.solve()
  }
}
