package com.tsystems.javaschool.tasks.subsequence;

import java.util.List;

public class IsPossible {
    public IsPossible() {
    }

    public boolean find(List<Object> x, List<Object> y) {
        checkInput(x, y);
        if (x.isEmpty()) {
            return true;
        } else if (y.isEmpty()) {
            return false;
        } else return isPossible(x, y);
    }

    private void checkInput(List<Object> x, List<Object> y) {
        if (x == null || y == null) {
            throw new IllegalArgumentException();
        }
    }

    private boolean isPossible(List<Object> x, List<Object> y) {
        int j = 0;
        for (int i = 0; i < x.size(); i++) {
            for (int k = j; k < y.size(); k++) {
                if (x.get(i).equals(y.get(k))) {
                    j = k + 1;
                    break;
                } else if (k == y.size() - 1 && i < x.size() - 1) {
                    return false;
                }
            }
        }
        return true;
    }
}
