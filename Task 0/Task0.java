public class Task0 {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Немає аргументів командного рядка.");
        } else {
            System.out.println("Аргументи командного рядка:");
            
            for (int i = 0; i < args.length; i++) {
                System.out.println((i + 1) + ": " + args[i]);
            }
        }
    }
}