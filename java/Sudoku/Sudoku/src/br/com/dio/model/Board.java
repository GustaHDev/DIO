package br.com.dio.model;

import static br.com.dio.model.GameStatusEnum.NOT_STARTED;
import static br.com.dio.model.GameStatusEnum.INCOMPLETE;
import static br.com.dio.model.GameStatusEnum.COMPLETE;
import static java.util.Objects.nonNull;
import static java.util.Objects.isNull;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

public class Board {
    private final List<List<Space>> spaces;

    public Board(final List<List<Space>> spaces) {
        this.spaces = spaces;
    }

    public List<List<Space>> getSpaces() {
        return spaces;
    }

    public GameStatusEnum getStatus() {
        Stream<Space> spaceFlatMap = spaces.stream().flatMap(Collection::stream);
        if (spaceFlatMap.noneMatch(s -> !s.isFixed() && nonNull(s.getActual()))) {
            return NOT_STARTED;
        }

        return spaceFlatMap.anyMatch(s -> !isNull(s.getActual())) ? INCOMPLETE : COMPLETE;
    }

    public boolean hasErrors() {
        if (getStatus() == NOT_STARTED) {
            return false;
        }
        return spaces.stream().flatMap(Collection::stream)
                .anyMatch(s -> nonNull(s.getActual()) && !s.getActual().equals(s.getExpected()));
    }

    public boolean changeValue(final int col, final int row, final int value) {
        var space = spaces.get(col).get(row);

        if (space.isFixed()) {
            return false;
        }

        space.setActual(value);
        return true;
    }

    public boolean clearValue(final int col, final int row) {
        var space = spaces.get(col).get(row);
        if (space.isFixed()) {
            return false;
        }

        space.clearSpace();
        return true;

    }

    public void reset() {
        spaces.forEach(c -> c.forEach(Space::clearSpace));
    }

    public boolean gameWon(){
        return !hasErrors() && getStatus() == COMPLETE;
    }
}
