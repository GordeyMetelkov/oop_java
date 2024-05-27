package sem1;

import java.util.ArrayList;
import java.util.HashMap;

public class MyVariantTree {
    
    public static void main(String[] args) {

        var irina = new People("Ирина");
        var vasya = new People("Вася");
        var masha = new People("Маша");
        var jane = new People("Женя");
        var ivan = new People("Иван");
        Tree aa = new Tree();
        aa.append(irina, vasya);
        aa.append(vasya, jane);

        System.out.println(new Searching(aa).outputResult(vasya, Relationship.parent)); 
        System.out.println(new Searching(aa).outputResult(vasya, Relationship.children)); 
    }
    
}
class People {

    String name;

    public People(String name) {
        
        this.name = name;
    }
    public String getName() {
        return name;
    }
    @Override
    public String toString() {
        
        return getName();
    }
}
enum Relationship {
    children,
    parent
}

class Node {

    People p1;
    Relationship rel;
    People p2;

    public Node (People p1, Relationship rel, People p2) {
        
        this.p1 = p1;
        this.rel = rel;
        this.p2 = p2;
    }
    // @Override
    // public String toString() {
        
    //     return String.format("%s %s %s", p1, rel, p2);
    // }
}
class Tree {
    ArrayList<Node> tree = new ArrayList<>();
    public void append (People parent, People children) {
        
        tree.add(new Node(parent, Relationship.parent, children));
        tree.add(new Node(children, Relationship.children, parent));
    }
    public ArrayList<Node> getTree() {
        return tree;
    }
}

class Searching {

    ArrayList<Node> output;


    public Searching(Tree tr) {
        output = tr.getTree();
    }
    public String outputResult(People p, Relationship re) {

        StringBuilder result = new StringBuilder();

        for (Node i : output) {
            if(i.p1.getName() == p.getName() && i.rel == re) {
                result.append(i.p2);
            }
        }
        return result.toString();
    }

}


