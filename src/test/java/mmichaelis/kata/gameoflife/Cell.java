package mmichaelis.kata.gameoflife;

/**
* @since 1.0
*/
interface Cell {
  void setAlive(boolean alive);

  boolean isAlive();

  void linkTo(Cell target, Direction direction);

  Cell getLink(Direction direction);
}
