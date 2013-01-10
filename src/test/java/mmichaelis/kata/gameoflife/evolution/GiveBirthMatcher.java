package mmichaelis.kata.gameoflife.evolution;

import mmichaelis.kata.gameoflife.Cell;
import org.hamcrest.CustomTypeSafeMatcher;
import org.hamcrest.Matchers;

import static mmichaelis.kata.gameoflife.CellIsAlive.cellIsAlive;
import static org.hamcrest.Matchers.both;

/**
* @since 1.0
*/
public class GiveBirthMatcher extends CustomTypeSafeMatcher<Cell> {
  private final Integer minimum;
  private final Integer maximum;

  public GiveBirthMatcher(final Integer minimum, final Integer maximum) {
    super(String.format("giving birth with living neighbors between %d and %d", minimum, maximum));
    this.minimum = minimum;
    this.maximum = maximum;
  }

  @Override
  protected boolean matchesSafely(final Cell item) {
    return both(Matchers.greaterThanOrEqualTo(minimum))
            .and(Matchers.lessThanOrEqualTo(maximum))
            .matches(item.countLinks(cellIsAlive()));
  }
}
