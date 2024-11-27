package details;

public abstract class Detail {
    private String shape;  // Форма
    private String material;  // Материал
    private double weight;  // Вес
    private double size;  // Размер

    // Конструктор
    public Detail(String shape, String material, double weight, double size) {
        this.shape = shape;
        this.material = material;
        this.weight = weight;
        this.size = size;
    }

    // Геттеры и сеттеры
    public String getShape() {
        return shape;
    }

    public void setShape(String shape) {
        this.shape = shape;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "Detail{" +
                "shape='" + shape + '\'' +
                ", material='" + material + '\'' +
                ", weight=" + weight +
                ", size=" + size +
                '}';
    }
}

