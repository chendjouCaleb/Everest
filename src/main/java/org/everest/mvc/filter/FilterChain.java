package org.everest.mvc.filter;

import java.util.ArrayList;
import java.util.List;

public class FilterChain {
    private List<IFilter> filters = new ArrayList<>();
    private IFilter currentFilter;
    private boolean finished;
    private int index = 0;

    public void addFilter(IFilter filter){
        filters.add(filter);
    }

    public boolean isFinished() {
        return finished;
    }

    public List<IFilter> getFilters() {
        return filters;
    }

    public void setFilters(List<IFilter> filters) {
        this.filters = filters;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public IFilter getCurrentFilter() {
        return currentFilter;
    }

    public void setCurrentFilter(IFilter currentFilter) {
        this.currentFilter = currentFilter;
    }
}
