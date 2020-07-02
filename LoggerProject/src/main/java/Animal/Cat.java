package Animal;

public class Cat extends Animal {


    public Cat(String name, Integer age, Integer weight) {
        super(name, age, weight);
    }

    @Override
    public String toString() {
        return "Cat{" +
                "name= '" + super.name + '\'' +
                ", age= " + super.age +
                ", weight= " + super.weight +
                "}";
    }
}
