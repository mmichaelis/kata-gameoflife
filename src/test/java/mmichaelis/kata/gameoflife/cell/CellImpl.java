package mmichaelis.kata.gameoflife.cell;

import com.google.common.base.Objects;
import com.google.common.collect.Collections2;
import com.google.common.collect.Sets;
import mmichaelis.kata.gameoflife.evolution.EvolutionConfiguration;
import org.hamcrest.Matcher;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Collection;

import static mmichaelis.kata.gameoflife.evolution.MatcherPredicate.toPredicate;

/**
 * @since 1.0
 */
@Named("defaultCell")
public class CellImpl implements Cell {
  @Inject
  private EvolutionConfiguration evolutionConfiguration;

  private boolean alive;
  private final Collection<Cell> neighbors = Sets.newHashSet();

  @Override
  public void setAlive(final boolean alive) {
    this.alive = alive;
  }

  @Override
  public boolean isAlive() {
    return alive;
  }

  @Override
  public int countLinks(final Matcher<Cell> matcher) {
    return Collections2.filter(neighbors, toPredicate(matcher)).size();
  }

  @Override
  public Collection<Cell> getNeighbors() {
    return Sets.newHashSet(neighbors);
  }

  @Override
  public void linkTo(final Cell target) {
    neighbors.add(target);
  }

  @Override
  public void evolve() {
    if (evolutionConfiguration.overCrowded().matches(this)
            || evolutionConfiguration.underPopulated().matches(this)) {
      setAlive(false);
    }
    if (evolutionConfiguration.givingBirth().matches(this)) {
      setAlive(true);
    }
  }

  @Override
  public void unlinkAll() {
    neighbors.clear();
  }

  @Override
  public String toString() {
    return Objects.toStringHelper(this)
            .add("alive", alive)
            .toString();
  }
}
