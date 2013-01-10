package mmichaelis.kata.gameoflife;

import com.google.common.base.Objects;
import com.google.common.collect.Collections2;
import com.google.common.collect.Maps;
import org.hamcrest.Matcher;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Map;

import static mmichaelis.kata.gameoflife.evolution.MatcherPredicate.toPredicate;

/**
 * @since 1.0
 */
@Named("defaultCell")
public class CellImpl implements Cell {
  @Inject
  private EvolutionConfiguration evolutionConfiguration;

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
  public int countLinks(final Matcher<Cell> matcher) {
    return Collections2.filter(directedLinks.values(), toPredicate(matcher)).size();
  }

  @Override
  public void evolve() {
    if (evolutionConfiguration.overCrowded().matches(this)
            || evolutionConfiguration.underPopulated().matches(this)) {
      setAlive(false);
    }
  }

  @Override
  public void unlinkAll() {
    directedLinks.clear();
  }

  @Override
  public String toString() {
    return Objects.toStringHelper(this)
            .add("alive", alive)
            .toString();
  }
}
