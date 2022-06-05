import java.util.Arrays;
import java.util.Scanner;

public class Menu {
    static String[] names = {"Döner", "Kumpir", "Pide", "Salata", "Meşrubat"};
    static Double[] prices = {5.50, 6.50, 3.00, 2.00, 1.85};
    static int[] productNumbers = {};
    static int[] productCounts = {};

    public static void main(String[] args) {
        try {
            showMenu();
            selectAction();
        }
        catch (Exception error){
            System.out.println("Hata: " + error);
        }
    }

    private static void showMenu(){
        try {
            System.out.println("Ürün Fiyat");
            System.out.println("============================================");
            for (int i = 0; i < names.length; i++){
                System.out.println((i+1) + ". " + names[i] + ": " + prices[i]);
            }
            System.out.println("============================================");
        }
        catch (Exception error){
            System.out.println("Hata: " + error);
        }
    }

    private static void selectAction(){
        try {
            Scanner scannerUserAction = new Scanner(System.in);
            System.out.print("Yeni sipariş için (y), Adisyon oluşturmak için (q):");
            String userAction  = scannerUserAction.nextLine();

            if( userAction.equals("y") || userAction.equals("Y")){
                selectProductNumber();
            }
            else if (userAction.equals("q") || userAction.equals("Q")) {
                createBill();
            }
            else{
                System.out.println("Lütfen geçerli bir seçim yapınız!");
                selectAction();
            }
        }
        catch (Exception error) {
            System.out.println("Hata: " + error);
        }
    }

    private static void selectProductNumber(){
        try {
            Scanner scannerProductNumber = new Scanner(System.in);
            System.out.print("Ürün Numarası: ");

            boolean hasProductNumber = scannerProductNumber.hasNextInt();
            int productNumber = scannerProductNumber.nextInt();

            if( hasProductNumber && productNumber > 0 && productNumber <= names.length){
                productNumbers = Arrays.copyOf(productNumbers, productNumbers.length + 1);    // Mevcut arrayi güncellemek için önce uzunluğunu artırılır
                productNumbers[productNumbers.length - 1] = productNumber - 1;                          // artırılan uzunluğa yeni değerin atanması,
                selectProductCounter();
            }
            else{
                System.out.println("Lütfen geçerli bir değer giriniz!");
                selectProductNumber();
            }
        }
        catch (Exception error) {
            System.out.println("Hata: " + error);
        }
    }

    private static void selectProductCounter(){
        try {
            Scanner scannerProductCount = new Scanner (System.in);
            System.out.print("Adet: ");

            boolean hasProductCount = scannerProductCount.hasNextInt();
            int productCount = scannerProductCount.nextInt();

            if(hasProductCount && productCount > 0){
                productCounts = Arrays.copyOf(productCounts, productCounts.length + 1);     // Mevcut arrayi güncellemek için önce uzunluğunu artırılır
                productCounts[productCounts.length - 1] = productCount;                               // artırılan uzunluğa yeni değerin atanması,
                System.out.println();
                showMenu();
                selectAction();
            }
            else{
                System.out.println("Lütfen geçerli bir değer giriniz!");
                selectProductCounter();
            }
        }
        catch (Exception error) {
            System.out.println("Hata: " + error);
        }
    }

    private  static void createBill(){
        try {
            double total = 0.00;
            System.out.println();
            System.out.println("---------- ADİSYONUNUZ TAMAMLANDI ----------");
            for (int i = 0; i < productNumbers.length; i++) {
                double tempCount = productCounts[i];
                total += prices[productNumbers[i]] * tempCount;
                System.out.println(productCounts[i] + " adet " + names[productNumbers[i]]);
            }
            System.out.println("--------------------------------------------");
            System.out.println("Toplam: " + total + " TL");
            System.out.println("--------------------------------------------");
        }
        catch (Exception error) {
            System.out.println("Hata: " + error);
        }
    }

}
