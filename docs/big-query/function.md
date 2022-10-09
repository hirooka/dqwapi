```
cd src/main/resources
jq -c '.[]' kokoro-flat.json > kokoro-flat-nd.json
cs
bq -> kokoro
```

```
CREATE OR REPLACE FUNCTION `hirooka-pro.dqwapi.getPlusAttributeMagnification`(attribute STRING, n0 INT64, g0 STRING, n1 INT64, g1 STRING, n2 INT64, g2 STRING, n3 INT64, g3 STRING)
RETURNS INT64 
LANGUAGE js AS r"""
  if (attribute === 'bagi') {
    if (n0 == 491 && g0 === 'SP' && n1 == 364 && g1 === 'SP'
      || n0 == 491 && g0 === 'SP' && n2 == 364 && g2 === 'SP'
      || n0 == 491 && g0 === 'SP' && n3 == 364 && g3 === 'SP'
      || n1 == 491 && g1 === 'SP' && n2 == 364 && g2 === 'SP'
      || n1 == 491 && g1 === 'SP' && n3 == 364 && g3 === 'SP'
      || n2 == 491 && g2 === 'SP' && n3 == 364 && g3 === 'SP'
      || n0 == 491 && g0 === 'SP' && n1 == 364 && g1 === 'SP'
      || n0 == 491 && g0 === 'SP' && n2 == 364 && g2 === 'SP'
      || n0 == 491 && g0 === 'SP' && n3 == 364 && g3 === 'SP'
      || n1 == 491 && g1 === 'SP' && n2 == 364 && g2 === 'SP'
      || n1 == 491 && g1 === 'SP' && n3 == 364 && g3 === 'SP'
      || n2 == 491 && g2 === 'SP' && n3 == 364 && g3 === 'SP'
    ) {
      return 10;
    }
    if (n0 == 491 && g0 === 'SP' && n1 == 285 && g1 === 'S'
      || n0 == 491 && g0 === 'SP' && n2 == 285 && g2 === 'S'
      || n0 == 491 && g0 === 'SP' && n3 == 285 && g3 === 'S'
      || n1 == 491 && g1 === 'SP' && n2 == 285 && g2 === 'S'
      || n1 == 491 && g1 === 'SP' && n3 == 285 && g3 === 'S'
      || n2 == 491 && g2 === 'SP' && n3 == 285 && g3 === 'S'
      || n0 == 491 && g0 === 'SP' && n1 == 285 && g1 === 'S'
      || n0 == 491 && g0 === 'SP' && n2 == 285 && g2 === 'S'
      || n0 == 491 && g0 === 'SP' && n3 == 285 && g3 === 'S'
      || n1 == 491 && g1 === 'SP' && n2 == 285 && g2 === 'S'
      || n1 == 491 && g1 === 'SP' && n3 == 285 && g3 === 'S'
      || n2 == 491 && g2 === 'SP' && n3 == 285 && g3 === 'S'
    ) {
      return 10;
    }
  }
  if (attribute === 'hyado') {
    if (n0 == 491 && g0 === 'SP' && n1 == 438 && g1 === 'S'
      || n0 == 491 && g0 === 'SP' && n2 == 438 && g2 === 'S'
      || n0 == 491 && g0 === 'SP' && n3 == 438 && g3 === 'S'
      || n1 == 491 && g1 === 'SP' && n2 == 438 && g2 === 'S'
      || n1 == 491 && g1 === 'SP' && n3 == 438 && g3 === 'S'
      || n2 == 491 && g2 === 'SP' && n3 == 438 && g3 === 'S'
      || n0 == 491 && g0 === 'SP' && n1 == 438 && g1 === 'S'
      || n0 == 491 && g0 === 'SP' && n2 == 438 && g2 === 'S'
      || n0 == 491 && g0 === 'SP' && n3 == 438 && g3 === 'S'
      || n1 == 491 && g1 === 'SP' && n2 == 438 && g2 === 'S'
      || n1 == 491 && g1 === 'SP' && n3 == 438 && g3 === 'S'
      || n2 == 491 && g2 === 'SP' && n3 == 438 && g3 === 'S'
    ) {
      return 10;
    }
  }
  return 0;
""";
```

```
CREATE OR REPLACE FUNCTION `hirooka-pro.dqwapi.getPlusAttackMagnification`(attack STRING, n0 INT64, g0 STRING, n1 INT64, g1 STRING, n2 INT64, g2 STRING, n3 INT64, g3 STRING)
RETURNS INT64 
LANGUAGE js AS r"""
  if (attack === 'hit') {
    if (n0 == 328 && g0 === 'S' && n1 == 329 && g1 === 'S'
      || n0 == 328 && g0 === 'S' && n2 == 329 && g2 === 'S'
      || n0 == 328 && g0 === 'S' && n3 == 329 && g3 === 'S'
      || n1 == 328 && g1 === 'S' && n2 == 329 && g2 === 'S'
      || n1 == 328 && g1 === 'S' && n3 == 329 && g3 === 'S'
      || n2 == 328 && g2 === 'S' && n3 == 329 && g3 === 'S'
      || n0 == 329 && g0 === 'S' && n1 == 328 && g1 === 'S'
      || n0 == 329 && g0 === 'S' && n2 == 328 && g2 === 'S'
      || n0 == 329 && g0 === 'S' && n3 == 328 && g3 === 'S'
      || n1 == 329 && g1 === 'S' && n2 == 328 && g2 === 'S'
      || n1 == 329 && g1 === 'S' && n3 == 328 && g3 === 'S'
      || n2 == 329 && g2 === 'S' && n3 == 328 && g3 === 'S'
    ) {
      return 10;
    }
  }
  return 0;
""";
```

