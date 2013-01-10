package mmichaelis.kata.gameoflife.cell;

import mmichaelis.kata.gameoflife.Direction;
import org.hamcrest.Matcher;

/**
* @since 1.0
*/
public interface Cell {
  void setAlive(boolean alive);

  boolean isAlive();

  void linkTo(Cell target, Direction direction);

  Cell getLink(Direction direction);

  void evolve();

  void unlinkAll();

  int countLinks(Matcher<Cell> matcher);
}
