package mmichaelis.kata.gameoflife.evolution;

import mmichaelis.kata.gameoflife.Cell;
import org.hamcrest.CustomTypeSafeMatcher;

import static mmichaelis.kata.gameoflife.CellIsAlive.cellIsAlive;
import static org.hamcrest.Matchers.greaterThan;

/**
* @since 1.0
*/
public class OverCrowdedMatcher extends CustomTypeSafeMatcher<Cell> {
  private final Integer maximum;

  public OverCrowdedMatcher(final Integer maximum) {
    super(String.format("over-crowded with more than %d living neighbors", maximum));
    this.maximum = maximum;
  }

  @Override
  protected boolean matchesSafely(final Cell item) {
    return greaterThan(maximum).matches(item.countLinks(cellIsAlive()));
  }
}