```
CREATE OR REPLACE FUNCTION `hirooka-pro.dqwapi.getMaxValue`(job STRING, p0 FLOAT64, t0 STRING, p1 FLOAT64, t1 STRING, p2 FLOAT64, t2 STRING, p3 FLOAT64, t3 STRING)
RETURNS INT64 
LANGUAGE js AS r"""
  function get(job, p0, t0, p1, t1, p2, t2, p3) {
    const mag = 1.2;
    switch (job) {
      case 'BATTLE_MASTER':
        if (t0 === 'RED' || t0 === 'RAINBOW') {
          if (t1 === 'RED' || t1 === 'RAINBOW') {
            if (t2 === 'RED' || t2 === 'YELLOW' || t2 === 'RAINBOW') {
              return Math.ceil(p0 * mag)
                + Math.ceil(p1 * mag)
                + Math.ceil(p2 * mag)
                + Math.ceil(p3 * mag);
            } else {
              return Math.ceil(p0 * mag)
                + Math.ceil(p1 * mag)
                + p2
                + Math.ceil(p3 * mag); 
            }
          } else {
            if (t2 === 'RED' || t2 === 'YELLOW' || t2 === 'RAINBOW') {
              return Math.ceil(p0 * mag)
                + p1
                + Math.ceil(p2 * mag)
                + Math.ceil(p3 * mag);
            } else {
              return Math.ceil(p0 * mag)
                + p1
                + p2
                + Math.ceil(p3 * mag); 
            }  
          }
        } else {
          if (t1 === 'RED' || t1 === 'RAINBOW') {
            if (t2 === 'RED' || t2 === 'YELLOW' || t2 === 'RAINBOW') {
              return p0
                + Math.ceil(p1 * mag)
                + Math.ceil(p2 * mag)
                + Math.ceil(p3 * mag);
            } else {
              return p0
                + Math.ceil(p1 * mag)
                + p2
                + Math.ceil(p3 * mag); 
            }
          } else {
            if (t2 === 'RED' || t2 === 'YELLOW' || t2 === 'RAINBOW') {
              return p0
                + p1
                + Math.ceil(p2 * mag)
                + Math.ceil(p3 * mag);
            } else {
              return p0
                + p1
                + p2
                + Math.ceil(p3 * mag); 
            }  
          }
        }
        return 0;
      case 'SAGE':
        if (t0 === 'PURPLE' || t0 === 'GREEN' || t0 === 'RAINBOW') {
          if (t1 === 'PURPLE' || t1 === 'GREEN' || t1 === 'RAINBOW') {
            if (t2 === 'PURPLE' || t2 === 'GREEN' || t2 === 'RAINBOW') {
              return Math.ceil(p0 * mag)
                + Math.ceil(p1 * mag)
                + Math.ceil(p2 * mag)
                + Math.ceil(p3 * mag);
            } else {
              return Math.ceil(p0 * mag)
                + Math.ceil(p1 * mag)
                + p2
                + Math.ceil(p3 * mag); 
            }
          } else {
            if (t2 === 'PURPLE' || t2 === 'GREEN' || t2 === 'RAINBOW') {
              return Math.ceil(p0 * mag)
                + p1
                + Math.ceil(p2 * mag)
                + Math.ceil(p3 * mag);
            } else {
              return Math.ceil(p0 * mag)
                + p1
                + p2
                + Math.ceil(p3 * mag); 
            }  
          }
        } else {
          if (t1 === 'PURPLE' || t1 === 'GREEN' || t1 === 'RAINBOW') {
            if (t2 === 'PURPLE' || t2 === 'GREEN' || t2 === 'RAINBOW') {
              return p0
                + Math.ceil(p1 * mag)
                + Math.ceil(p2 * mag)
                + Math.ceil(p3 * mag);
            } else {
              return p0
                + Math.ceil(p1 * mag)
                + p2
                + Math.ceil(p3 * mag); 
            }
          } else {
            if (t2 === 'PURPLE' || t2 === 'GREEN' || t2 === 'RAINBOW') {
              return p0
                + p1
                + Math.ceil(p2 * mag)
                + Math.ceil(p3 * mag);
            } else {
              return p0
                + p1
                + p2
                + Math.ceil(p3 * mag); 
            }  
          }
        }
        return 0;
      case 'RANGER':
        if (t0 === 'BLUE' || t0 === 'RAINBOW') {
          if (t1 === 'BLUE' || t1 === 'RAINBOW') {
            if (t2 === 'RED' || t2 === 'BLUE' || t2 === 'RAINBOW') {
              return Math.ceil(p0 * mag)
                + Math.ceil(p1 * mag)
                + Math.ceil(p2 * mag)
                + Math.ceil(p3 * mag);
            } else {
              return Math.ceil(p0 * mag)
                + Math.ceil(p1 * mag)
                + p2
                + Math.ceil(p3 * mag); 
            }
          } else {
            if (t2 === 'RED' || t2 === 'BLUE' || t2 === 'RAINBOW') {
              return Math.ceil(p0 * mag)
                + p1
                + Math.ceil(p2 * mag)
                + Math.ceil(p3 * mag);
            } else {
              return Math.ceil(p0 * mag)
                + p1
                + p2
                + Math.ceil(p3 * mag); 
            }  
          }
        } else {
          if (t1 === 'BLUE' || t1 === 'RAINBOW') {
            if (t2 === 'RED' || t2 === 'BLUE' || t2 === 'RAINBOW') {
              return p0
                + Math.ceil(p1 * mag)
                + Math.ceil(p2 * mag)
                + Math.ceil(p3 * mag);
            } else {
              return p0
                + Math.ceil(p1 * mag)
                + p2
                + Math.ceil(p3 * mag); 
            }
          } else {
            if (t2 === 'RED' || t2 === 'BLUE' || t2 === 'RAINBOW') {
              return p0
                + p1
                + Math.ceil(p2 * mag)
                + Math.ceil(p3 * mag);
            } else {
              return p0
                + p1
                + p2
                + Math.ceil(p3 * mag); 
            }  
          }
        }
        return 0;
      case 'ARMAMENTALIST':
        if (t0 === 'YELLOW' || t0 === 'PURPLE' || t0 === 'RAINBOW') {
          if (t1 === 'YELLOW' || t1 === 'PURPLE' || t1 === 'RAINBOW') {
            if (t2 === 'YELLOW' || t2 === 'PURPLE' || t2 === 'RAINBOW') {
              return Math.ceil(p0 * mag)
                + Math.ceil(p1 * mag)
                + Math.ceil(p2 * mag)
                + Math.ceil(p3 * mag);
            } else {
              return Math.ceil(p0 * mag)
                + Math.ceil(p1 * mag)
                + p2
                + Math.ceil(p3 * mag); 
            }
          } else {
            if (t2 === 'YELLOW' || t2 === 'PURPLE' || t2 === 'RAINBOW') {
              return Math.ceil(p0 * mag)
                + p1
                + Math.ceil(p2 * mag)
                + Math.ceil(p3 * mag);
            } else {
              return Math.ceil(p0 * mag)
                + p1
                + p2
                + Math.ceil(p3 * mag); 
            }  
          }
        } else {
          if (t1 === 'YELLOW' || t1 === 'PURPLE' || t1 === 'RAINBOW') {
            if (t2 === 'YELLOW' || t2 === 'PURPLE' || t2 === 'RAINBOW') {
              return p0
                + Math.ceil(p1 * mag)
                + Math.ceil(p2 * mag)
                + Math.ceil(p3 * mag);
            } else {
              return p0
                + Math.ceil(p1 * mag)
                + p2
                + Math.ceil(p3 * mag); 
            }
          } else {
            if (t2 === 'YELLOW' || t2 === 'PURPLE' || t2 === 'RAINBOW') {
              return p0
                + p1
                + Math.ceil(p2 * mag)
                + Math.ceil(p3 * mag);
            } else {
              return p0
                + p1
                + p2
                + Math.ceil(p3 * mag); 
            }  
          }
        }
        return 0;
      case 'PALADIN':
        if (t0 === 'YELLOW' || t0 === 'GREEN') {
          if (t1 === 'YELLOW' || t1 === 'GREEN') {
            if (t2 === 'YELLOW' || t2 === 'GREEN') {
              return Math.ceil(p0 * mag)
                + Math.ceil(p1 * mag)
                + Math.ceil(p2 * mag)
                + Math.ceil(p3 * mag);
            } else {
              return Math.ceil(p0 * mag)
                + Math.ceil(p1 * mag)
                + p2
                + Math.ceil(p3 * mag); 
            }
          } else {
            if (t2 === 'YELLOW' || t2 === 'GREEN' || t2 === 'RAINBOW') {
              return Math.ceil(p0 * mag)
                + p1
                + Math.ceil(p2 * mag)
                + Math.ceil(p3 * mag);
            } else {
              return Math.ceil(p0 * mag)
                + p1
                + p2
                + Math.ceil(p3 * mag); 
            }  
          }
        } else {
          if (t1 === 'YELLOW' || t1 === 'GREEN' || t1 === 'RAINBOW') {
            if (t2 === 'YELLOW' || t2 === 'GREEN' || t2 === 'RAINBOW') {
              return p0
                + Math.ceil(p1 * mag)
                + Math.ceil(p2 * mag)
                + Math.ceil(p3 * mag);
            } else {
              return p0
                + Math.ceil(p1 * mag)
                + p2
                + Math.ceil(p3 * mag); 
            }
          } else {
            if (t2 === 'YELLOW' || t2 === 'GREEN' || t2 === 'RAINBOW') {
              return p0
                + p1
                + Math.ceil(p2 * mag)
                + Math.ceil(p3 * mag);
            } else {
              return p0
                + p1
                + p2
                + Math.ceil(p3 * mag); 
            }  
          }
        }
        return 0;
      case 'SUPERSTAR':
        if (t0 === 'BLUE' || t0 === 'RAINBOW') {
          if (t1 === 'GREEN' || t1 === 'RAINBOW') {
            if (t2 === 'BLUE' || t2 === 'GREEN' || t2 === 'RAINBOW') {
              return Math.ceil(p0 * mag)
                + Math.ceil(p1 * mag)
                + Math.ceil(p2 * mag)
                + Math.ceil(p3 * mag);
            } else {
              return Math.ceil(p0 * mag)
                + Math.ceil(p1 * mag)
                + p2
                + Math.ceil(p3 * mag); 
            }
          } else {
            if (t2 === 'BLUE' || t2 === 'GREEN' || t2 === 'RAINBOW') {
              return Math.ceil(p0 * mag)
                + p1
                + Math.ceil(p2 * mag)
                + Math.ceil(p3 * mag);
            } else {
              return Math.ceil(p0 * mag)
                + p1
                + p2
                + Math.ceil(p3 * mag); 
            }  
          }
        } else {
          if (t1 === 'GREEN') {
            if (t2 === 'BLUE' || t2 === 'GREEN' || t2 === 'RAINBOW') {
              return p0
                + Math.ceil(p1 * mag)
                + Math.ceil(p2 * mag)
                + Math.ceil(p3 * mag);
            } else {
              return p0
                + Math.ceil(p1 * mag)
                + p2
                + Math.ceil(p3 * mag); 
            }
          } else {
            if (t2 === 'BLUE' || t2 === 'GREEN' || t2 === 'RAINBOW') {
              return p0
                + p1
                + Math.ceil(p2 * mag)
                + Math.ceil(p3 * mag);
            } else {
              return p0
                + p1
                + p2
                + Math.ceil(p3 * mag); 
            }  
          }
        }
        return 0;
      case 'PIRATE':
        if (t0 === 'YELLOW' || t0 === 'RAINBOW') {
          if (t1 === 'BLUE' || t1 === 'RAINBOW') {
            if (t2 === 'YELLOW' || t2 === 'BLUE' || t2 === 'RAINBOW') {
              return Math.ceil(p0 * mag)
                + Math.ceil(p1 * mag)
                + Math.ceil(p2 * mag)
                + Math.ceil(p3 * mag);
            } else {
              return Math.ceil(p0 * mag)
                + Math.ceil(p1 * mag)
                + p2
                + Math.ceil(p3 * mag); 
            }
          } else {
            if (t2 === 'YELLOW' || t2 === 'BLUE' || t2 === 'RAINBOW') {
              return Math.ceil(p0 * mag)
                + p1
                + Math.ceil(p2 * mag)
                + Math.ceil(p3 * mag);
            } else {
              return Math.ceil(p0 * mag)
                + p1
                + p2
                + Math.ceil(p3 * mag); 
            }  
          }
        } else {
          if (t1 === 'BLUE' || t1 === 'RAINBOW') {
            if (t2 === 'YELLOW' || t2 === 'BLUE' || t2 === 'RAINBOW') {
              return p0
                + Math.ceil(p1 * mag)
                + Math.ceil(p2 * mag)
                + Math.ceil(p3 * mag);
            } else {
              return p0
                + Math.ceil(p1 * mag)
                + p2
                + Math.ceil(p3 * mag); 
            }
          } else {
            if (t2 === 'RED' || t2 === 'YELLOW' || t2 === 'RAINBOW') {
              return p0
                + p1
                + Math.ceil(p2 * mag)
                + Math.ceil(p3 * mag);
            } else {
              return p0
                + p1
                + p2
                + Math.ceil(p3 * mag); 
            }  
          }
        }
        return 0;
      case 'MONSTER_MASTER':
        if (t0 === 'BLUE' || t0 === 'PURPLE' || t0 === 'RAINBOW') {
          if (t1 === 'BLUE' || t1 === 'PURPLE' || t1 === 'RAINBOW') {
            return Math.ceil(p0 * mag)
              + Math.ceil(p1 * mag)
              + Math.ceil(p2 * mag)
              + Math.ceil(p3 * mag);
          } else {
            return Math.ceil(p0 * mag)
              + p1
              + Math.ceil(p2 * mag)
              + Math.ceil(p3 * mag);
          }
        } else {
          if (t1 === 'PURPLE' || t1 === 'GREEN' || t1 === 'RAINBOW') {
            return p0
              + Math.ceil(p1 * mag)
              + Math.ceil(p2 * mag)
              + Math.ceil(p3 * mag);
          } else {
            return p0
              + p1
              + Math.ceil(p2 * mag)
              + Math.ceil(p3 * mag);
          }
        }
        return 0;
      case 'GOD_HAND':
        if (t0 === 'RED' || t0 === 'RAINBOW') {
          if (t1 === 'YELLOW' || t1 === 'RAINBOW') {
            if (t2 === 'RED' || t2 === 'YELLOW' || t2 === 'RAINBOW') {
              return Math.ceil(p0 * 1.3)
                + Math.ceil(p1 * 1.3)
                + Math.ceil(p2 * 1.3)
                + Math.ceil(p3 * 1.3);
            } else {
              return Math.ceil(p0 * 1.3)
                + Math.ceil(p1 * 1.3)
                + p2
                + Math.ceil(p3 * 1.3); 
            }
          } else {
            if (t2 === 'RED' || t2 === 'YELLOW' || t2 === 'RAINBOW') {
              return Math.ceil(p0 * 1.3)
                + p1
                + Math.ceil(p2 * 1.3)
                + Math.ceil(p3 * 1.3);
            } else {
              return Math.ceil(p0 * 1.3)
                + p1
                + p2
                + Math.ceil(p3 * 1.3); 
            }  
          }
        } else {
          if (t1 === 'YELLOW' || t1 === 'RAINBOW') {
            if (t2 === 'RED' || t2 === 'YELLOW' || t2 === 'RAINBOW') {
              return p0
                + Math.ceil(p1 * 1.3)
                + Math.ceil(p2 * 1.3)
                + Math.ceil(p3 * 1.3);
            } else {
              return p0
                + Math.ceil(p1 * 1.3)
                + p2
                + Math.ceil(p3 * 1.3); 
            }
          } else {
            if (t2 === 'RED' || t2 === 'YELLOW' || t2 === 'RAINBOW') {
              return p0
                + p1
                + Math.ceil(p2 * 1.3)
                + Math.ceil(p3 * 1.3);
            } else {
              return p0
                + p1
                + p2
                + Math.ceil(p3 * 1.3); 
            }  
          }
        }
        return 0;
      case 'DAIMADOUSHI':
        if (t0 === 'YELLOW' || t0 === 'PURPLE' || t0 === 'RAINBOW') {
          if (t1 === 'PURPLE' || t1 === 'RAINBOW') {
            if (t2 === 'YELLOW' || t2 === 'PURPLE' || t2 === 'RAINBOW') {
              return Math.ceil(p0 * 1.3)
                + Math.ceil(p1 * 1.3)
                + Math.ceil(p2 * 1.3)
                + Math.ceil(p3 * 1.3);
            } else {
              return Math.ceil(p0 * 1.3)
                + Math.ceil(p1 * 1.3)
                + p2
                + Math.ceil(p3 * 1.3); 
            }
          } else {
            if (t2 === 'YELLOW' || t2 === 'PURPLE' || t2 === 'RAINBOW') {
              return Math.ceil(p0 * 1.3)
                + p1
                + Math.ceil(p2 * 1.3)
                + Math.ceil(p3 * 1.3);
            } else {
              return Math.ceil(p0 * 1.3)
                + p1
                + p2
                + Math.ceil(p3 * 1.3);
            }  
          }
        } else {
          if (t1 === 'PURPLE' || t1 === 'RAINBOW') {
            if (t2 === 'YELLOW' || t2 === 'PURPLE' || t2 === 'RAINBOW') {
              return p0
                + Math.ceil(p1 * 1.3)
                + Math.ceil(p2 * 1.3)
                + Math.ceil(p3 * 1.3);
            } else {
              return p0
                + Math.ceil(p1 * 1.3)
                + p2
                + Math.ceil(p3 * 1.3); 
            }
          } else {
            if (t2 === 'YELLOW' || t2 === 'PURPLE' || t2 === 'RAINBOW') {
              return p0
                + p1
                + Math.ceil(p2 * 1.3)
                + Math.ceil(p3 * 1.3);
            } else {
              return p0
                + p1
                + p2
                + Math.ceil(p3 * 1.3); 
            }  
          }
        }
        return 0;            
      default:
        return 0;
    }  
  }

  let max = 0;
  const ps = [p0, p1, p2, p3];
  const ts = [t0, t1, t2, t3];
  for (i = 0; i < 4; i++) {
    for (j = 0; j < 4; j++) {
      if (i != j) {
        for (k = 0; k < 4; k++) {
          if (i != k && j != k) {
            for (l = 0; l < 4; l++) {
              if (i != l && j != l && k != l) {
                const val = get(job, ps[i], ts[i], ps[j], ts[j], ps[k], ts[k], ps[l]);
                if (val > max) {
                  max = val;  
                }
              }  
            }  
          }  
        }  
      }  
    }   
  }
  return max;
""";
```

