import java.util.Scanner;
import java.util.ArrayList;

public class Assignment3 {

    public static void main(String[] args) {
        BookManager bookManager = new BookManager();
        Scanner key = new Scanner(System.in);
        int input, input2;
        ArrayList books = bookManager.getList();

        while (true) {
            System.out.println("Welcome to book manager.exe, what would you like to do?\n"
                    + "1. Add book\n2. Remove book\n3. Replace book\n4. Check inventory\n"
                    + "5. Check book\n6. Sell book\n7. Check sales report\n8.Quit");
            input = key.nextInt();
            key.nextLine();
            if (input == 1) {
                System.out.println(
                        "Please type the title, the author, the ISBN, amount of that book and then the price in that order.");
                bookManager.addBook(
                        new Book(key.nextLine(), key.nextLine(), key.nextLine(), key.nextInt(), key.nextDouble()));
                System.out.println("Book added!");
            } else if (input == 2) {
                System.out.println("Please input the index (starts at zero) of the book you would like to remove.");
                input = key.nextInt();

                if (input < 0 || input > books.size() - 1) {
                    System.out.println("ERROR: Non valid index.");
                } else {
                    bookManager.removeBook(input);
                    System.out.println("Book removed!");
                }
            } else if (input == 3) {
                System.out.println("Please input the index (starts of zero) of the book you want to replace.");
                input = key.nextInt();

                if (input < 0 || input > books.size() - 1) {
                    System.out.println("ERROR: Non valid index.");
                } else {
                    System.out.println(
                            "Please type the title, the author, the ISBN, amount of that book and then the price of that book in that order.");
                    bookManager.replaceBook(input,
                            new Book(key.nextLine(), key.nextLine(), key.nextLine(), key.nextInt(), key.nextDouble()));
                    System.out.println("Book replaced!");
                }
            } else if (input == 4) {
                System.out.println(bookManager.getInventory());
            } else if (input == 5) {
                System.out.println("Please input the index (starts at zero) of the book you would like to check.");
                input = key.nextInt();

                if (input < 0 || input > books.size() - 1) {
                    System.out.println("ERROR: Non valid index.");
                } else {
                    System.out.println(bookManager.getBook(input));
                }
            } else if (input == 6) {
                System.out.println(
                        "Please input the index (starts at zero) of the book you would like to sell followed by the number you would like to sell.");
                input = key.nextInt();
                input2 = key.nextInt();
                if (bookManager.sellBook(input, input2)) {
                    System.out.println("Success! Balance is now $" + bookManager.getMoney() + "!");
                } else {
                    System.out.println(
                            "Something went wrong, make sure that your index is valid and you did not exceed the quantity of books.");
                }

            } else if (input == 7) {
                System.out.println("Total books sold: " + bookManager.generateSalesReport());
            } else if (input == 8) {
                System.out.println("Thank you for using my program, goodbye.");
                break;
            } else {
                System.out.println("ERROR: Not a valid input.");
            }

        }
        key.close();

    }

}

class Book {
    private String title, author, iSBN;
    private int quantity;
    private double price;

    public Book(String t, String a, String i, int q, double p) {
        title = t;
        author = a;
        iSBN = i;
        quantity = q;
        price = p;
    }

    public void setTitle(String t) {
        title = t;
    }

    public void setAuthor(String a) {
        author = a;
    }

    public void setISBN(String i) {
        iSBN = i;
    }

    public void setQuantity(int q) {
        quantity = q;
    }

    public void lowerQuantity() {
        quantity--;
    }

    public void setPrice(int p) {
        price = p;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getISBN() {
        return iSBN;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public String toString() {
        return "The book " + title + " is written by " + author + ", has an ISBN of " + iSBN + " there are " + quantity
                + " copies and each copy"
                + " costs $" + price + ".";
    }
}

class BookManager {
    private ArrayList<Book> books = new ArrayList<>();
    private double money;
    private int booksSold;

    public BookManager() {
        money = 0;
        booksSold = 0;
    }

    public void addBook(Book b) {
        books.add(b);
    }

    public double getMoney() {
        return money;
    }

    public void removeBook(int i) {
        books.remove(i);
    }

    public boolean sellBook(int i, int q) {
        if (i < 0 || i > books.size() - 1 || q > books.get(i).getQuantity() || q < 0) {
            return false;
        } else {
            while (q > 0) {
                money += books.get(i).getPrice();
                books.get(i).lowerQuantity();
                q--;
                booksSold++;
            }
            if (books.get(i).getQuantity() == 0) {
                books.remove(i);
            }
            return true;
        }
    }

    public void replaceBook(int i, Book b) {
        books.set(i, b);
    }

    public ArrayList<Book> getList() {
        return books;
    }

    public int generateSalesReport() {
        return booksSold;
    }

    public String getInventory() {
        String result = "";
        for (int i = 0; i < books.size(); i++) {
            result += books.get(i).toString() + "\n";
        }
        return result;
    }

    public String getBook(int i) {
        String result = books.get(i).toString();
        return result;
    }
}