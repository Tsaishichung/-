public class Main {

    private String sb;

    private int pdog;


    public static void main(String[] args) {
        Main main = new Main();
        int x = 8,y = 6;
        main.add(x, y);
        int res = main.add(x, y);
        System.out.println(res);
    }

    public int add(int x, int y){
        int sum = x + y;
        return sum;
    }

    public int add(){
        int x = 8;
        int y = 6;
        return x + y;
    }

}
