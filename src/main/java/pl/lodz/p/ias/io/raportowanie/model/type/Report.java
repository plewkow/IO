package pl.lodz.p.ias.io.raportowanie.model.type;

import lombok.Getter;

public abstract class Report {
    @Getter
    private Long userId;

    public Report(Long userId) {
        this.userId = userId;
    }
}
