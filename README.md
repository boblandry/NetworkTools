# NetworkTools
##目的
为了测试路由器和本机之间的网络稳定性，确认网络不稳定产生的原因。<br>
默认路由器地址为192.168.1.1。需要更改，请直接修改代码。后期会改成参数配置。<br>
##使用方法（Windows）
打包成jar包后，可以手动运行。或者使用run.bat执行。<br>
开机后台运行方法：添加run.vbs脚本到 windows启动目录即可。<br>
执行ping命令20次，当最大延迟超过20ms，或者平时延迟超过10ms，或者产生丢包，就会写入日志文件。<br>
目前日志文件路径已经写死，后期会改。<br>
