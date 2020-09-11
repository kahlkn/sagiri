echo "stopping..."
pid=`ps -ef | grep sagiri.jar | grep -v grep | awk '{print $2}'`
if [ -n "$pid" ]; then
    echo "kill ${pid}."
    kill $pid
else
    echo "do nothing."
fi

