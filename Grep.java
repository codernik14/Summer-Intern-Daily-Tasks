import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class Grep {
    private String[] file_data;
    private int file_length = 0;
    private void set_file_data(String[] data,int n)
    {
        file_length = n;
        file_data = new String[n];
        for(int i=0;i<n;i++)
        {
            file_data[i] = data[i];
        }
    }

    Grep(ArrayList <String> data, int n) {
        String[] data_list = new String[n];
        for(int i=0;i<n;i++)
        {
            data_list[i] = data.get(i);
        }
        set_file_data(data_list,n);
    }

    public static void main(String[] args) throws Exception
    {
        File file = new File(args[1]);
        BufferedReader br = new BufferedReader(new FileReader(file));

        String st;
        ArrayList <String> data = new ArrayList<String>();
        int count = 0;
        while ((st = br.readLine()) != null)
        {
            data.add(st.strip());
            count++;
//            System.out.println(count + " " + st.strip());
        }
        Grep grep = new Grep(data, count);
        grep.search(args[0]);
    }

    public void search(String text)
    {
        int n = text.length();
        for(int j=0;j<file_length;j++)
        {
//            System.out.println(printl"j=" + j);
            String line = file_data[j];
            int m = line.length();
            if (m>n)
            {
                ArrayList <Integer> positions = new ArrayList<Integer> ();
                for(int k=0; k< m-n;k++)
                {
                    int i = 0;
                    while(i<n && text.charAt(i) == line.charAt(k+i))
                    {
                        i++;
                    }
                    if(i==n)
                    {
                        positions.add(k);
                    }
                }
                if(!positions.isEmpty())
                {
//                    System.out.println(j+1);
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
