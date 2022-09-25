package dqwapi.infra.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.CaseFormat;
import dqwapi.domain.entity.Result5;
import dqwapi.domain.model.common.AttackType;
import dqwapi.domain.model.common.AttributeType;
import dqwapi.domain.model.common.BigQueryTableType;
import dqwapi.domain.model.common.RaceType;
import dqwapi.domain.model.job.JobParameter;
import dqwapi.domain.model.job.JobType;
import dqwapi.domain.model.kokoro.*;
import dqwapi.domain.repository.IKokoroCombinationRepository;
import dqwapi.infra.gcp.bigquery.IBigQueryConnector;
import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;

import static dqwapi.domain.model.common.KokoroType.RAINBOW;
import static dqwapi.domain.model.common.KokoroType.RED;
import static dqwapi.domain.model.common.KokoroType.RED_YELLOW;
import static dqwapi.domain.model.common.KokoroType.YELLOW;

@Profile("dwh-gcp-bigquery")
@Slf4j
@RequiredArgsConstructor
@Repository("big-query-repository5")
public class BigQueryKokoroCombination5Repository implements IKokoroCombinationRepository {

  @Value("${gcp.project-id}")
  private String projectId;

  @Value("${gcp.big-query.dataset}")
  private String dataset;

  @Value("${gcp.big-query.table}")
  private String table;

  @Value("${gcp.big-query.table-type}")
  private BigQueryTableType tableType;

  private final IBigQueryConnector bigQueryConnector;
  private String queryTemplate5DamageAttributeWithoutRace = "";
  private String queryTemplate5DamageAttributeWithRace = "";
  private String queryTemplate5DamageNoAttributeWithoutRace = "";
  private String queryTemplate5DamageNoAttributeWithRace = "";
  private String queryTemplate5HealingAll = "";

  private String queryTemplate5minDamageAttributeWithoutRace = "";
  private String queryTemplate5minDamageAttributeWithRace = "";
  private String queryTemplate5minDamageNoAttributeWithoutRace = "";
  private String queryTemplate5minDamageNoAttributeWithRace = "";
  private String queryTemplate5minHealingAll = "";

  @Value("${dqwapi.kokoro-json}")
  private String kokoroJson;

  private List<Kokoro> kokoros = new ArrayList<>();

  @PostConstruct
  void init() {
    try {
      queryTemplate5DamageAttributeWithoutRace = IOUtils.toString(new ClassPathResource("gcp-big-query-template5-damage-attribute-without-race.txt").getInputStream(), StandardCharsets.UTF_8);
      queryTemplate5DamageAttributeWithRace = IOUtils.toString(new ClassPathResource("gcp-big-query-template5-damage-attribute-with-race.txt").getInputStream(), StandardCharsets.UTF_8);
      queryTemplate5DamageNoAttributeWithoutRace = IOUtils.toString(new ClassPathResource("gcp-big-query-template5-damage-no-attribute-without-race.txt").getInputStream(), StandardCharsets.UTF_8);
      queryTemplate5DamageNoAttributeWithRace = IOUtils.toString(new ClassPathResource("gcp-big-query-template5-damage-no-attribute-with-race.txt").getInputStream(), StandardCharsets.UTF_8);
      queryTemplate5HealingAll = IOUtils.toString(new ClassPathResource("gcp-big-query-template5-healing-all.txt").getInputStream(), StandardCharsets.UTF_8);

      queryTemplate5minDamageAttributeWithoutRace = IOUtils.toString(new ClassPathResource("gcp-big-query-template5min-damage-attribute-without-race.txt").getInputStream(), StandardCharsets.UTF_8);
      queryTemplate5minDamageAttributeWithRace = IOUtils.toString(new ClassPathResource("gcp-big-query-template5min-damage-attribute-with-race.txt").getInputStream(), StandardCharsets.UTF_8);
      queryTemplate5minDamageNoAttributeWithoutRace = IOUtils.toString(new ClassPathResource("gcp-big-query-template5min-damage-no-attribute-without-race.txt").getInputStream(), StandardCharsets.UTF_8);
      queryTemplate5minDamageNoAttributeWithRace = IOUtils.toString(new ClassPathResource("gcp-big-query-template5min-damage-no-attribute-with-race.txt").getInputStream(), StandardCharsets.UTF_8);
      queryTemplate5minHealingAll = IOUtils.toString(new ClassPathResource("gcp-big-query-template5min-healing-all.txt").getInputStream(), StandardCharsets.UTF_8);

      kokoros = new ObjectMapper().readValue(new ClassPathResource(kokoroJson).getInputStream(), new TypeReference<>() {});
    } catch (IOException ex) {
      throw new IllegalStateException("Failed to read SQL template.", ex);
    }
  }

  private Kokoro get(final int number, final GradeType gradeType) {
    for (Kokoro kokoro : kokoros) {
      if (kokoro.getNumber() == number && kokoro.getGrade().equals(gradeType)) {
        return kokoro;
      }
    }
    throw new IllegalArgumentException("");
  }

