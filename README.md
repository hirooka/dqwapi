# dqwapi

API that suggests the optimal combination of kokoro for advanced jobs of DRAGON QUEST WALK.

## Prerequisites

- Java 17
- BigQuery, Cloud Storage, Cloud Functions (Google Cloud)

## Getting Started

The easiest way to run application is to use Docker.

Swagger UI is

```
http://localhost:8888/api.html
```

## Supported Kokoro

| Type | Grade |
|--------|--------|
| メガモンスター | S, A |
| ほこら(上級職推奨レベル60以上) | S, A |
| ほこら(上級職推奨レベル60未満) | S |
| 強敵 | S |
| めったに見かけない | S, A, B |
| あまり見かけない | S, A, B |
| ときどき見かける | S |
| よく見かける | Unsupported |
| とてもよく見かける | Unsupported |
