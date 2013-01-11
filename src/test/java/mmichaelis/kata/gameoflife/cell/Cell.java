package mmichaelis.kata.gameoflife.cell;

import org.hamcrest.Matcher;

import java.util.Collection;

/**
 * @since 1.0
 */
public interface Cell {
  void setAlive(boolean alive);

  boolean isAlive();

  void evolve();

  void unlinkAll();

  int countLinks(Matcher<Cell> matcher);

  Collection<Cell> getNeighbors();

  void linkTo(Cell target);
}
