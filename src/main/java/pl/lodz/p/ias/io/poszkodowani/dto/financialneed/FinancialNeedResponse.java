package pl.lodz.p.ias.io.poszkodowani.dto.financialneed;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.lodz.p.ias.io.poszkodowani.model.Need;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FinancialNeedResponse {

    private Long id;
    private Long userId;
    private Long mapPointId;
    private String description;
    private Date creationDate;
    private Date expirationDate;
    private Need.Status status;
    private int priority;
    private double collectionStatus;
    private double collectionGoal;
}
