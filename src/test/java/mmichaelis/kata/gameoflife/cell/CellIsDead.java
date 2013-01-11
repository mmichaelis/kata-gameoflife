package mmichaelis.kata.gameoflife.cell;

import org.hamcrest.CustomTypeSafeMatcher;
import org.hamcrest.Factory;

/**
 * @since 1.0
 */
public class CellIsDead extends CustomTypeSafeMatcher<Cell> {
  public CellIsDead() {
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
