FROM registry.cn-shanghai.aliyuncs.com/dy_docker/java:1.8

MAINTAINER long82(rock.abstract@hotmail.com)

RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

RUN mkdir -p /home/oto/mobile

WORKDIR /home/oto/mobile

ADD ./target/ontoffline_supermall_api-1.0.1.jar ./

EXPOSE 443

CMD java -jar -XX:MetaspaceSize=128m -XX:MaxMetaspaceSize=128m -Xms1024m -Xmx1024m -Xmn256m -Xss256k -XX:SurvivorRatio=8 -XX:+UseConcMarkSweepGC ontoffline_supermall_api-1.0.1.jar