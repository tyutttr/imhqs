# imhqs

mental_health_quiz 部署文档

创建一个存放`Maven仓库`的卷,使得 Maven 的依赖被缓存，可加快构建速度。

```
D:\Design25\imhqs>docker volume create --name imhqs-maven-repo
imhqs-maven-repo
```

```
docker run -it --rm --name imhqs-maven \
    -v imhqs-maven-repo:/root/.m2 \
    -v "$PWD/src/imhqs-server":/usr/src/mymaven \
    -w /usr/src/mymaven \
    maven:3.8.4-jdk-8 mvn clean install package -e -Dmaven.test.skip=true
```

```
docker compose up -d
```

```
admin/admin123 密码
```
