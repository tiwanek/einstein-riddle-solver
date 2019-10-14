package einsteinriddle

import einsteinriddle.solver._

package object dsl {

  class TheDSL(itemDSL: ItemDSL) {
    def same(implicit builder: EinsteinRiddleBuilder): Unit = {
      builder.addPuzzle(CompoundPuzzle(itemDSL.toSeq))
    }
  }

  class NextDSL(itemDSL: ItemDSL) {
    def to(value: Item)(implicit builder: EinsteinRiddleBuilder): Unit = {
      builder.addPuzzle(NextToPuzzle(
        itemDSL.toSeq,
        value
      ))
    }
  }

  class LeftDSL(itemDSL: ItemDSL) {
    def to(value: Item)(implicit builder: EinsteinRiddleBuilder): Unit = {
      builder.addPuzzle(DirectedPuzzle(
        itemDSL.toSeq,
        value,
        Direction.Right
      ))
    }
  }

  class RightDSL(itemDSL: ItemDSL) {
    def to(value: Item)(implicit builder: EinsteinRiddleBuilder): Unit = {
      builder.addPuzzle(DirectedPuzzle(
        itemDSL.toSeq,
        value,
        Direction.Left
      ))
    }
  }

  class OnDSL(itemDSL: ItemDSL) {
    def position(value: Int)(implicit builder: EinsteinRiddleBuilder): Unit = {
      assert(itemDSL.toSeq.size == 1)
      builder.addPuzzle(PlacedPuzzle(
        itemDSL.toSeq.head,
        value - 1
      ))
    }
  }

  case class TheDSLToken()
  case class NextDSLToken()
  case class LeftDSLToken()
  case class RightDSLToken()
  case class OnDSLToken()

  val the = TheDSLToken()
  val next = NextDSLToken()
  val left = LeftDSLToken()
  val right = RightDSLToken()
  val on = OnDSLToken()

  implicit class ItemDSL(value: Item) {
    private var next: Option[ItemDSL] = None

    def and(other: ItemDSL): ItemDSL = {
      other.next = Some(this)
      other
    }

    def is(token: TheDSLToken): TheDSL = new TheDSL(this)
    def is(token: NextDSLToken): NextDSL = new NextDSL(this)
    def is(token: LeftDSLToken): LeftDSL = new LeftDSL(this)
    def is(token: RightDSLToken): RightDSL = new RightDSL(this)
    def is(token: OnDSLToken): OnDSL = new OnDSL(this)

    def toSeq: Seq[Item] = next.map(_.toSeq).getOrElse(Seq.empty) :+ value
  }

  class WhereDSL {
    def is(item: Item)(implicit builder: EinsteinRiddleBuilder): Unit = {
      builder.addPuzzle(CompoundPuzzle(Seq(item)))
    }
  }

  class RowDSL() {
    def of(item: ItemDSL): ItemDSL = item
  }

  def where: WhereDSL = new WhereDSL()
  def rowType(`type`: Int): (String => Item) = value => Item(value, `type`)
  def riddleRow: RowDSL = new RowDSL()
}
