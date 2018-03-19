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
    static char Char;
    static char[] text = new char[1000];
    public static boolean c =false;
    public static boolean l =false;
    public static boolean w =false;
    public static boolean o =false;
    public static String message = "";
    public static char[] charMessage;


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
        if(Char=='\n')
            return true;
        else
            return false;
    }
    public static void Reader(String filename)
    {
        try{
            FileReader in = new FileReader(filename);
            in.read(text);
            in.close();

        }
        catch (IOException e){

        }
    }

    public static void main(String[] args)
    {
        try
        {
            String filename = "";                         //文件名
            String outfile = "";                         //文件名
            int i = 0;
            int length = args.length;
            while(i<length)
            {
                if(args[i].equals("-w"))
                {
                    w = true;
                    i++;
                }
                if(args[i].equals("-o"))
                {
                    o = true;
                    i++;
                    outfile =args[i];
                    i++;
                }
                if(args[i].equals("-l"))
                {
                    l = true;
                    i++;
                }
                if(args[i].equals("-c"))
                {
                    w = true;
                    i++;
                }
                if(args[i].charAt(0)!='-')
                {
                    filename = args[i];
                    i++;
                }
            }

            Reader(filename);

            do
            {
                Process();
            }while(Char!='\0');
            charCount--;
            charCount = charCount-2*lineCount;
            if(w==true)
            {
                message =message+ "单词数是";
                message = message +wordCount;
                message = message +"\r\n";
            }
            if(c==true)
            {
                message =message+ "字符数是";
                message = message +wordCount;
                message = message +"\r\n";
            }
            if(l==true)
            {
                message =message+ "行数是";
                message = message +lineCount;
                message = message +"\r\n";
            }
            File file = new File("result.txt");
            FileWriter out = new FileWriter("result.txt");
            if(o==true)
            {
                out = new FileWriter(outfile);
            }
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
        while(!(isComma()||isSpace()||isEnter()))
        {
            GetChar();
        }
        if(isEnter())
        {
            if(text[r]!=' ')
            {
                wordCount++;
            }
        }
        else
        {
            wordCount++;
        }
        while(isComma()||isSpace()||isEnter())
        {
            GetChar();
        }
    }
};
