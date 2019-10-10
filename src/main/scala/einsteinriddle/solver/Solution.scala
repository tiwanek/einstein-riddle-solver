package einsteinriddle.solver

case class Solution(length: Int, types: Int, board: Seq[Seq[Option[Item]]]) {

  def applyItem(item: Item, position: Int): Option[Solution] = if (board(position)(item.`type`).isDefined) {
    if (item.value == board(position)(item.`type`).get.value) {
      Some(this)
    } else {
      None
    }
  } else {
    Some(
      Solution(
        length,
        types,
        board.updated(position, board(position).updated(item.`type`, Some(item)))
      )
    )
  }

  def canPlace(item: Item, position: Int): Boolean = if (board(position)(item.`type`).isEmpty) {
    !board.flatMap(row => row(item.`type`).map(_.value)).contains(item.value)
  } else {
    item.value == board(position)(item.`type`).get.value
  }

  override def toString: String = {
    (for (row <- board) yield {
      (for (field <- row) yield {
        val value = field.map(_.value).getOrElse("")
        "%20s".format(value)
      }).mkString("|")
    }).mkString("\n")
  }
}

object Solution {
  def empty(length: Int, types: Int): Solution = Solution(length, types, Seq.fill(types)(Seq.fill(length)(None)))
}