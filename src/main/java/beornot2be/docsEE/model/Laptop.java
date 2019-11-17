package beornot2be.docsEE.model;

public class Laptop {
    public int id;
    public int price;
    public int ram;
    public String name;

    public Laptop(int id, int price, int ram, String name) {
      this.id = id;
      this.name = name;
      this.price = price;
      this.ram = ram;
    };

}
