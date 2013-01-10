package mmichaelis.kata.gameoflife;

import org.hamcrest.CustomTypeSafeMatcher;
import org.hamcrest.Factory;

/**
* @since 1.0
*/
public class CellIsAlive extends CustomTypeSafeMatcher<Cell> {
  CellIsAlive() {
    super("cell is alive");
  }

  @Override
  protected boolean matchesSafely(final Cell item) {
    return item.isAlive();
  }

  @Factory
  public static CellIsAlive cellIsAlive() {
    return new CellIsAlive();
  }
}