  private void setParameter5(final Combination combination, final JobType jobType) {
    final double magnification = 1.2;
    int hp = 0;
    int mp = 0;
    int op = 0;
    int dp = 0;
    int os = 0;
    int ds = 0;
    int sp = 0;
    int dx = 0;
    switch (jobType) {
      case GOD_HAND:
        if (combination.getSlots().get(0).getKokoro().getType().equals(RED) || combination.getSlots().get(0).getKokoro().getType().equals(RAINBOW)) {
          hp += (int) Math.ceil(combination.getSlots().get(0).getKokoro().getHp() * magnification);
          mp += (int) Math.ceil(combination.getSlots().get(0).getKokoro().getMp() * magnification);
          op += (int) Math.ceil(combination.getSlots().get(0).getKokoro().getOp() * magnification);
          dp += (int) Math.ceil(combination.getSlots().get(0).getKokoro().getDp() * magnification);
          os += (int) Math.ceil(combination.getSlots().get(0).getKokoro().getOs() * magnification);
          ds += (int) Math.ceil(combination.getSlots().get(0).getKokoro().getDs() * magnification);
          sp += (int) Math.ceil(combination.getSlots().get(0).getKokoro().getSp() * magnification);
          dx += (int) Math.ceil(combination.getSlots().get(0).getKokoro().getDx() * magnification);
        } else {
          hp += combination.getSlots().get(0).getKokoro().getHp();
          mp += combination.getSlots().get(0).getKokoro().getMp();
          op += combination.getSlots().get(0).getKokoro().getOp();
          dp += combination.getSlots().get(0).getKokoro().getDp();
          os += combination.getSlots().get(0).getKokoro().getOs();
          ds += combination.getSlots().get(0).getKokoro().getDs();
          sp += combination.getSlots().get(0).getKokoro().getSp();
          dx += combination.getSlots().get(0).getKokoro().getDx();
        }
        if (combination.getSlots().get(1).getKokoro().getType().equals(YELLOW) || combination.getSlots().get(1).getKokoro().getType().equals(RAINBOW)) {
          hp += (int) Math.ceil(combination.getSlots().get(1).getKokoro().getHp() * magnification);
          mp += (int) Math.ceil(combination.getSlots().get(1).getKokoro().getMp() * magnification);
          op += (int) Math.ceil(combination.getSlots().get(1).getKokoro().getOp() * magnification);
          dp += (int) Math.ceil(combination.getSlots().get(1).getKokoro().getDp() * magnification);
          os += (int) Math.ceil(combination.getSlots().get(1).getKokoro().getOs() * magnification);
          ds += (int) Math.ceil(combination.getSlots().get(1).getKokoro().getDs() * magnification);
          sp += (int) Math.ceil(combination.getSlots().get(1).getKokoro().getSp() * magnification);
          dx += (int) Math.ceil(combination.getSlots().get(1).getKokoro().getDx() * magnification);
        } else {
          hp += combination.getSlots().get(1).getKokoro().getHp();
          mp += combination.getSlots().get(1).getKokoro().getMp();
          op += combination.getSlots().get(1).getKokoro().getOp();
          dp += combination.getSlots().get(1).getKokoro().getDp();
          os += combination.getSlots().get(1).getKokoro().getOs();
          ds += combination.getSlots().get(1).getKokoro().getDs();
          sp += combination.getSlots().get(1).getKokoro().getSp();
          dx += combination.getSlots().get(1).getKokoro().getDx();
        }
        if (combination.getSlots().get(2).getKokoro().getType().equals(RED) || combination.getSlots().get(2).getKokoro().getType().equals(YELLOW) || combination.getSlots().get(2).getKokoro().getType().equals(RAINBOW)) {
          hp += (int) Math.ceil(combination.getSlots().get(2).getKokoro().getHp() * magnification);
          mp += (int) Math.ceil(combination.getSlots().get(2).getKokoro().getMp() * magnification);
          op += (int) Math.ceil(combination.getSlots().get(2).getKokoro().getOp() * magnification);
          dp += (int) Math.ceil(combination.getSlots().get(2).getKokoro().getDp() * magnification);
          os += (int) Math.ceil(combination.getSlots().get(2).getKokoro().getOs() * magnification);
          ds += (int) Math.ceil(combination.getSlots().get(2).getKokoro().getDs() * magnification);
          sp += (int) Math.ceil(combination.getSlots().get(2).getKokoro().getSp() * magnification);
          dx += (int) Math.ceil(combination.getSlots().get(2).getKokoro().getDx() * magnification);
        } else {
          hp += combination.getSlots().get(2).getKokoro().getHp();
          mp += combination.getSlots().get(2).getKokoro().getMp();
          op += combination.getSlots().get(2).getKokoro().getOp();
          dp += combination.getSlots().get(2).getKokoro().getDp();
          os += combination.getSlots().get(2).getKokoro().getOs();
          ds += combination.getSlots().get(2).getKokoro().getDs();
          sp += combination.getSlots().get(2).getKokoro().getSp();
          dx += combination.getSlots().get(2).getKokoro().getDx();
        }
        if (combination.getSlots().get(3).getKokoro().getType().equals(RED) || combination.getSlots().get(3).getKokoro().getType().equals(YELLOW) || combination.getSlots().get(3).getKokoro().getType().equals(RAINBOW)) {
          hp += (int) Math.ceil(combination.getSlots().get(3).getKokoro().getHp() * magnification);
          mp += (int) Math.ceil(combination.getSlots().get(3).getKokoro().getMp() * magnification);
          op += (int) Math.ceil(combination.getSlots().get(3).getKokoro().getOp() * magnification);
          dp += (int) Math.ceil(combination.getSlots().get(3).getKokoro().getDp() * magnification);
          os += (int) Math.ceil(combination.getSlots().get(3).getKokoro().getOs() * magnification);
          ds += (int) Math.ceil(combination.getSlots().get(3).getKokoro().getDs() * magnification);
          sp += (int) Math.ceil(combination.getSlots().get(3).getKokoro().getSp() * magnification);
          dx += (int) Math.ceil(combination.getSlots().get(3).getKokoro().getDx() * magnification);
        } else {
          hp += combination.getSlots().get(3).getKokoro().getHp();
          mp += combination.getSlots().get(3).getKokoro().getMp();
          op += combination.getSlots().get(3).getKokoro().getOp();
          dp += combination.getSlots().get(3).getKokoro().getDp();
          os += combination.getSlots().get(3).getKokoro().getOs();
          ds += combination.getSlots().get(3).getKokoro().getDs();
          sp += combination.getSlots().get(3).getKokoro().getSp();
          dx += combination.getSlots().get(3).getKokoro().getDx();
        }
        break;
      default:
        throw new IllegalArgumentException("");
    }
    hp += (int) Math.ceil(combination.getSlots().get(4).getKokoro().getHp() * magnification);
    mp += (int) Math.ceil(combination.getSlots().get(4).getKokoro().getMp() * magnification);
    op += (int) Math.ceil(combination.getSlots().get(4).getKokoro().getOp() * magnification);
    dp += (int) Math.ceil(combination.getSlots().get(4).getKokoro().getDp() * magnification);
    os += (int) Math.ceil(combination.getSlots().get(4).getKokoro().getOs() * magnification);
    ds += (int) Math.ceil(combination.getSlots().get(4).getKokoro().getDs() * magnification);
    sp += (int) Math.ceil(combination.getSlots().get(4).getKokoro().getSp() * magnification);
    dx += (int) Math.ceil(combination.getSlots().get(4).getKokoro().getDx() * magnification);

    combination.setHp(hp);
    combination.setMp(mp);
    combination.setOp(op);
    combination.setDp(dp);
    combination.setOs(os);
    combination.setDs(ds);
    combination.setDx(dx);
    combination.setSp(sp);
  }

