for file in `find . -name "*.jar"`; do
echo "$CLASSPATH:$file"; done
	#echo "$CLASSPATH:`realpath $file`"; done
