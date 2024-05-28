#FROM anapsix/alpine-java:8_server-jre_unlimited
FROM registry.cn-shanghai.aliyuncs.com/dy_docker/java:1.8

MAINTAINER long82(rock.abstract@hotmail.com)

RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

RUN mkdir -p /home/oto/plateform

WORKDIR /home/oto/plateform

ADD ./target/ontoffline_supermall_admin-1.0.1.jar ./

EXPOSE 8088

CMD java -jar -XX:MetaspaceSize=128m -XX:MaxMetaspaceSize=128m -Xms512m -Xmx512m -Xmn128m -Xss256k -XX:SurvivorRatio=8 -XX:+UseConcMarkSweepGC ontoffline_supermall_admin-1.0.1.jar