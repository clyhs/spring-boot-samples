first. We must generate a KeyStore file
```
keytool -genkeypair -alias jwt -keyalg RSA -keypass password -keystore jwt.jks -storepass password
```
Now let's export the public key:
```
keytool -list -rfc --keystore jwt.jks | openssl x509 -inform pem -pubkey
```

User HTTPie

install python
pip install --upgrade pip setuptools
pip install --upgrade httpie

To get a JWT token use the following command (webapp = the name of the OAuth2 client):
```
http --form POST webapp:@localhost:9999/oauth/token grant_type=password username=admin password=password --ignore-stdin

```

To access a resource use:
```
http resource:10000/resource/users 'Authorization: Bearer '$TOKEN
```
To use the refresh token functionality:

```
http --form POST webapp:@auth:9999/oauth/token 'Authorization: Bearer '$TOKEN grant_type=refresh_token refresh_token=$REFRESH_TOKEN

```