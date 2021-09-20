package dqwapi.domain.operator;

import dqwapi.domain.model.damage.DamageResult;
import dqwapi.domain.model.job.JobType;
import java.util.List;

public interface IDamageOperator {
  List<DamageResult> getDamages(
      final String weaponName,
      final String skillName,
      final JobType jobType,
      final int level,
      final int defence
  );
}
