wc.exe -c test1.c -o result1.txt
wc.exe -w -l test2.c -o result2.txt
wc.exe -a test3.c -o result3.txt
wc.exe -w test4.c ¨Ce stoplist.txt -o result4.txt
wc.exe -a test5.c -o result5.txt
wc.exe -a test6.c -o result6.txt
wc.exe -a test7.c -o result7.txt
wc.exe -w test8.c -e stoplist2.txt -o result8.txt
wc.exe ¨Cw test9.c ¨Co result9.c
wc.exe -w -l -c -s *.c -o result10.txt