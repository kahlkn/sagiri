nohup java -jar sagiri.jar --spring.profiles.active=test --spring.datasource.url="jdbc:mysql://127.0.0.1:3306/sagiri?useUnicode=true&characterEncoding=utf8&useSSL=false" --spring.datasource.username=root --spring.datasource.password=root --logging.home=/tmp/logs/ >> nohup.out 2>&1 &


