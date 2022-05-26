import java.io.*;
import java.util.ArrayList;

public class grepSearchRecursive {

    private ArrayList<String> filenames = new ArrayList<String>();
    public void listOfFiles(File dirPath)
    {
        File filesList[] = dirPath.listFiles();
        for(File file: filesList)
        {
            if(file.isFile())
            {
                filenames.add(file.getPath());
            }
            else if(file.isDirectory())
            {
                listOfFiles(file);
            }
        }
    }

    public static void main(String[] args) throws IOException{
        String st = args[0];
        File path = new File(args[1]);
        grepSearchRecursive grep = new grepSearchRecursive();
        try
        {
            if(path.isDirectory())
            {
//                System.out.println(path.isDirectory());
                grep.listOfFiles(path);
            }
            else if (path.isFile())
            {
                grep.filenames.add(path.getPath());
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        finally {
            for(String file: grep.filenames)
            {
                File file1 = new File(file);
                grepSearchInFile thisFile = new grepSearchInFile(file1);
                thisFile.search(st);
            }
        }
    }
}
