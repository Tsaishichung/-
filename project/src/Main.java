public class Main {


    public static void main(String[] args) {
        Main main = new Main();
        int x = 8,y = 6;
        int res = main.add(x, y);
        System.out.println(res);
    }

    public int add(int x, int y){
        int sum = x + y;
        return sum;
    }
}
