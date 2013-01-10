package mmichaelis.kata.gameoflife;

/**
 * @since 1.0
 */
public class EvolutionConfiguration {
  private Integer minimumLivingNeighbors;
  private Integer maximumLivingNeighbors;

  public void setMinimumLivingNeighbors(final Integer minimumLivingNeighbors) {
    this.minimumLivingNeighbors = minimumLivingNeighbors;
  }

  public Integer getMinimumLivingNeighbors() {
    return minimumLivingNeighbors;
  }

  public void setMaximumLivingNeighbors(final Integer maximumLivingNeighbors) {
    this.maximumLivingNeighbors = maximumLivingNeighbors;
  }

  public Integer getMaximumLivingNeighbors() {
    return maximumLivingNeighbors;
  }
}