  private List<Combination> convert5(final List<Result5> results, final JobType jobType) {
    final List<Combination> combinations = new ArrayList<>();
    for (Result5 result : results) {
      final String pattern = result.getPattern();
      final int k0Index = Integer.parseInt(pattern.substring(1, 2));
      final int k1Index = Integer.parseInt(pattern.substring(2, 3));
      final int k2Index = Integer.parseInt(pattern.substring(3, 4));
      final int k3Index = Integer.parseInt(pattern.substring(4, 5));
      final int k4Index = Integer.parseInt(pattern.substring(5, 6));
      log.debug("{}, {}, {}, {}, {}", k0Index, k1Index, k2Index, k3Index, k4Index);
      List<Integer> indexes = Arrays.asList(k0Index, k1Index, k2Index, k3Index, k4Index);
      List<Integer> ids = Arrays.asList(
              result.getK0id(), result.getK1id(), result.getK2id(), result.getK3id(), result.getK4id()
      );
      List<GradeType> grades = Arrays.asList(
              result.getK0grade(), result.getK1grade(), result.getK2grade(), result.getK3grade(), result.getK4grade()
      );
      final Combination combination = new Combination();
      final List<Slot> slots = new ArrayList<>();
      for (int i = 0; i < 5; i++) {
        final Slot slot = new Slot();
        slot.setKokoro(get(ids.get(indexes.get(i)), grades.get(indexes.get(i))));
        switch (jobType) {
          case GOD_HAND:
            switch (i) {
              case 0:
                slot.setType(RED);
                if (slot.getKokoro().getType().equals(RED) || slot.getKokoro().getType().equals(RAINBOW)) {
                  slot.setUp(true);
                }
                break;
              case 1:
                slot.setType(YELLOW);
                if (slot.getKokoro().getType().equals(YELLOW) || slot.getKokoro().getType().equals(RAINBOW)) {
                  slot.setUp(true);
                }
                break;
              case 2:
              case 3:
                slot.setType(RED_YELLOW);
                if (slot.getKokoro().getType().equals(RED) || slot.getKokoro().getType().equals(YELLOW) || slot.getKokoro().getType().equals(RAINBOW)
                ) {
                  slot.setUp(true);
                }
                break;
              case 4:
                slot.setType(RAINBOW);
                slot.setUp(true);
                break;
              default:
                throw new IllegalArgumentException("");
            }
            break;
          default:
            throw new IllegalArgumentException("");
        }
        slots.add(slot);
      }
      combination.setSlots(slots);

      setParameter5(combination, jobType);

      final int cost = combination.getSlots().get(0).getKokoro().getCost()
              + combination.getSlots().get(1).getKokoro().getCost()
              + combination.getSlots().get(2).getKokoro().getCost()
              + combination.getSlots().get(3).getKokoro().getCost()
              + combination.getSlots().get(4).getKokoro().getCost();
      combination.setCost(cost);

      final List<Damage> damages = new ArrayList<>();
      for (Slot slot : slots) {
        damages.addAll(slot.getKokoro().getDamages());
      }
      final List<Damage> mergedDamages = new ArrayList<>();
      for (int x = 0; x < damages.size(); x++) {
        boolean isMerged = false;
        int mergedMagnification = damages.get(x).getMagnification();
        for (int y = 0; y < damages.size(); y++) {
          if (damages.get(x).getAttack()
                  .equals(damages.get(y).getAttack())
                  && damages.get(x).getAttribute()
                  .equals(damages.get(y).getAttribute())
                  && damages.get(x).getRace()
                  .equals(damages.get(y).getRace())
          ) {
            if (y > x) {
              mergedMagnification += damages.get(y).getMagnification();
            } else if (y < x) {
              isMerged = true;
            }
          }
        }
        if (!isMerged) {
          final Damage mergedDamage = new Damage();
          mergedDamage.setAttack(damages.get(x).getAttack());
          mergedDamage.setAttribute(damages.get(x).getAttribute());
          mergedDamage.setRace(damages.get(x).getRace());
          mergedDamage.setMagnification(mergedMagnification);
          mergedDamages.add(mergedDamage);
        }
      }
      combination.setDamages(damages);

      final List<Healing> healings = new ArrayList<>();
      for (Slot slot : slots) {
        healings.addAll(slot.getKokoro().getHealings());
      }
      final List<Healing> mergedHealings = new ArrayList<>();
      for (int x = 0; x < healings.size(); x++) {
        boolean isMerged = false;
        int mergedMagnification = healings.get(x).getMagnification();
        for (int y = 0; y < healings.size(); y++) {
          if (healings.get(x).getType().equals(healings.get(y).getType())) {
            if (y > x) {
              mergedMagnification += healings.get(y).getMagnification();
            } else if (y < x) {
              isMerged = true;
            }
          }
        }
        if (!isMerged) {
          final Healing mergedHealing = new Healing();
          mergedHealing.setType(healings.get(x).getType());
          mergedHealing.setMagnification(mergedMagnification);
          mergedHealings.add(mergedHealing);
        }
      }
      combination.setHealings(healings);

      combinations.add(combination);
      log.debug("{}, {}, {}, {}, {}",
              result.getK0id(),
              result.getK1id(),
              result.getK2id(),
              result.getK3id(),
              result.getK4id()
      );
      log.debug("{}, {}, {}, {}, {}",
              slots.get(0).getKokoro().getNumber(),
              slots.get(1).getKokoro().getNumber(),
              slots.get(2).getKokoro().getNumber(),
              slots.get(3).getKokoro().getNumber(),
              slots.get(4).getKokoro().getNumber()
      );
    }
    return combinations;
  }

