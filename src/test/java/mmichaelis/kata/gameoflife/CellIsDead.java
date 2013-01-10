package mmichaelis.kata.gameoflife;

import org.hamcrest.CustomTypeSafeMatcher;
import org.hamcrest.Factory;

/**
* @since 1.0
*/
public class CellIsDead extends CustomTypeSafeMatcher<Cell> {
  CellIsDead() {
    super("cell is dead");
  }

  @Override
  protected boolean matchesSafely(final Cell item) {
    return !item.isAlive();
  }

  @Factory
  public static CellIsDead cellIsDead() {
    return new CellIsDead();
  }
}