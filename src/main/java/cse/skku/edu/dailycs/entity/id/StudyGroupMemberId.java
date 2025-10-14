package cse.skku.edu.dailycs.entity.id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Data
public class StudyGroupMemberId {
    private Long studyGroup;
    private Long user;
}
