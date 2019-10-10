package einsteinriddle

import einsteinriddle.solver._

object Application extends App {

  object Types extends Enumeration {
    val Country = Value(0)
    val Hour = Value(1)
    val Cargo = Value(2)
    val Destination = Value(3)
    val Chimney = Value(4)
  }

  val solver = new EinsteinRiddleSolver(
    types = 5,
    length = 5,
    inputPuzzles = Seq(
      CompoundPuzzle(
        Seq(
          Item("Greece", Types.Country.id),
          Item("Six", Types.Hour.id),
          Item("Coffee", Types.Cargo.id)
        )
      ),
      PlacedPuzzle(Item("Black", Types.Chimney.id), 2),
      CompoundPuzzle(
        Seq(
          Item("England", Types.Country.id),
          Item("Nine", Types.Hour.id)
        )
      ),
      DirectedPuzzle(
        Seq(
          Item("France", Types.Country.id),
          Item("Blue", Types.Chimney.id)
        ),
        Item("Coffee", Types.Cargo.id),
        Direction.Right
      ),
      DirectedPuzzle(
        Seq(
          Item("Cocoa", Types.Cargo.id)
        ),
        Item("Marseilles", Types.Destination.id),
        Direction.Right
      ),
      CompoundPuzzle(
        Seq(
          Item("Brazil", Types.Country.id),
          Item("Manila", Types.Destination.id)
        )
      ),
      NextToPuzzle(
        Seq(
          Item("Rice", Types.Cargo.id)
        ),
        Item("Green", Types.Chimney.id)
      ),
      CompoundPuzzle(
        Seq(
          Item("Genui", Types.Destination.id),
          Item("Five", Types.Hour.id)
        )
      ),
      DirectedPuzzle(
        Seq(
          Item("Spain", Types.Country.id),
          Item("Seven", Types.Hour.id)
        ),
        Item("Marseilles", Types.Destination.id),
        Direction.Left
      ),
      CompoundPuzzle(
        Seq(
          Item("Hamburg", Types.Destination.id),
          Item("Red", Types.Chimney.id)
        )
      ),
      NextToPuzzle(
        Seq(
          Item("Seven", Types.Hour.id)
        ),
        Item("White", Types.Chimney.id)
      ),
      PlacedPuzzle(Item("Sweetcorn", Types.Cargo.id), 4),
      CompoundPuzzle(
        Seq(
          Item("Black", Types.Chimney.id),
          Item("Eight", Types.Hour.id)
        )
      ),
      NextToPuzzle(
        Seq(
          Item("Rice", Types.Cargo.id)
        ),
        Item("Sweetcorn", Types.Cargo.id)
      ),
      CompoundPuzzle(
        Seq(
          Item("Hamburg", Types.Destination.id),
          Item("Six", Types.Hour.id)
        )
      ),
      CompoundPuzzle(Seq(Item("Tea", Types.Cargo.id))),
      CompoundPuzzle(Seq(Item("Port Said", Types.Destination.id)))
    )
  )

  val solution = solver.solve()
  solution match {
    case Some(result) => println(result)
    case None => println("No solution")
  }
}
