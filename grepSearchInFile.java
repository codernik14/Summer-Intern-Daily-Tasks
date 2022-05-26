import java.io.*;
import java.util.ArrayList;

public class grepSearchInFile {
    private String[] file_data;
    private int file_length = 0;
    private String thisfilename, thisfilepath;
    grepSearchInFile(File file) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));
        String st;
        ArrayList <String> data = new ArrayList<String>();
        thisfilename = file.getName();
        thisfilepath = file.getPath();
        while ((st = br.readLine()) != null)
        {
            data.add(st.strip());
            file_length++;
//            System.out.println(count + " " + st.strip());
        }
        file_data = new String[file_length];
        for(int i=0;i<file_length;i++)
        {
            file_data[i] = data.get(i);
        }
        br.close();
    }

    public void search(String text)
    {
        int n = text.length();
        for(int j=0;j<file_length;j++)
        {
            String line = file_data[j];
            int m = line.length();
            if (m>n)
            {
                ArrayList <Integer> positions = new ArrayList<Integer> ();
                for(int k=0; k< m-n;k++)
                {
                    int i = 0;
                    int pos = k+1;
                    while(i<n && text.charAt(i) == line.charAt(k))
                    {
                        i++;
                        k++;
                    }
                    if(i==n)
                    {
                        k--;
                        positions.add(pos);
                    }
                }
                if(!positions.isEmpty())
                {
                    System.out.println("In file " + thisfilepath);
                    System.out.println("Match in Line " + (int)(j+1) + ": " + line);
                    System.out.print("Matches found on positions: ");
                    for(int i = 0;i<positions.size();i++)
                    {
                        System.out.print(positions.get(i) + " ");
                    }
                    System.out.println("\n");
                }
            }
        }
    }
}
