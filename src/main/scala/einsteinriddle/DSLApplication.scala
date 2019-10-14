package einsteinriddle

import einsteinriddle.solver._
import einsteinriddle.dsl._

object DSLApplication extends App {

  object Types extends Enumeration {
    val Country = Value(0)
    val Hour = Value(1)
    val Cargo = Value(2)
    val Destination = Value(3)
    val Chimney = Value(4)
  }

  implicit class TypesLift(value: String) {
    def country: Item = Item(value, Types.Country.id)
    def hour: Item = Item(value, Types.Hour.id)
    def cargo: Item = Item(value, Types.Cargo.id)
    def destination: Item = Item(value, Types.Destination.id)
    def chimney: Item = Item(value, Types.Chimney.id)
  }

  def ship = riddleSubject

  implicit val builder: EinsteinRiddleBuilder = new EinsteinRiddleBuilder(types = 5, length = 5)

  ship of ("Greece" country) and ("Six" hour) and ("Coffee" cargo) is the same

  ship of ("Black" chimney) is on position 3

  ship of ("England" country) and ("Nine" hour) is the same

  ship of ("France" country) and ("Blue" chimney) is left to ("Coffee" cargo)

  ship of ("Cocoa" cargo) is left to ("Marseilles" destination)

  ship of ("Brazil" country) and ("Manila" destination) is the same

  ship of ("Rice" cargo) is next to ("Green" chimney)

  ship of ("Genui" destination) and ("Five" hour) is the same

  ship of ("Spain" country) and ("Seven" hour) is right to ("Marseilles" destination)

  ship of ("Hamburg" destination) and ("Red" chimney) is the same

  ship of ("Seven" hour) is next to ("White" chimney)

  ship of ("Sweetcorn" cargo) is on position 5

  ship of ("Black" chimney) and ("Eight" hour) is the same

  ship of ("Rice" cargo) is next to ("Sweetcorn" cargo)

  ship of ("Hamburg" destination) and ("Six" hour) is the same

  where is ("Tea" cargo)

  where is ("Port Said" destination)

  val solution = builder.solve()
  solution match {
    case Some(result) => println(result)
    case None => println("No solution")
  }
}