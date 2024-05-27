import java.lang.Thread.Builder;

public class Prog {
    
    public static void main(String[] args) {
        Worker w = new Worker.Builder("Dan", "Brown").age(12).post(Worker.Post.Director).salary(100).build();
        System.out.println(w);
    }
}
class Worker {

    int age;
    int salary;
    final String firstName;
    final String secondName;
    Post post;

    enum Post{
        Director,
        Manager,
        Officer
    }
    public static class Builder {
        final String firstName;
        final String secondName;
        
        int age;
        int salary;
        Post post;
        
        public Builder (String firstName, String secondName) {
            this.firstName = firstName;
            this.secondName = secondName;
        }

        public Builder age(int age) {
            this.age = age;
            return this;
        }
        public Builder salary(int salary) {
            this.salary = salary;
            return this;
        }
        public Builder post(Post post) {
            this.post = post;
            return this;
        }
        public Worker build() {
            return new Worker(this);
        }
    }
    public Worker(Builder builder) {
        this.age = builder.age;
        this.salary = builder.salary;
        this.firstName = builder.firstName;
        this.secondName = builder.secondName;
        this.post = builder.post;
    }
    @Override
    public String toString() {
        return firstName + " " + secondName + " " + age + " " + salary;
    }
}
