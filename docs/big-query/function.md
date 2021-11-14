```
jq -c '.[]' kokoro-flat.json > kokoro-flat-nd.json
```

```
CREATE OR REPLACE FUNCTION `hirooka-pro.dqwapi.getMaxValue`(job STRING, p0 FLOAT64, t0 STRING, p1 FLOAT64, t1 STRING, p2 FLOAT64, t2 STRING, p3 FLOAT64, t3 STRING)
RETURNS INT64 
LANGUAGE js AS r"""
  function get(job, p0, t0, p1, t1, p2, t2, p3) {
    const mag = 1.2;
    switch (job) {
      case 'BATTLE_MASTER':
        if (t0 === 'RED') {
          if (t1 === 'RED') {
            if (t2 === 'RED' || t2 === 'YELLOW') {
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
            if (t2 === 'RED' || t2 === 'YELLOW') {
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
          if (t1 === 'RED') {
            if (t2 === 'RED' || t2 === 'YELLOW') {
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
            if (t2 === 'RED' || t2 === 'YELLOW') {
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
        if (t0 === 'PURPLE' || t0 === 'GREEN') {
          if (t1 === 'PURPLE' || t1 === 'GREEN') {
            if (t2 === 'PURPLE' || t2 === 'GREEN') {
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
            if (t2 === 'PURPLE' || t2 === 'GREEN') {
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
          if (t1 === 'PURPLE' || t1 === 'GREEN') {
            if (t2 === 'PURPLE' || t2 === 'GREEN') {
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
            if (t2 === 'PURPLE' || t2 === 'GREEN') {
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
        if (t0 === 'BLUE') {
          if (t1 === 'BLUE') {
            if (t2 === 'RED' || t2 === 'BLUE') {
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
            if (t2 === 'RED' || t2 === 'BLUE') {
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
          if (t1 === 'BLUE') {
            if (t2 === 'RED' || t2 === 'BLUE') {
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
            if (t2 === 'RED' || t2 === 'BLUE') {
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
        if (t0 === 'YELLOW' || t0 === 'PURPLE') {
          if (t1 === 'YELLOW' || t1 === 'PURPLE') {
            if (t2 === 'YELLOW' || t2 === 'PURPLE') {
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
            if (t2 === 'YELLOW' || t2 === 'PURPLE') {
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
          if (t1 === 'YELLOW' || t1 === 'PURPLE') {
            if (t2 === 'YELLOW' || t2 === 'PURPLE') {
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
            if (t2 === 'YELLOW' || t2 === 'PURPLE') {
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
            if (t2 === 'YELLOW' || t2 === 'GREEN') {
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
          if (t1 === 'YELLOW' || t1 === 'GREEN') {
            if (t2 === 'YELLOW' || t2 === 'GREEN') {
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
            if (t2 === 'YELLOW' || t2 === 'GREEN') {
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
        if (t0 === 'BLUE') {
          if (t1 === 'GREEN') {
            if (t2 === 'BLUE' || t2 === 'GREEN') {
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
            if (t2 === 'BLUE' || t2 === 'GREEN') {
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
            if (t2 === 'BLUE' || t2 === 'GREEN') {
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
            if (t2 === 'BLUE' || t2 === 'GREEN') {
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
        if (t0 === 'YELLOW') {
          if (t1 === 'BLUE') {
            if (t2 === 'YELLOW' || t2 === 'BLUE') {
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
            if (t2 === 'YELLOW' || t2 === 'BLUE') {
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
          if (t1 === 'BLUE') {
            if (t2 === 'YELLOW' || t2 === 'BLUE') {
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
            if (t2 === 'RED' || t2 === 'YELLOW') {
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
                const val = get(job, ps[0], ts[0], ps[1], ts[1], ps[2], ts[2], ps[3]);
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
        if (t0 === 'RED') {
          if (t1 === 'RED') {
            if (t2 === 'RED' || t2 === 'YELLOW') {
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
            if (t2 === 'RED' || t2 === 'YELLOW') {
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
          if (t1 === 'RED') {
            if (t2 === 'RED' || t2 === 'YELLOW') {
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
            if (t2 === 'RED' || t2 === 'YELLOW') {
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
        if (t0 === 'PURPLE' || t0 === 'GREEN') {
          if (t1 === 'PURPLE' || t1 === 'GREEN') {
            if (t2 === 'PURPLE' || t2 === 'GREEN') {
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
            if (t2 === 'PURPLE' || t2 === 'GREEN') {
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
          if (t1 === 'PURPLE' || t1 === 'GREEN') {
            if (t2 === 'PURPLE' || t2 === 'GREEN') {
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
            if (t2 === 'PURPLE' || t2 === 'GREEN') {
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
        if (t0 === 'BLUE') {
          if (t1 === 'BLUE') {
            if (t2 === 'RED' || t2 === 'BLUE') {
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
            if (t2 === 'RED' || t2 === 'BLUE') {
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
          if (t1 === 'BLUE') {
            if (t2 === 'RED' || t2 === 'BLUE') {
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
            if (t2 === 'RED' || t2 === 'BLUE') {
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
        if (t0 === 'YELLOW' || t0 === 'PURPLE') {
          if (t1 === 'YELLOW' || t1 === 'PURPLE') {
            if (t2 === 'YELLOW' || t2 === 'PURPLE') {
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
            if (t2 === 'YELLOW' || t2 === 'PURPLE') {
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
          if (t1 === 'YELLOW' || t1 === 'PURPLE') {
            if (t2 === 'YELLOW' || t2 === 'PURPLE') {
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
            if (t2 === 'YELLOW' || t2 === 'PURPLE') {
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
            if (t2 === 'YELLOW' || t2 === 'GREEN') {
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
          if (t1 === 'YELLOW' || t1 === 'GREEN') {
            if (t2 === 'YELLOW' || t2 === 'GREEN') {
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
            if (t2 === 'YELLOW' || t2 === 'GREEN') {
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
        if (t0 === 'BLUE') {
          if (t1 === 'GREEN') {
            if (t2 === 'BLUE' || t2 === 'GREEN') {
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
            if (t2 === 'BLUE' || t2 === 'GREEN') {
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
            if (t2 === 'BLUE' || t2 === 'GREEN') {
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
            if (t2 === 'BLUE' || t2 === 'GREEN') {
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
        if (t0 === 'YELLOW') {
          if (t1 === 'BLUE') {
            if (t2 === 'YELLOW' || t2 === 'BLUE') {
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
            if (t2 === 'YELLOW' || t2 === 'BLUE') {
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
          if (t1 === 'BLUE') {
            if (t2 === 'YELLOW' || t2 === 'BLUE') {
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
            if (t2 === 'RED' || t2 === 'YELLOW') {
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