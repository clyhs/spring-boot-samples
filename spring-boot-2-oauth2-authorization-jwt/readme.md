### curl
```
curl adminapp:password@localhost:9999/oauth/token -d grant_type=password -d username=admin -d password=password
```

{"access_token":...}

```
curl http://localhost:8080/users -H "Authorization: Bearer $token"
```

```
curl adminapp:password@localhost:9999/oauth/token -d grant_type=refresh_token -d refresh_token=$refresh_token
```

### httpie

```
http --form POST adminapp:password@localhost:9999/oauth/token grant_type=password username=admin password=password  --ignore-stdin

http localhost:8080/users Authorization:Bearer $token

http --form POST adminapp:password@localhost:9999/oauth/token grant_type=refresh_token refresh_token=$REFRESH_TOKEN
```
