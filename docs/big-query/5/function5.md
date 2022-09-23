```
cd src/main/resources
jq -c '.[]' kokoro-flat.json > kokoro-flat-nd.json
cs
bq -> kokoro
```

```
CREATE OR REPLACE FUNCTION `hirooka-pro.dqwapi.getMaxValue5`(job STRING, p0 FLOAT64, t0 STRING, p1 FLOAT64, t1 STRING, p2 FLOAT64, t2 STRING, p3 FLOAT64, t3 STRING, p4 FLOAT64, t4 STRING)
RETURNS INT64 
LANGUAGE js AS r"""
  function get(job, p0, t0, p1, t1, p2, t2, p3, t3, p4, t4) {
    const mag = 1.2;
    switch (job) {
      case 'GOD_HAND':
        if (t0 === 'RED' || t0 === 'RAINBOW') {
          if (t1 === 'YELLOW' || t1 === 'RAINBOW') {
            if (t2 === 'RED' || t2 === 'YELLOW' || t2 === 'RAINBOW') {
              if (t3 === 'RED' || t3 === 'YELLOW' || t3 === 'RAINBOW') {
                return Math.ceil(p0 * mag)
                  + Math.ceil(p1 * mag)
                  + Math.ceil(p2 * mag)
                  + Math.ceil(p3 * mag)
                  + Math.ceil(p4 * mag);
              } else {
                return Math.ceil(p0 * mag)
                  + Math.ceil(p1 * mag)
                  + Math.ceil(p2 * mag)
                  + p3
                  + Math.ceil(p4 * mag);
              }
            } else {
              if (t3 === 'RED' || t3 === 'YELLOW' || t3 === 'RAINBOW') {
                return Math.ceil(p0 * mag)
                  + Math.ceil(p1 * mag)
                  + p2
                  + Math.ceil(p3 * mag)
                  + Math.ceil(p4 * mag);
              } else {
                return Math.ceil(p0 * mag)
                  + Math.ceil(p1 * mag)
                  + p2
                  + p3
                  + Math.ceil(p4 * mag);
              }
            }
          } else {
            if (t2 === 'RED' || t2 === 'YELLOW' || t2 === 'RAINBOW') {
              if (t3 === 'RED' || t3 === 'YELLOW' || t3 === 'RAINBOW') {
                return Math.ceil(p0 * mag)
                  + p1
                  + Math.ceil(p2 * mag)
                  + Math.ceil(p3 * mag)
                  + Math.ceil(p4 * mag);
              } else {
                return Math.ceil(p0 * mag)
                  + p1
                  + Math.ceil(p2 * mag)
                  + p3
                  + Math.ceil(p4 * mag);
              }
            } else {
              if (t3 === 'RED' || t3 === 'YELLOW' || t3 === 'RAINBOW') {
                return Math.ceil(p0 * mag)
                  + p1
                  + p2
                  + Math.ceil(p3 * mag)
                  + Math.ceil(p4 * mag);
              } else {
                return Math.ceil(p0 * mag)
                  + p1
                  + p2
                  + p3
                  + Math.ceil(p4 * mag);
              }
            }  
          }
        } else {
          if (t1 === 'RED' || t1 === 'RAINBOW') {
            if (t2 === 'RED' || t2 === 'YELLOW' || t2 === 'RAINBOW') {
              if (t3 === 'RED' || t3 === 'YELLOW' || t3 === 'RAINBOW') {
                return p0
                  + Math.ceil(p1 * mag)
                  + Math.ceil(p2 * mag)
                  + Math.ceil(p3 * mag)
                  + Math.ceil(p4 * mag);
              } else {
                return p0
                  + Math.ceil(p1 * mag)
                  + Math.ceil(p2 * mag)
                  + p3
                  + Math.ceil(p4 * mag);
              }
            } else {
              if (t3 === 'RED' || t3 === 'YELLOW' || t3 === 'RAINBOW') {
                return p0
                  + Math.ceil(p1 * mag)
                  + p2
                  + Math.ceil(p3 * mag)
                  + Math.ceil(p4 * mag);
              } else {
                return p0
                  + Math.ceil(p1 * mag)
                  + p2
                  + p3
                  + Math.ceil(p4 * mag);
              } 
            }
          } else {
            if (t2 === 'RED' || t2 === 'YELLOW' || t2 === 'RAINBOW') {
              if (t3 === 'RED' || t3 === 'YELLOW' || t3 === 'RAINBOW') {
                return p0
                  + p1
                  + Math.ceil(p2 * mag)
                  + Math.ceil(p3 * mag)
                  + Math.ceil(p4 * mag);
              } else {
                return p0
                  + p1
                  + Math.ceil(p2 * mag)
                  + p3
                  + Math.ceil(p4 * mag);
              }
            } else {
              if (t3 === 'RED' || t3 === 'YELLOW' || t3 === 'RAINBOW') {
                return p0
                  + p1
                  + p2
                  + Math.ceil(p3 * mag)
                  + Math.ceil(p4 * mag);
              } else {
                return p0
                  + p1
                  + p2
                  + p3
                  + Math.ceil(p4 * mag);
              }
            }  
          }
        }
        return 0;
      default:
        return 0;
    }  
  }

  let max = 0;
  const ps = [p0, p1, p2, p3, p4];
  const ts = [t0, t1, t2, t3, t4];
  for (i = 0; i < 5; i++) {
    for (j = 0; j < 5; j++) {
      if (i != j) {
        for (k = 0; k < 5; k++) {
          if (i != k && j != k) {
            for (l = 0; l < 5; l++) {
              if (i != l && j != l && k != l) {
                for (m = 0; m < 5; m++) {
                  if (i != m && j != m && k != m && l != m) {
                    const val = get(job, ps[i], ts[i], ps[j], ts[j], ps[k], ts[k], ps[l], ts[l], ps[m], ts[m]);
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
    }   
  }
  return max;
""";
```

