package Animal;

public abstract class Animal {
    String name;
    Integer age;
    Integer weight;

    public Animal() {
    }

    public Animal(String name, Integer age, Integer weight) {
        this.name = name;
        this.age = age;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "name= '" + name + '\'' +
                ", age= " + age +
                ", weight= " + weight +
                '}';
    }
}
