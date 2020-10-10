#!/bin/bash
#export BUILD_ID=dontKillMe这一句很重要，这样指定了，项目启动之后才不会被Jenkins杀掉。
export BUILD_ID=dontKillMe

# 防止rm 时候参数变量为空
cd /tmp/

#最终打包安装的jar包库路径，包括了依赖其他快照jar包(依赖的jar应该不需要删除)
#mvn_jar_path=/mvn/libspace/com/middol/


#先进入target目录
cd $WORKSPACE/target

#获取Jenkins中编译好的jar名称，其中XXX为你的pom文件中的artifactId的值，这一步主要是为了根据项目版本号动态获取项目文件名
jar_name=`ls |grep aspirin-|grep -v original`


#获取运行编译好的进程ID，便于我们在重新部署项目的时候先杀掉以前的进程,需要换成你自己的地址,并建好run.pid文件
#注意，如果你的服务器有多个项目，要保证每个项目的run.pid文件地址不一样，否则会杀掉其他项目的进程
pid=$(cat $WORKSPACE/run.pid)

if [! -z "$pid"]; then
#杀掉以前可能启动的项目进程
kill -9 ${pid}
fi

#启动jar，指定SpringBoot的profiles为dev,后台启动
nohup java -jar ${jar_name} >/dev/null 2>&1 &


#将进程ID存入到run.pid文件中，需要换成你自己的地址
echo $! > $WORKSPACE/run.pid
