package org.everest.mvc.result;

public class Next implements IFilterResult {
    @Override
    public boolean sendResponse() {
        return false;
    }
}
