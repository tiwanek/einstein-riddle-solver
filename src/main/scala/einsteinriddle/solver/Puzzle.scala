package einsteinriddle.solver

trait Puzzle {
  def numberOfCombinations(solution: Solution): Int
  def applySolutionWithNumber(solution: Solution, number: Int): Option[Solution]
}
