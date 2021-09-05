# homework
- Insert/append set of long values to pool by id
- Query values quantile by pool id

## Configuration
name|type|default|description
----|----|-------|----------|
persistence.enable|boolean|false|If ```true```, all 'add' operation will be write to ```persistence``` file. Application will try to recover pool values via this file.


## Commands
Start project:
```
$ ./mvnw spring-boot:run
```
## API
### Add values to pool
URL: /add
Method: POST
Content-Type: application/json

Sample request:
```
$ curl --location --request POST 'localhost:8080/add' \
--header 'Content-Type: application/json' \
--data-raw '{
    "poolId": 2, 
    "values": [3, 6, 7, 8, 8, 10, 13, 15, 16, 20]
}'
```
Sample response:
```
{"action": "INSERTED"}
```

### Query pool
URL: /query
Method: POST
Content-Type: application/json

Sample request:
```
$ curl --location --request POST 'localhost:8080/query' \
--header 'Content-Type: application/json' \
--data-raw '{
    "poolId": 2,
    "percentile": 50
}'

```

Sample response:
```
{"count":10,"result":9}
```
