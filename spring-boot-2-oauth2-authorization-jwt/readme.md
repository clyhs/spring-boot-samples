First we must generate a KeyStore file. To do that execute the following command:
```
keytool -genkeypair -alias jwt -keyalg RSA -keypass password -keystore jwt.jks -storepass password
```
(if you're under Windows go your Java install dir and there you'll find a jar named keytool)

The command will generate a file called jwt.jks which contains the Public and Private keys.

It is recommended to migrate to PKCS12. To do that execute the following command:
```
keytool -importkeystore -srckeystore jwt.jks -destkeystore jwt.jks -deststoretype pkcs12
```
Now let's export the public key:
```
keytool -list -rfc --keystore jwt.jks | openssl x509 -inform pem -pubkey
```
Copy the jwt.jks file to the *Resources* folder.

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