```
CREATE OR REPLACE FUNCTION `hirooka-pro.dqwapi.getMaxPattern5`(job STRING, p0 FLOAT64, t0 STRING, p1 FLOAT64, t1 STRING, p2 FLOAT64, t2 STRING, p3 FLOAT64, t3 STRING, p4 FLOAT64, t4 STRING)
RETURNS STRING 
LANGUAGE js AS r"""
  function get(job, p0, t0, p1, t1, p2, t2, p3, t3, p4, t4) {
    const mag = 1.2;
    switch (job) {
      case 'GOD_HAND':
        if (t0 === 'RED' || t0 === 'RAINBOW') {
          if (t1 === 'YELLOW' || t1 === 'RAINBOW') {
            if (t2 === 'RED' || t2 === 'YELLOW' || t2 === 'RAINBOW') {
              if (t3 === 'RED' || t3 === 'YELLOW' || t3 === 'RAINBOW') {
                return Math.ceil(p0 * mag)
                  + Math.ceil(p1 * mag)
                  + Math.ceil(p2 * mag)
                  + Math.ceil(p3 * mag)
                  + Math.ceil(p4 * mag);
              } else {
                return Math.ceil(p0 * mag)
                  + Math.ceil(p1 * mag)
                  + Math.ceil(p2 * mag)
                  + p3
                  + Math.ceil(p4 * mag);
              }
            } else {
              if (t3 === 'RED' || t3 === 'YELLOW' || t3 === 'RAINBOW') {
                return Math.ceil(p0 * mag)
                  + Math.ceil(p1 * mag)
                  + p2
                  + Math.ceil(p3 * mag)
                  + Math.ceil(p4 * mag);
              } else {
                return Math.ceil(p0 * mag)
                  + Math.ceil(p1 * mag)
                  + p2
                  + p3
                  + Math.ceil(p4 * mag);
              }
            }
          } else {
            if (t2 === 'RED' || t2 === 'YELLOW' || t2 === 'RAINBOW') {
              if (t3 === 'RED' || t3 === 'YELLOW' || t3 === 'RAINBOW') {
                return Math.ceil(p0 * mag)
                  + p1
                  + Math.ceil(p2 * mag)
                  + Math.ceil(p3 * mag)
                  + Math.ceil(p4 * mag);
              } else {
                return Math.ceil(p0 * mag)
                  + p1
                  + Math.ceil(p2 * mag)
                  + p3
                  + Math.ceil(p4 * mag);
              }
            } else {
              if (t3 === 'RED' || t3 === 'YELLOW' || t3 === 'RAINBOW') {
                return Math.ceil(p0 * mag)
                  + p1
                  + p2
                  + Math.ceil(p3 * mag)
                  + Math.ceil(p4 * mag);
              } else {
                return Math.ceil(p0 * mag)
                  + p1
                  + p2
                  + p3
                  + Math.ceil(p4 * mag);
              }
            }  
          }
        } else {
          if (t1 === 'RED' || t1 === 'RAINBOW') {
            if (t2 === 'RED' || t2 === 'YELLOW' || t2 === 'RAINBOW') {
              if (t3 === 'RED' || t3 === 'YELLOW' || t3 === 'RAINBOW') {
                return p0
                  + Math.ceil(p1 * mag)
                  + Math.ceil(p2 * mag)
                  + Math.ceil(p3 * mag)
                  + Math.ceil(p4 * mag);
              } else {
                return p0
                  + Math.ceil(p1 * mag)
                  + Math.ceil(p2 * mag)
                  + p3
                  + Math.ceil(p4 * mag);
              }
            } else {
              if (t3 === 'RED' || t3 === 'YELLOW' || t3 === 'RAINBOW') {
                return p0
                  + Math.ceil(p1 * mag)
                  + p2
                  + Math.ceil(p3 * mag)
                  + Math.ceil(p4 * mag);
              } else {
                return p0
                  + Math.ceil(p1 * mag)
                  + p2
                  + p3
                  + Math.ceil(p4 * mag);
              } 
            }
          } else {
            if (t2 === 'RED' || t2 === 'YELLOW' || t2 === 'RAINBOW') {
              if (t3 === 'RED' || t3 === 'YELLOW' || t3 === 'RAINBOW') {
                return p0
                  + p1
                  + Math.ceil(p2 * mag)
                  + Math.ceil(p3 * mag)
                  + Math.ceil(p4 * mag);
              } else {
                return p0
                  + p1
                  + Math.ceil(p2 * mag)
                  + p3
                  + Math.ceil(p4 * mag);
              }
            } else {
              if (t3 === 'RED' || t3 === 'YELLOW' || t3 === 'RAINBOW') {
                return p0
                  + p1
                  + p2
                  + Math.ceil(p3 * mag)
                  + Math.ceil(p4 * mag);
              } else {
                return p0
                  + p1
                  + p2
                  + p3
                  + Math.ceil(p4 * mag);
              }
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
  const ps = [p0, p1, p2, p3, p4];
  const ts = [t0, t1, t2, t3, t4];
  for (i = 0; i < 5; i++) {
    for (j = 0; j < 5; j++) {
      if (i != j) {
        for (k = 0; k < 5; k++) {
          if (i != k && j != k) {
            for (l = 0; l < 5; l++) {
              if (i != l && j != l && k != l) {
                for (m = 0; m < 5; m++) {
                  if (i != m && j != m && k != m && l != m) {
                    const val = get(job, ps[i], ts[i], ps[j], ts[j], ps[k], ts[k], ps[l], ts[l], ps[m], ts[m]);
                    if (val > max) {
                      max = val;
                      pattern = 'p' + i + j + k + l + m;  
                    }
                  }
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