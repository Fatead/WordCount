import java.io.*;

/**
 * Created by zero on 2018/3/17.
 */
public class WordCount
{
    static int r = 0;
    static int lineCount = 0;                //统计代码行数
    static int charCount = 0;                //统计字符个数
    static int wordCount = 0;                //统计单词个数
    static int wordCount2 = 0;                //统计单词个数
    static char Char;
    static char SChar;
    static char[] text = new char[1000];
    static char[] stop = new char[1000];
    public static boolean a =false;
    public static boolean c =false;
    public static boolean l =false;
    public static boolean w =false;
    public static boolean o =false;
    public static boolean e =false;
    public static boolean change =false;
    public static boolean sp =false;
    public static String message = "";
    public static char[] charMessage;
    public static String [] stopArray = new String [200];
    public static String [] wordArray = new String [200];


    public static boolean isSpace()       //判断是否是空格
    {
        if(Char==' ')
            return true;
        else
            return false;
    }

    public static boolean isComma()       //判断是否是逗号
    {
        if(Char==',')
            return true;
        else
            return false;
    }

    public static boolean isEnter()        //判断是否是回车
    {
        if(Char=='\n'||Char=='\r')
            return true;
        else
            return false;
    }
    public static void Reader(String filename,char[] array)
    {
        try{
            FileReader in = new FileReader(filename);
            in.read(array);
            in.close();

        }
        catch (IOException e){

        }
    }

    public static void main(String[] args)           //程序从控制台接收数据
    {
        try
        {
            String filename = "";                         //文件名
            String outfile = "result.txt";                         //输出信息文件名
            String stoplist = "";
            int i = 0;
            int num = 0;
            int length = args.length;
            while(i<length)
            {
                if(args[i].equals("-a"))
                {
                    a = true;
                    i++;
                }
                else if(args[i].equals("-o"))
                {
                    o = true;
                    i++;
                    outfile =args[i];
                    if((i+1) == args.length)
                    {
                        break;
                    }
                    i++;
                }
                else if(args[i].equals("-w"))
                {
                    w = true;
                    i++;
                }
                else if(args[i].equals("-c"))
                {
                    c = true;
                    i++;
                }
                else if(args[i].equals("-l"))
                {
                    l = true;
                    i++;
                }

                else if(args[i].equals("-e"))
                {
                    e = true;
                    i++;
                    stoplist=args[i];
                    if((i+1) == args.length)
                    {
                        break;
                    }
                    i++;
                }
                else if(args[i].charAt(0)!='-')
                {
                    filename = args[i];
                    i++;
                }
            }

            Reader(filename,text);
            for(int h=0;h<200;h++)
            {
                stopArray[h] = "";
            }

            if(e)
            {
                Reader(stoplist,stop);
                int j = 0;
                while(j<stop.length)
                {
                    SChar = stop[j];
                    while(SChar==' '||SChar==','||SChar=='\r')
                    {
                        j++;
                        SChar = stop[j];
                        change = true;
                    }
                    if(change)
                    {
                        num++;
                        change = false;
                    }
                    stopArray[num] = stopArray[num]+stop[j];
                    j++;
                }
            }

            do
            {
                Process();
            }while(Char!='\0');
            charCount--;
            charCount = charCount-2*lineCount;
            String temp = "";
            String temp2 = "";
            if(e)
            {
                for(int m = 0;m<wordCount;m++)
                {
                    for(int n = 0;n<num;n++)
                    {
                        temp = stopArray[n];
                        temp2 = wordArray[m];
                        if(temp.equals(temp2))
                        {
                            sp=true;
                        }

                    }
                    if(!sp)
                    {
                        wordCount2++;
                    }
                    sp=false;
                }
            }

            if(w)
            {
                message =message+ "单词数是";
                if(e)
                {
                    message = message +wordCount2;
                }
                else
                {
                    message = message +wordCount;
                }
                message = message +"\r\n";
            }
            if(c)
            {
                message =message+ "字符数是";
                message = message +charCount;
                message = message +"\r\n";
            }
            if(l)
            {
                message =message+ "行数是";
                message = message +lineCount;
                message = message +"\r\n";
            }
            System.out.println(outfile);
            File file = new File(outfile);
            FileWriter out = new FileWriter(outfile);
            charMessage = message.toCharArray();
            out.write(charMessage);
            out.close();
        }
        catch(IOException e)
        {
            System.out.println("File read error" + e);
        }
    }
    public static void GetChar()            //从源程序中提取字符
    {
        Char = text[r];
        if(Char=='\n')
        {
            lineCount++;
        }
        charCount++;
        if(Char!='\0')
            r++;
    }


    public static void Process()               //处理函数
    {
        String temp = "";
        while(!(isComma()||isSpace()||isEnter()))
        {
            if(Char!=' ')
                temp = temp + Char;
            GetChar();
            if(Char=='\0')
                break;

        }
        wordArray[wordCount] = temp;
        wordCount++;

    /*    if(isEnter())
        {
            if(text[r+1]!=' ')
            {
                wordCount++;
            }
        }
        else
        {
            wordCount++;
        }  */
        while(isComma()||isSpace()||isEnter())
        {
            GetChar();
            if(Char=='\0')
                break;
        }
    }
};
