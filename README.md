# two-way-ssl-spring-boot-server

# hello end point url : https://localhost:9001/two-way-ssl-client/api/v1/hello-client

# Ref : https://medium.com/@niral22/2-way-ssl-with-spring-boot-microservices-2c97c974e83

# Create Self Signed Server Cert: keytool -genkeypair -alias two-way-ssl-spring-boot-client -keyalg RSA -keysize 2048 -storetype JKS -keystore ssl-client.jks -validity 3650 -ext SAN=dns:localhost,ip:127.0.0.1