package mmichaelis.kata.gameoflife;

import com.google.common.base.Objects;
import com.google.common.collect.Maps;

import java.util.Map;

/**
* @since 1.0
*/
public class CellImpl implements Cell {
  private boolean alive;
  private final Map<Direction, Cell> directedLinks = Maps.newHashMap();

  @Override
  public void setAlive(final boolean alive) {
    this.alive = alive;
  }

  @Override
  public boolean isAlive() {
    return alive;
  }

  @Override
  public void linkTo(final Cell target, final Direction direction) {
    directedLinks.put(direction, target);
  }

  @Override
  public Cell getLink(final Direction direction) {
    return directedLinks.get(direction);
  }

  @Override
  public void evolve() {
  }

  @Override
  public String toString() {
    return Objects.toStringHelper(this)
            .add("alive", alive)
            .toString();
  }
}
