
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan= new Scanner(System.in);

        int island=scan.nextInt();
        int Edge= scan.nextInt();

        Graph1 g= new Graph1(island+1);

        for (int i=1;i<=Edge;i++){
            int u= scan.nextInt();
            int v=scan.nextInt();
            g.addEdges(u,v);
        }
        int x= scan.nextInt();
        int y= scan.nextInt();
        int time= scan.nextInt();
        int travel=scan.nextInt();
        g.BFS(x,y);
        //return from island y to x
        g.BFS(y,x);


    }
}