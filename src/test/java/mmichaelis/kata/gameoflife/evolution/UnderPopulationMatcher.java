package mmichaelis.kata.gameoflife.evolution;

import mmichaelis.kata.gameoflife.Cell;
import org.hamcrest.CustomTypeSafeMatcher;

import static mmichaelis.kata.gameoflife.CellIsAlive.cellIsAlive;
import static org.hamcrest.Matchers.lessThan;

/**
* @since 1.0
*/
public class UnderPopulationMatcher extends CustomTypeSafeMatcher<Cell> {
  private final Integer minimum;

  public UnderPopulationMatcher(final Integer minimum) {
    super(String.format("under-populated with less than %d neighbors", minimum));
    this.minimum = minimum;
  }

  @Override
  protected boolean matchesSafely(final Cell item) {
    return lessThan(minimum).matches(item.countLinks(cellIsAlive()));
  }
}
