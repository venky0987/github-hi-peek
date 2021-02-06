import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;

class doc implements Comparator<String> {
    Map<String, Integer> base;
    public doc(Map<String, Integer> base) {
        this.base = base;
    }
    public int compare(String a, String b) {
        if (base.get(a) <= base.get(b)) {
            return -1;
        } else {
            return 1;
        }
    }
}

public class Main {

    // static void foo(int[] a, int noofEmp) {
    //     Arrays.sort(a);
    //     System.out.println(Arrays.toString(a));
    //     int minDiff = 229900;
    //     int range = noofEmp - 1;
    //     int arrlen = a.length - range;
    //     int item1 = 0, item2 = 0;
    //     for (int i = 0; i < arrlen; i++) {
    //         int x = a[i];
    //         int y = a[noofEmp - 1];
    //         int v = Math.abs(x - y);
    //         if (v < minDiff) {
    //             minDiff = v;
    //             item1 = i;
    //             item2 = noofEmp - 1;
    //         }
    //         noofEmp++;
    //     }
    //     System.out.println("Number of employees: " + noofEmp);
    //     System.out.println();
    //     System.out.println("Here the goodies that are selected for distribution are:");
    //     for (int i = item1; i <= item2; i++) {
    //         System.out.println(a[i]);
    //     }
    //     System.out.println();
    //     System.out.println("And the difference between the chosen goodie with highest price and the lowest price is " +minDiff);
    // }

    public static void main(String[] args) throws IOException {

        HashMap<String, Integer> data = new HashMap<String, Integer>();
        doc d = new doc(data);
        TreeMap<String, Integer> smap = new TreeMap<String, Integer>(d);
        
        FileReader f = new FileReader("sample_input.txt");
        BufferedReader br = new BufferedReader(f);
        String s = br.readLine();
        int emps = 0;
        int c = 0;
        while (s != null) {
            if(c == 0) {
                System.out.println("emps");
                String[]  empstmp = s.split(": ");
                emps = Integer.parseInt(empstmp[1]);
            }
            else if (c > 3 ){
                String s1 = s;
                String[]  s2 = s1.split(": ");
                data.put(s2[0], Integer.parseInt(s2[1]));
            }
            s = br.readLine();
            c++;
        }
        System.out.println(emps);
        
        smap.putAll(data);
        
        int minDiff = 229900;
        int range = emps -1;
        int arrlen = smap.size() - range;
        int item1 = 0, item2 = 0;
        for (int i = 0; i < arrlen; i++) {
            
            int x = (new Vector<Integer>(smap.values()).get(i));
            int y = (new Vector<Integer>(smap.values()).get(emps - 1));
            int v = Math.abs(x-y);
            if(v < minDiff) {
                minDiff = v;
                item1 = i;
                item2 = emps -1;
            }
            emps++;
        }

        FileWriter fw = new FileWriter("sample_output.txt");
        fw.write("The goodies selected for distribution are:");
        fw.write("\n\n");

        for (int i = item1; i <= item2; i++) {
            fw.write(new Vector<String>(smap.keySet()).get(i));
            fw.write(": ");
            String s1111 = new Vector<Integer>(smap.values()).get(i) + "";
            fw.write(s1111);
            fw.write("\n");
        }

        fw.write("\n");
        fw.write("And the difference between the chosen goodie with highest price and the lowest price is " + minDiff);

        fw.close();
        br.close();

        // // int[] a = { 7980, 22349, 999, 2799, 229900, 11101, 9999, 2195, 9800, 4999 };
        // // int noofEmp = 6;
        // // foo(a, noofEmp);   
    }
}