  @Override
  public List<Combination> get(
      final JobType jobType,
      final AttackType attackType,
      final AttributeType attributeType,
      final RaceType raceType,
      final int cost,
      final JobParameter jobParameter,
      final List<Integer> nonBrides,
      final Map<Integer, List<GradeType>> exclusions,
      final Map<Integer, List<GradeType>> inclusions,
      final int limit
  ) {
    final List<String> exclusionGrades = new ArrayList<>();
    for (final Map.Entry<Integer, List<GradeType>> entry : exclusions.entrySet()) {
      final Integer key = entry.getKey();
      for (GradeType gradeType : entry.getValue()) {
        exclusionGrades.add(key + "_" + gradeType.name());
      }
    }
    if (exclusionGrades.size() == 0) {
      exclusionGrades.add("");
    }
    final List<String> inclusionGrades = new ArrayList<>();
    for (final Map.Entry<Integer, List<GradeType>> entry : inclusions.entrySet()) {
      final Integer key = entry.getKey();
      for (GradeType gradeType : entry.getValue()) {
        inclusionGrades.add(key + "_" + gradeType.name());
      }
    }

    final int base = switch (attackType) {
      case SLASH, HIT -> jobParameter.getOp();
      case SPELL -> jobParameter.getOs();
      case PHYSICS_SPELL_SLASH, PHYSICS_SPELL_HIT -> jobParameter.getOp() + jobParameter.getOs();
      case BREATH -> jobParameter.getOp() + jobParameter.getDx();
      case HEALING_SPELL, HEALING_SPECIALTY -> jobParameter.getDs();
      default -> throw new IllegalArgumentException("Unknown AttackType: " + attackType);
    };

    final String parameter = switch (attackType) {
      case SLASH, HIT -> "op";
      case SPELL -> "os";
      case PHYSICS_SPELL_SLASH, PHYSICS_SPELL_HIT -> "opos";
      case BREATH -> "opdx";
      case HEALING_SPELL, HEALING_SPECIALTY -> "ds";
      default -> throw new IllegalArgumentException("Unknown AttackType: " + attackType);
    };
    final String pattern = "max_" + jobType.name().toLowerCase() + "_" + parameter + "_pattern";
    final String joinedNonBrides = nonBrides.stream()
        .map(integer -> Integer.toString(integer)).collect(Collectors.joining(","));
    final String joinedExclusions = exclusionGrades.stream()
        .collect(Collectors.joining("','", "'", "'"));
    final StringBuilder replacedInclusions = new StringBuilder();
    if (inclusionGrades.size() > 0) {
      for (final String str : inclusionGrades) {
        replacedInclusions.append("""
                 AND (CONCAT(k0.number, '_', k0.grade) IN ('{{inclusion}}') OR CONCAT(k1.number, '_', k1.grade) IN ('{{inclusion}}') OR CONCAT(k2.number, '_', k2.grade) IN ('{{inclusion}}') OR CONCAT(k3.number, '_', k3.grade) IN ('{{inclusion}}') OR CONCAT(k4.number, '_', k4.grade) IN ('{{inclusion}}')) 
                 """.replace("{{inclusion}}", str));
        }
    }

    // To reduce resource that query uses (from)
    final String k0Order = switch (attackType) {
      case SLASH, HIT -> "op";
      case SPELL -> "os";
      case PHYSICS_SPELL_SLASH, PHYSICS_SPELL_HIT -> "op+os";
      case BREATH -> "op+dx";
      case HEALING_SPELL, HEALING_SPECIALTY -> "ds";
      default -> throw new IllegalArgumentException("Unknown AttackType: " + attackType);
    };
    final String k0JoinedExclusions = exclusionGrades.stream()
            .collect(Collectors.joining("','", "'", "'"));
    final StringBuilder k0ReplacedInclusions = new StringBuilder();
    if (inclusionGrades.size() > 0) {
      for (final String str : inclusionGrades) {
        k0ReplacedInclusions.append("""
            OR CONCAT(number, '_', grade) IN ('{{inclusion}}') 
            """.replace("{{inclusion}}", str));
      }
    }
    final int k0Limit = 30; // TODO: fix

    final String k1Order = k0Order;
    final String k1JoinedExclusions = exclusionGrades.stream()
            .collect(Collectors.joining("','", "'", "'"));
    final StringBuilder k1ReplacedInclusions = new StringBuilder();
    if (inclusionGrades.size() > 0) {
      for (final String str : inclusionGrades) {
        k1ReplacedInclusions.append("""
            OR CONCAT(number, '_', grade) IN ('{{inclusion}}') 
            """.replace("{{inclusion}}", str));
      }
    }
    final int k1Limit = 30; // TODO: fix

    final String k2Order = k0Order;
    final String k2JoinedExclusions = exclusionGrades.stream()
            .collect(Collectors.joining("','", "'", "'"));
    final StringBuilder k2ReplacedInclusions = new StringBuilder();
    if (inclusionGrades.size() > 0) {
      for (final String str : inclusionGrades) {
        k2ReplacedInclusions.append("""
            OR CONCAT(number, '_', grade) IN ('{{inclusion}}') 
            """.replace("{{inclusion}}", str));
      }
    }
    final int k2Limit = 30; // TODO: fix

    final String k3Order = k0Order;
    final String k3JoinedExclusions = exclusionGrades.stream()
            .collect(Collectors.joining("','", "'", "'"));
    final StringBuilder k3ReplacedInclusions = new StringBuilder();
    if (inclusionGrades.size() > 0) {
      for (final String str : inclusionGrades) {
        k3ReplacedInclusions.append("""
            OR CONCAT(number, '_', grade) IN ('{{inclusion}}') 
            """.replace("{{inclusion}}", str));
      }
    }
    final int k3Limit = 30; // TODO: fix
    // To reduce resource that query uses (to)

    final String replacedQuery;
    if (tableType.equals(BigQueryTableType.CROSS)) {
      final List<String> parameters = new ArrayList<>();
      switch (attackType) {
        case SLASH, HIT:
          parameters.add("k0.op");
          parameters.add("k1.op");
          parameters.add("k2.op");
          parameters.add("k3.op");
          parameters.add("k4.op");
          break;
        case SPELL:
          parameters.add("k0.os");
          parameters.add("k1.os");
          parameters.add("k2.os");
          parameters.add("k3.os");
          parameters.add("k4.os");
          break;
        case PHYSICS_SPELL_SLASH, PHYSICS_SPELL_HIT:
          parameters.add("k0.op + k0.os");
          parameters.add("k1.op + k1.os");
          parameters.add("k2.op + k2.os");
          parameters.add("k3.op + k3.os");
          parameters.add("k4.op + k4.os");
          break;
        case BREATH:
          parameters.add("k0.op + k0.dx");
          parameters.add("k1.op + k1.dx");
          parameters.add("k2.op + k2.dx");
          parameters.add("k3.op + k3.dx");
          parameters.add("k4.op + k4.dx");
          break;
        case HEALING_SPELL, HEALING_SPECIALTY:
          parameters.add("k0.ds");
          parameters.add("k1.ds");
          parameters.add("k2.ds");
          parameters.add("k3.ds");
          parameters.add("k4.ds");
          break;
        default:
          throw new IllegalArgumentException("Unknown AttackType: " + attackType);
      }
      final AttackType replacedAttackType;
      if (attackType.equals(AttackType.PHYSICS_SPELL_SLASH)) {
        replacedAttackType = AttackType.SLASH;
      } else if (attackType.equals(AttackType.PHYSICS_SPELL_HIT)) {
        replacedAttackType = AttackType.HIT;
      } else {
        replacedAttackType = attackType;
      }

      final String column;
      if (raceType.equals(RaceType.NONE)) {
        switch (attackType) {
          case SLASH, HIT, SPELL, PHYSICS_SPELL_SLASH, PHYSICS_SPELL_HIT, BREATH:
            column = (jobType.name()
                + "_" + attributeType.name()
                + "_" + replacedAttackType.name()
                + "_damage").toLowerCase();
            break;
          case HEALING_SPELL, HEALING_SPECIALTY:
            column = (jobType.name()
                + "_" + replacedAttackType.name()
                + "_damage").toLowerCase();
            break;
          default:
            throw new IllegalArgumentException("Unknown AttackType: " + attackType);
        }
      } else {
        switch (attackType) {
          case SLASH, HIT, SPELL, PHYSICS_SPELL_SLASH, PHYSICS_SPELL_HIT, BREATH:
            column = (jobType.name()
                + "_" + attributeType.name()
                + "_" + replacedAttackType.name()
                + "_" + raceType.name()
                + "_damage").toLowerCase();
            break;
          case HEALING_SPELL, HEALING_SPECIALTY:
            column = (jobType.name()
                + "_" + replacedAttackType.name()
                + "_damage").toLowerCase();
            break;
          default:
            throw new IllegalArgumentException("Unknown AttackType: " + attackType);
        }
      }

      final String queryTemplateDamageAttributeWithoutRace;
      final String queryTemplateDamageAttributeWithRace;
      final String queryTemplateDamageNoAttributeWithoutRace;
      final String queryTemplateDamageNoAttributeWithRace;
      final String queryTemplateHealingAll;
      if (cost >= 350) {
        queryTemplateDamageAttributeWithoutRace = queryTemplate5DamageAttributeWithoutRace;
        queryTemplateDamageAttributeWithRace = queryTemplate5DamageAttributeWithRace;
        queryTemplateDamageNoAttributeWithoutRace = queryTemplate5DamageNoAttributeWithoutRace;
        queryTemplateDamageNoAttributeWithRace = queryTemplate5DamageNoAttributeWithRace;
        queryTemplateHealingAll = queryTemplate5HealingAll;
      } else {
        queryTemplateDamageAttributeWithoutRace = queryTemplate5minDamageAttributeWithoutRace;
        queryTemplateDamageAttributeWithRace = queryTemplate5minDamageAttributeWithRace;
        queryTemplateDamageNoAttributeWithoutRace = queryTemplate5minDamageNoAttributeWithoutRace;
        queryTemplateDamageNoAttributeWithRace = queryTemplate5minDamageNoAttributeWithRace;
        queryTemplateHealingAll = queryTemplate5minHealingAll;
      }
      switch (attackType) {
        case SLASH, HIT, SPELL, PHYSICS_SPELL_SLASH, PHYSICS_SPELL_HIT, BREATH:
          if (attributeType.equals(AttributeType.NONE)) {
            if (raceType.equals(RaceType.NONE)) {
              replacedQuery = queryTemplateDamageNoAttributeWithoutRace.replace("{{project-id}}", projectId)
                      .replace("{{dataset}}", dataset)
                      .replace("{{table}}", table)
                      .replace("{{JOB}}", jobType.name())
                      .replace("{{job}}", jobType.name().toLowerCase())
                      .replace("{{param0}}", parameters.get(0))
                      .replace("{{param1}}", parameters.get(1))
                      .replace("{{param2}}", parameters.get(2))
                      .replace("{{param3}}", parameters.get(3))
                      .replace("{{param4}}", parameters.get(4))
                      .replace("{{base}}", Integer.toString(base))
                      .replace("{{attack}}", replacedAttackType.name().toLowerCase())
                      .replace("{{Attack}}", CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, replacedAttackType.name()))
                      .replace("{{attribute}}", attributeType.name().toLowerCase())
                      .replace("{{Attribute}}", CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, attributeType.name()))
                      .replace("{{race}}", raceType.name().toLowerCase())
                      .replace("{{Race}}", CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, raceType.name()))
                      .replace("{{pattern}}", pattern)
                      .replace("{{cost}}", Integer.toString(cost))
                      .replace("{{joinedNonBrides}}", joinedNonBrides)
                      .replace("{{joinedExclusions}}", joinedExclusions)
                      .replace("{{inclusions}}", replacedInclusions.toString())
                      .replace("{{column}}", column)
                      .replace("{{limit}}", Integer.toString(limit))
                      .replace("{{k0Order}}", k0Order)
                      .replace("{{k0Limit}}", Integer.toString(k0Limit))
                      .replace("{{k0JoinedExclusions}}", k0JoinedExclusions)
                      .replace("{{k0Inclusions}}", k0ReplacedInclusions.toString())
                      .replace("{{k1Order}}", k1Order)
                      .replace("{{k1Limit}}", Integer.toString(k1Limit))
                      .replace("{{k1JoinedExclusions}}", k1JoinedExclusions)
                      .replace("{{k1Inclusions}}", k1ReplacedInclusions.toString())
                      .replace("{{k2Order}}", k2Order)
                      .replace("{{k2Limit}}", Integer.toString(k2Limit))
                      .replace("{{k2JoinedExclusions}}", k2JoinedExclusions)
                      .replace("{{k2Inclusions}}", k2ReplacedInclusions.toString())
                      .replace("{{k3Order}}", k3Order)
                      .replace("{{k3Limit}}", Integer.toString(k3Limit))
                      .replace("{{k3JoinedExclusions}}", k3JoinedExclusions)
                      .replace("{{k3Inclusions}}", k3ReplacedInclusions.toString());
            } else {
              replacedQuery = queryTemplateDamageNoAttributeWithRace.replace("{{project-id}}", projectId)
                      .replace("{{dataset}}", dataset)
                      .replace("{{table}}", table)
                      .replace("{{JOB}}", jobType.name())
                      .replace("{{job}}", jobType.name().toLowerCase())
                      .replace("{{param0}}", parameters.get(0))
                      .replace("{{param1}}", parameters.get(1))
                      .replace("{{param2}}", parameters.get(2))
                      .replace("{{param3}}", parameters.get(3))
                      .replace("{{param4}}", parameters.get(4))
                      .replace("{{base}}", Integer.toString(base))
                      .replace("{{attack}}", replacedAttackType.name().toLowerCase())
                      .replace("{{Attack}}", CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, replacedAttackType.name()))
                      .replace("{{attribute}}", attributeType.name().toLowerCase())
                      .replace("{{Attribute}}", CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, attributeType.name()))
                      .replace("{{race}}", raceType.name().toLowerCase())
                      .replace("{{Race}}", CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, raceType.name()))
                      .replace("{{pattern}}", pattern)
                      .replace("{{cost}}", Integer.toString(cost))
                      .replace("{{joinedNonBrides}}", joinedNonBrides)
                      .replace("{{joinedExclusions}}", joinedExclusions)
                      .replace("{{inclusions}}", replacedInclusions.toString())
                      .replace("{{column}}", column)
                      .replace("{{limit}}", Integer.toString(limit))
                      .replace("{{k0Order}}", k0Order)
                      .replace("{{k0Limit}}", Integer.toString(k0Limit))
                      .replace("{{k0JoinedExclusions}}", k0JoinedExclusions)
                      .replace("{{k0Inclusions}}", k0ReplacedInclusions.toString())
                      .replace("{{k1Order}}", k1Order)
                      .replace("{{k1Limit}}", Integer.toString(k1Limit))
                      .replace("{{k1JoinedExclusions}}", k1JoinedExclusions)
                      .replace("{{k1Inclusions}}", k1ReplacedInclusions.toString())
                      .replace("{{k2Order}}", k2Order)
                      .replace("{{k2Limit}}", Integer.toString(k2Limit))
                      .replace("{{k2JoinedExclusions}}", k2JoinedExclusions)
                      .replace("{{k2Inclusions}}", k2ReplacedInclusions.toString())
                      .replace("{{k3Order}}", k3Order)
                      .replace("{{k3Limit}}", Integer.toString(k3Limit))
                      .replace("{{k3JoinedExclusions}}", k3JoinedExclusions)
                      .replace("{{k3Inclusions}}", k3ReplacedInclusions.toString());
            }
          } else {
            if (raceType.equals(RaceType.NONE)) {
              replacedQuery = queryTemplateDamageAttributeWithoutRace.replace("{{project-id}}", projectId)
                      .replace("{{dataset}}", dataset)
                      .replace("{{table}}", table)
                      .replace("{{JOB}}", jobType.name())
                      .replace("{{job}}", jobType.name().toLowerCase())
                      .replace("{{param0}}", parameters.get(0))
                      .replace("{{param1}}", parameters.get(1))
                      .replace("{{param2}}", parameters.get(2))
                      .replace("{{param3}}", parameters.get(3))
                      .replace("{{param4}}", parameters.get(4))
                      .replace("{{base}}", Integer.toString(base))
                      .replace("{{attack}}", replacedAttackType.name().toLowerCase())
                      .replace("{{Attack}}", CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, replacedAttackType.name()))
                      .replace("{{attribute}}", attributeType.name().toLowerCase())
                      .replace("{{Attribute}}", CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, attributeType.name()))
                      .replace("{{race}}", raceType.name().toLowerCase())
                      .replace("{{Race}}", CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, raceType.name()))
                      .replace("{{pattern}}", pattern)
                      .replace("{{cost}}", Integer.toString(cost))
                      .replace("{{joinedNonBrides}}", joinedNonBrides)
                      .replace("{{joinedExclusions}}", joinedExclusions)
                      .replace("{{inclusions}}", replacedInclusions.toString())
                      .replace("{{column}}", column)
                      .replace("{{limit}}", Integer.toString(limit))
                      .replace("{{k0Order}}", k0Order)
                      .replace("{{k0Limit}}", Integer.toString(k0Limit))
                      .replace("{{k0JoinedExclusions}}", k0JoinedExclusions)
                      .replace("{{k0Inclusions}}", k0ReplacedInclusions.toString())
                      .replace("{{k1Order}}", k1Order)
                      .replace("{{k1Limit}}", Integer.toString(k1Limit))
                      .replace("{{k1JoinedExclusions}}", k1JoinedExclusions)
                      .replace("{{k1Inclusions}}", k1ReplacedInclusions.toString())
                      .replace("{{k2Order}}", k2Order)
                      .replace("{{k2Limit}}", Integer.toString(k2Limit))
                      .replace("{{k2JoinedExclusions}}", k2JoinedExclusions)
                      .replace("{{k2Inclusions}}", k2ReplacedInclusions.toString())
                      .replace("{{k3Order}}", k3Order)
                      .replace("{{k3Limit}}", Integer.toString(k3Limit))
                      .replace("{{k3JoinedExclusions}}", k3JoinedExclusions)
                      .replace("{{k3Inclusions}}", k3ReplacedInclusions.toString());
            } else {
              replacedQuery = queryTemplateDamageAttributeWithRace.replace("{{project-id}}", projectId)
                      .replace("{{dataset}}", dataset)
                      .replace("{{table}}", table)
                      .replace("{{JOB}}", jobType.name())
                      .replace("{{job}}", jobType.name().toLowerCase())
                      .replace("{{param0}}", parameters.get(0))
                      .replace("{{param1}}", parameters.get(1))
                      .replace("{{param2}}", parameters.get(2))
                      .replace("{{param3}}", parameters.get(3))
                      .replace("{{param4}}", parameters.get(4))
                      .replace("{{base}}", Integer.toString(base))
                      .replace("{{attack}}", replacedAttackType.name().toLowerCase())
                      .replace("{{Attack}}", CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, replacedAttackType.name()))
                      .replace("{{attribute}}", attributeType.name().toLowerCase())
                      .replace("{{Attribute}}", CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, attributeType.name()))
                      .replace("{{race}}", raceType.name().toLowerCase())
                      .replace("{{Race}}", CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, raceType.name()))
                      .replace("{{pattern}}", pattern)
                      .replace("{{cost}}", Integer.toString(cost))
                      .replace("{{joinedNonBrides}}", joinedNonBrides)
                      .replace("{{joinedExclusions}}", joinedExclusions)
                      .replace("{{inclusions}}", replacedInclusions.toString())
                      .replace("{{column}}", column)
                      .replace("{{limit}}", Integer.toString(limit))
                      .replace("{{k0Order}}", k0Order)
                      .replace("{{k0Limit}}", Integer.toString(k0Limit))
                      .replace("{{k0JoinedExclusions}}", k0JoinedExclusions)
                      .replace("{{k0Inclusions}}", k0ReplacedInclusions.toString())
                      .replace("{{k1Order}}", k1Order)
                      .replace("{{k1Limit}}", Integer.toString(k1Limit))
                      .replace("{{k1JoinedExclusions}}", k1JoinedExclusions)
                      .replace("{{k1Inclusions}}", k1ReplacedInclusions.toString())
                      .replace("{{k2Order}}", k2Order)
                      .replace("{{k2Limit}}", Integer.toString(k2Limit))
                      .replace("{{k2JoinedExclusions}}", k2JoinedExclusions)
                      .replace("{{k2Inclusions}}", k2ReplacedInclusions.toString())
                      .replace("{{k3Order}}", k3Order)
                      .replace("{{k3Limit}}", Integer.toString(k3Limit))
                      .replace("{{k3JoinedExclusions}}", k3JoinedExclusions)
                      .replace("{{k3Inclusions}}", k3ReplacedInclusions.toString());
            }
          }
          break;
        case HEALING_SPELL, HEALING_SPECIALTY:
          replacedQuery = queryTemplateHealingAll.replace("{{project-id}}", projectId)
                  .replace("{{dataset}}", dataset)
                  .replace("{{table}}", table)
                  .replace("{{JOB}}", jobType.name())
                  .replace("{{job}}", jobType.name().toLowerCase())
                  .replace("{{param0}}", parameters.get(0))
                  .replace("{{param1}}", parameters.get(1))
                  .replace("{{param2}}", parameters.get(2))
                  .replace("{{param3}}", parameters.get(3))
                  .replace("{{param4}}", parameters.get(4))
                  .replace("{{base}}", Integer.toString(base))
                  .replace("{{attack}}", replacedAttackType.name().toLowerCase())
                  .replace("{{Attack}}", CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, replacedAttackType.name()))
                  .replace("{{attribute}}", attributeType.name().toLowerCase())
                  .replace("{{Attribute}}", CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, attributeType.name()))
                  .replace("{{race}}", raceType.name().toLowerCase())
                  .replace("{{Race}}", CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, raceType.name()))
                  .replace("{{pattern}}", pattern)
                  .replace("{{cost}}", Integer.toString(cost))
                  .replace("{{joinedNonBrides}}", joinedNonBrides)
                  .replace("{{joinedExclusions}}", joinedExclusions)
                  .replace("{{inclusions}}", replacedInclusions.toString())
                  .replace("{{column}}", column)
                  .replace("{{limit}}", Integer.toString(limit))
                  .replace("{{k0Order}}", k0Order)
                  .replace("{{k0Limit}}", Integer.toString(k0Limit))
                  .replace("{{k0JoinedExclusions}}", k0JoinedExclusions)
                  .replace("{{k0Inclusions}}", k0ReplacedInclusions.toString())
                  .replace("{{k1Order}}", k1Order)
                  .replace("{{k1Limit}}", Integer.toString(k1Limit))
                  .replace("{{k1JoinedExclusions}}", k1JoinedExclusions)
                  .replace("{{k1Inclusions}}", k1ReplacedInclusions.toString())
                  .replace("{{k2Order}}", k2Order)
                  .replace("{{k2Limit}}", Integer.toString(k2Limit))
                  .replace("{{k2JoinedExclusions}}", k2JoinedExclusions)
                  .replace("{{k2Inclusions}}", k2ReplacedInclusions.toString())
                  .replace("{{k3Order}}", k3Order)
                  .replace("{{k3Limit}}", Integer.toString(k3Limit))
                  .replace("{{k3JoinedExclusions}}", k3JoinedExclusions)
                  .replace("{{k3Inclusions}}", k3ReplacedInclusions.toString());
          break;
        default:
          throw new IllegalArgumentException("Unknown AttackType: " + attackType);
      }
    } else if (tableType.equals(BigQueryTableType.ONE)) {
      throw new UnsupportedOperationException("Not implemented: " + tableType);
    } else {
      throw new IllegalArgumentException("Unknown BigQuery table type: " + tableType);
    }
    log.info("{}", replacedQuery);

    final List<Result5> results = new ArrayList<>();
    bigQueryConnector.query(replacedQuery).iterateAll().forEach(row -> {
      final Result5 result = new Result5();
      result.setK0id(row.get(0).getNumericValue().intValue());
      result.setK0grade(GradeType.valueOf(row.get(1).getStringValue()));
      result.setK1id(row.get(2).getNumericValue().intValue());
      result.setK1grade(GradeType.valueOf(row.get(3).getStringValue()));
      result.setK2id(row.get(4).getNumericValue().intValue());
      result.setK2grade(GradeType.valueOf(row.get(5).getStringValue()));
      result.setK3id(row.get(6).getNumericValue().intValue());
      result.setK3grade(GradeType.valueOf(row.get(7).getStringValue()));
      result.setK4id(row.get(8).getNumericValue().intValue());
      result.setK4grade(GradeType.valueOf(row.get(9).getStringValue()));
      result.setPattern(row.get(10).getStringValue());
      results.add(result);
    });
    log.info(results.toString());

    return convert5(results, jobType);
  }
}