```
CREATE OR REPLACE FUNCTION `hirooka-pro.dqwapi.getMaxPattern`(job STRING, p0 FLOAT64, t0 STRING, p1 FLOAT64, t1 STRING, p2 FLOAT64, t2 STRING, p3 FLOAT64, t3 STRING)
RETURNS STRING 
LANGUAGE js AS r"""
  function get(job, p0, t0, p1, t1, p2, t2, p3) {
    const mag = 1.2;
    switch (job) {
      case 'BATTLE_MASTER':
        if (t0 === 'RED' || t0 === 'RAINBOW') {
          if (t1 === 'RED' || t1 === 'RAINBOW') {
            if (t2 === 'RED' || t2 === 'YELLOW' || t2 === 'RAINBOW') {
              return Math.ceil(p0 * mag)
                + Math.ceil(p1 * mag)
                + Math.ceil(p2 * mag)
                + Math.ceil(p3 * mag);
            } else {
              return Math.ceil(p0 * mag)
                + Math.ceil(p1 * mag)
                + p2
                + Math.ceil(p3 * mag); 
            }
          } else {
            if (t2 === 'RED' || t2 === 'YELLOW' || t2 === 'RAINBOW') {
              return Math.ceil(p0 * mag)
                + p1
                + Math.ceil(p2 * mag)
                + Math.ceil(p3 * mag);
            } else {
              return Math.ceil(p0 * mag)
                + p1
                + p2
                + Math.ceil(p3 * mag); 
            }  
          }
        } else {
          if (t1 === 'RED' || t1 === 'RAINBOW') {
            if (t2 === 'RED' || t2 === 'YELLOW' || t2 === 'RAINBOW') {
              return p0
                + Math.ceil(p1 * mag)
                + Math.ceil(p2 * mag)
                + Math.ceil(p3 * mag);
            } else {
              return p0
                + Math.ceil(p1 * mag)
                + p2
                + Math.ceil(p3 * mag); 
            }
          } else {
            if (t2 === 'RED' || t2 === 'YELLOW' || t2 === 'RAINBOW') {
              return p0
                + p1
                + Math.ceil(p2 * mag)
                + Math.ceil(p3 * mag);
            } else {
              return p0
                + p1
                + p2
                + Math.ceil(p3 * mag); 
            }  
          }
        }
        return 0;
      case 'SAGE':
        if (t0 === 'PURPLE' || t0 === 'GREEN' || t0 === 'RAINBOW') {
          if (t1 === 'PURPLE' || t1 === 'GREEN' || t1 === 'RAINBOW') {
            if (t2 === 'PURPLE' || t2 === 'GREEN' || t2 === 'RAINBOW') {
              return Math.ceil(p0 * mag)
                + Math.ceil(p1 * mag)
                + Math.ceil(p2 * mag)
                + Math.ceil(p3 * mag);
            } else {
              return Math.ceil(p0 * mag)
                + Math.ceil(p1 * mag)
                + p2
                + Math.ceil(p3 * mag); 
            }
          } else {
            if (t2 === 'PURPLE' || t2 === 'GREEN' || t2 === 'RAINBOW') {
              return Math.ceil(p0 * mag)
                + p1
                + Math.ceil(p2 * mag)
                + Math.ceil(p3 * mag);
            } else {
              return Math.ceil(p0 * mag)
                + p1
                + p2
                + Math.ceil(p3 * mag); 
            }  
          }
        } else {
          if (t1 === 'PURPLE' || t1 === 'GREEN' || t1 === 'RAINBOW') {
            if (t2 === 'PURPLE' || t2 === 'GREEN' || t2 === 'RAINBOW') {
              return p0
                + Math.ceil(p1 * mag)
                + Math.ceil(p2 * mag)
                + Math.ceil(p3 * mag);
            } else {
              return p0
                + Math.ceil(p1 * mag)
                + p2
                + Math.ceil(p3 * mag); 
            }
          } else {
            if (t2 === 'PURPLE' || t2 === 'GREEN' || t2 === 'RAINBOW') {
              return p0
                + p1
                + Math.ceil(p2 * mag)
                + Math.ceil(p3 * mag);
            } else {
              return p0
                + p1
                + p2
                + Math.ceil(p3 * mag); 
            }  
          }
        }
        return 0;
      case 'RANGER':
        if (t0 === 'BLUE' || t0 === 'RAINBOW') {
          if (t1 === 'BLUE' || t1 === 'RAINBOW') {
            if (t2 === 'RED' || t2 === 'BLUE' || t2 === 'RAINBOW') {
              return Math.ceil(p0 * mag)
                + Math.ceil(p1 * mag)
                + Math.ceil(p2 * mag)
                + Math.ceil(p3 * mag);
            } else {
              return Math.ceil(p0 * mag)
                + Math.ceil(p1 * mag)
                + p2
                + Math.ceil(p3 * mag); 
            }
          } else {
            if (t2 === 'RED' || t2 === 'BLUE' || t2 === 'RAINBOW') {
              return Math.ceil(p0 * mag)
                + p1
                + Math.ceil(p2 * mag)
                + Math.ceil(p3 * mag);
            } else {
              return Math.ceil(p0 * mag)
                + p1
                + p2
                + Math.ceil(p3 * mag); 
            }  
          }
        } else {
          if (t1 === 'BLUE' || t1 === 'RAINBOW') {
            if (t2 === 'RED' || t2 === 'BLUE' || t2 === 'RAINBOW') {
              return p0
                + Math.ceil(p1 * mag)
                + Math.ceil(p2 * mag)
                + Math.ceil(p3 * mag);
            } else {
              return p0
                + Math.ceil(p1 * mag)
                + p2
                + Math.ceil(p3 * mag); 
            }
          } else {
            if (t2 === 'RED' || t2 === 'BLUE' || t2 === 'RAINBOW') {
              return p0
                + p1
                + Math.ceil(p2 * mag)
                + Math.ceil(p3 * mag);
            } else {
              return p0
                + p1
                + p2
                + Math.ceil(p3 * mag); 
            }  
          }
        }
        return 0;
      case 'ARMAMENTALIST':
        if (t0 === 'YELLOW' || t0 === 'PURPLE' || t0 === 'RAINBOW') {
          if (t1 === 'YELLOW' || t1 === 'PURPLE' || t1 === 'RAINBOW') {
            if (t2 === 'YELLOW' || t2 === 'PURPLE' || t2 === 'RAINBOW') {
              return Math.ceil(p0 * mag)
                + Math.ceil(p1 * mag)
                + Math.ceil(p2 * mag)
                + Math.ceil(p3 * mag);
            } else {
              return Math.ceil(p0 * mag)
                + Math.ceil(p1 * mag)
                + p2
                + Math.ceil(p3 * mag); 
            }
          } else {
            if (t2 === 'YELLOW' || t2 === 'PURPLE' || t2 === 'RAINBOW') {
              return Math.ceil(p0 * mag)
                + p1
                + Math.ceil(p2 * mag)
                + Math.ceil(p3 * mag);
            } else {
              return Math.ceil(p0 * mag)
                + p1
                + p2
                + Math.ceil(p3 * mag); 
            }  
          }
        } else {
          if (t1 === 'YELLOW' || t1 === 'PURPLE' || t1 === 'RAINBOW') {
            if (t2 === 'YELLOW' || t2 === 'PURPLE' || t2 === 'RAINBOW') {
              return p0
                + Math.ceil(p1 * mag)
                + Math.ceil(p2 * mag)
                + Math.ceil(p3 * mag);
            } else {
              return p0
                + Math.ceil(p1 * mag)
                + p2
                + Math.ceil(p3 * mag); 
            }
          } else {
            if (t2 === 'YELLOW' || t2 === 'PURPLE' || t2 === 'RAINBOW') {
              return p0
                + p1
                + Math.ceil(p2 * mag)
                + Math.ceil(p3 * mag);
            } else {
              return p0
                + p1
                + p2
                + Math.ceil(p3 * mag); 
            }  
          }
        }
        return 0;
      case 'PALADIN':
        if (t0 === 'YELLOW' || t0 === 'GREEN' || t0 === 'RAINBOW') {
          if (t1 === 'YELLOW' || t1 === 'GREEN' || t1 === 'RAINBOW') {
            if (t2 === 'YELLOW' || t2 === 'GREEN' || t2 === 'RAINBOW') {
              return Math.ceil(p0 * mag)
                + Math.ceil(p1 * mag)
                + Math.ceil(p2 * mag)
                + Math.ceil(p3 * mag);
            } else {
              return Math.ceil(p0 * mag)
                + Math.ceil(p1 * mag)
                + p2
                + Math.ceil(p3 * mag); 
            }
          } else {
            if (t2 === 'YELLOW' || t2 === 'GREEN' || t2 === 'RAINBOW') {
              return Math.ceil(p0 * mag)
                + p1
                + Math.ceil(p2 * mag)
                + Math.ceil(p3 * mag);
            } else {
              return Math.ceil(p0 * mag)
                + p1
                + p2
                + Math.ceil(p3 * mag); 
            }  
          }
        } else {
          if (t1 === 'YELLOW' || t1 === 'GREEN' || t1 === 'RAINBOW') {
            if (t2 === 'YELLOW' || t2 === 'GREEN' || t2 === 'RAINBOW') {
              return p0
                + Math.ceil(p1 * mag)
                + Math.ceil(p2 * mag)
                + Math.ceil(p3 * mag);
            } else {
              return p0
                + Math.ceil(p1 * mag)
                + p2
                + Math.ceil(p3 * mag); 
            }
          } else {
            if (t2 === 'YELLOW' || t2 === 'GREEN' || t2 === 'RAINBOW') {
              return p0
                + p1
                + Math.ceil(p2 * mag)
                + Math.ceil(p3 * mag);
            } else {
              return p0
                + p1
                + p2
                + Math.ceil(p3 * mag); 
            }  
          }
        }
        return 0;
      case 'SUPERSTAR':
        if (t0 === 'BLUE' || t0 === 'RAINBOW') {
          if (t1 === 'GREEN' || t1 === 'RAINBOW') {
            if (t2 === 'BLUE' || t2 === 'GREEN' || t2 === 'RAINBOW') {
              return Math.ceil(p0 * mag)
                + Math.ceil(p1 * mag)
                + Math.ceil(p2 * mag)
                + Math.ceil(p3 * mag);
            } else {
              return Math.ceil(p0 * mag)
                + Math.ceil(p1 * mag)
                + p2
                + Math.ceil(p3 * mag); 
            }
          } else {
            if (t2 === 'BLUE' || t2 === 'GREEN' || t2 === 'RAINBOW') {
              return Math.ceil(p0 * mag)
                + p1
                + Math.ceil(p2 * mag)
                + Math.ceil(p3 * mag);
            } else {
              return Math.ceil(p0 * mag)
                + p1
                + p2
                + Math.ceil(p3 * mag); 
            }  
          }
        } else {
          if (t1 === 'GREEN' || t1 === 'RAINBOW') {
            if (t2 === 'BLUE' || t2 === 'GREEN' || t2 === 'RAINBOW') {
              return p0
                + Math.ceil(p1 * mag)
                + Math.ceil(p2 * mag)
                + Math.ceil(p3 * mag);
            } else {
              return p0
                + Math.ceil(p1 * mag)
                + p2
                + Math.ceil(p3 * mag); 
            }
          } else {
            if (t2 === 'BLUE' || t2 === 'GREEN' || t2 === 'RAINBOW') {
              return p0
                + p1
                + Math.ceil(p2 * mag)
                + Math.ceil(p3 * mag);
            } else {
              return p0
                + p1
                + p2
                + Math.ceil(p3 * mag); 
            }  
          }
        }
        return 0;
      case 'PIRATE':
        if (t0 === 'YELLOW' || t0 === 'RAINBOW') {
          if (t1 === 'BLUE' || t1 === 'RAINBOW') {
            if (t2 === 'YELLOW' || t2 === 'BLUE' || t2 === 'RAINBOW') {
              return Math.ceil(p0 * mag)
                + Math.ceil(p1 * mag)
                + Math.ceil(p2 * mag)
                + Math.ceil(p3 * mag);
            } else {
              return Math.ceil(p0 * mag)
                + Math.ceil(p1 * mag)
                + p2
                + Math.ceil(p3 * mag); 
            }
          } else {
            if (t2 === 'YELLOW' || t2 === 'BLUE' || t2 === 'RAINBOW') {
              return Math.ceil(p0 * mag)
                + p1
                + Math.ceil(p2 * mag)
                + Math.ceil(p3 * mag);
            } else {
              return Math.ceil(p0 * mag)
                + p1
                + p2
                + Math.ceil(p3 * mag); 
            }  
          }
        } else {
          if (t1 === 'BLUE' || t1 === 'RAINBOW') {
            if (t2 === 'YELLOW' || t2 === 'BLUE' || t2 === 'RAINBOW') {
              return p0
                + Math.ceil(p1 * mag)
                + Math.ceil(p2 * mag)
                + Math.ceil(p3 * mag);
            } else {
              return p0
                + Math.ceil(p1 * mag)
                + p2
                + Math.ceil(p3 * mag); 
            }
          } else {
            if (t2 === 'RED' || t2 === 'YELLOW' || t2 === 'RAINBOW') {
              return p0
                + p1
                + Math.ceil(p2 * mag)
                + Math.ceil(p3 * mag);
            } else {
              return p0
                + p1
                + p2
                + Math.ceil(p3 * mag); 
            }  
          }
        }
        return 0;
      case 'MONSTER_MASTER':
        if (t0 === 'BLUE' || t0 === 'PURPLE' || t0 === 'RAINBOW') {
          if (t1 === 'BLUE' || t1 === 'PURPLE' || t1 === 'RAINBOW') {
            return Math.ceil(p0 * mag)
              + Math.ceil(p1 * mag)
              + Math.ceil(p2 * mag)
              + Math.ceil(p3 * mag);
          } else {
            return Math.ceil(p0 * mag)
              + p1
              + Math.ceil(p2 * mag)
              + Math.ceil(p3 * mag);
          }
        } else {
          if (t1 === 'PURPLE' || t1 === 'GREEN' || t1 === 'RAINBOW') {
            return p0
              + Math.ceil(p1 * mag)
              + Math.ceil(p2 * mag)
              + Math.ceil(p3 * mag);
          } else {
            return p0
              + p1
              + Math.ceil(p2 * mag)
              + Math.ceil(p3 * mag);
          }
        }
        return 0;
      case 'GOD_HAND':
        if (t0 === 'RED' || t0 === 'RAINBOW') {
          if (t1 === 'YELLOW' || t1 === 'RAINBOW') {
            if (t2 === 'RED' || t2 === 'YELLOW' || t2 === 'RAINBOW') {
              return Math.ceil(p0 * 1.3)
                + Math.ceil(p1 * 1.3)
                + Math.ceil(p2 * 1.3)
                + Math.ceil(p3 * 1.3);
            } else {
              return Math.ceil(p0 * 1.3)
                + Math.ceil(p1 * 1.3)
                + p2
                + Math.ceil(p3 * 1.3); 
            }
          } else {
            if (t2 === 'RED' || t2 === 'YELLOW' || t2 === 'RAINBOW') {
              return Math.ceil(p0 * 1.3)
                + p1
                + Math.ceil(p2 * 1.3)
                + Math.ceil(p3 * 1.3);
            } else {
              return Math.ceil(p0 * 1.3)
                + p1
                + p2
                + Math.ceil(p3 * 1.3); 
            }  
          }
        } else {
          if (t1 === 'YELLOW' || t1 === 'RAINBOW') {
            if (t2 === 'RED' || t2 === 'YELLOW' || t2 === 'RAINBOW') {
              return p0
                + Math.ceil(p1 * 1.3)
                + Math.ceil(p2 * 1.3)
                + Math.ceil(p3 * 1.3);
            } else {
              return p0
                + Math.ceil(p1 * 1.3)
                + p2
                + Math.ceil(p3 * 1.3); 
            }
          } else {
            if (t2 === 'RED' || t2 === 'YELLOW' || t2 === 'RAINBOW') {
              return p0
                + p1
                + Math.ceil(p2 * 1.3)
                + Math.ceil(p3 * 1.3);
            } else {
              return p0
                + p1
                + p2
                + Math.ceil(p3 * 1.3); 
            }  
          }
        }
        return 0;
      case 'DAIMADOUSHI':
        if (t0 === 'YELLOW' || t0 === 'PURPLE' || t0 === 'RAINBOW') {
          if (t1 === 'PURPLE' || t1 === 'RAINBOW') {
            if (t2 === 'YELLOW' || t2 === 'PURPLE' || t2 === 'RAINBOW') {
              return Math.ceil(p0 * 1.3)
                + Math.ceil(p1 * 1.3)
                + Math.ceil(p2 * 1.3)
                + Math.ceil(p3 * 1.3);
            } else {
              return Math.ceil(p0 * 1.3)
                + Math.ceil(p1 * 1.3)
                + p2
                + Math.ceil(p3 * 1.3); 
            }
          } else {
            if (t2 === 'YELLOW' || t2 === 'PURPLE' || t2 === 'RAINBOW') {
              return Math.ceil(p0 * 1.3)
                + p1
                + Math.ceil(p2 * 1.3)
                + Math.ceil(p3 * 1.3);
            } else {
              return Math.ceil(p0 * 1.3)
                + p1
                + p2
                + Math.ceil(p3 * 1.3);
            }  
          }
        } else {
          if (t1 === 'PURPLE' || t1 === 'RAINBOW') {
            if (t2 === 'YELLOW' || t2 === 'PURPLE' || t2 === 'RAINBOW') {
              return p0
                + Math.ceil(p1 * 1.3)
                + Math.ceil(p2 * 1.3)
                + Math.ceil(p3 * 1.3);
            } else {
              return p0
                + Math.ceil(p1 * 1.3)
                + p2
                + Math.ceil(p3 * 1.3); 
            }
          } else {
            if (t2 === 'YELLOW' || t2 === 'PURPLE' || t2 === 'RAINBOW') {
              return p0
                + p1
                + Math.ceil(p2 * 1.3)
                + Math.ceil(p3 * 1.3);
            } else {
              return p0
                + p1
                + p2
                + Math.ceil(p3 * 1.3); 
            }  
          }
        }
        return 0;
      default:
        return 0;
    }  
  }

  let max = 0;
  let pattern = '';
  const ps = [p0, p1, p2, p3];
  const ts = [t0, t1, t2, t3];
  for (i = 0; i < 4; i++) {
    for (j = 0; j < 4; j++) {
      if (i != j) {
        for (k = 0; k < 4; k++) {
          if (i != k && j != k) {
            for (l = 0; l < 4; l++) {
              if (i != l && j != l && k != l) {
                let val = get(job, ps[i], ts[i], ps[j], ts[j], ps[k], ts[k], ps[l]);
                if (val > max) {
                  max = val;
                  pattern = 'p' + i + j + k + l;  
                }
              }  
            }  
          }  
        }  
      }  
    }   
  }
  return pattern;
""";
```

```
CREATE TEMP FUNCTION getMaxValue...
CREATE TEMP FUNCTION getMaxPattern...
```