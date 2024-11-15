import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

class Stock 
{
  private String symbol;
  private double price;
  private int quantity;

  public Stock(String symbol, double price, int quantity) 
  {
    this.symbol = symbol;
    this.price = price;
    this.quantity = quantity;
  }

  public String getSymbol() 
  {
    return symbol;
  }

  public double getPrice() 
  {
    return price;
  }

  public int getQuantity() 
  {
    return quantity;
  }

  public void setPrice(double price) 
  {
    this.price = price;
  }

  public void setQuantity(int quantity) 
  {
    this.quantity = quantity;
  }
}

class Portfolio 
{
  private Map<String, Stock> stocks;

  public Portfolio() 
  {
    stocks = new HashMap<>();
  }

  public void buyStock(String symbol, double price, int quantity) 
  {
    if (stocks.containsKey(symbol)) 
    {
      Stock stock = stocks.get(symbol);
      stock.setQuantity(stock.getQuantity() + quantity);
    } 
    else 
    {
      stocks.put(symbol, new Stock(symbol, price, quantity));
    } 
  }
  
  public void sellStock(String symbol, int quantity) 
  {
    if (stocks.containsKey(symbol)) 
    {
      Stock stock = stocks.get(symbol);
      if (stock.getQuantity() >= quantity) 
      {
        stock.setQuantity(stock.getQuantity() - quantity);
      } 
      else 
      {
        System.out.println("Not enough shares to sell.");
      }
    } 
    else 
    {
      System.out.println("Stock not found in portfolio.");
    }
  }

  public double calculatePortfolioValue() 
  {
    double totalValue = 0;
    for (Stock stock : stocks.values()) 
      {
        totalValue += stock.getPrice() * stock.getQuantity();
      }
      return totalValue;
  }
}

public class StockTradingPlatform 
{
  public static void main(String[] args) 
  {
    Portfolio portfolio = new Portfolio();
    Random random = new Random();
    Scanner input = new Scanner(System.in);

    double initialPrice = 100.0;

    boolean running = true;
    while (running) 
    {
      System.out.println("1. Buy Stock");
      System.out.println("2. Sell Stock");
      System.out.println("3. View Portfolio Value");
      System.out.println("4. Exit");
      System.out.print("Choose an option: ");
      int option = input.nextInt();

      switch (option) 
        {
          case 1:
            String symbol = "AAPL";
            double price = initialPrice + random.nextDouble() * 10;
            int quantity = random.nextInt(10) + 1;
            portfolio.buyStock(symbol, price, quantity);
            System.out.println("Bought " + quantity + " shares of " + symbol + " at $" + price + " each.");
            break;

          case 2:
            System.out.print("Enter stock symbol to sell: ");
            String sellSymbol = input.next();
            System.out.print("Enter quantity to sell: ");
            int sellQuantity = input.nextInt();
            portfolio.sellStock(sellSymbol, sellQuantity);
            break;

          case 3:
            double portfolioValue = portfolio.calculatePortfolioValue();
            System.out.println("Portfolio Value: $" + portfolioValue);
            break;

          case 4:
            running = false;
            break;

          default:
            System.out.println("Invalid option. Please try again.");
        }
    }
    input.close();
  }
}