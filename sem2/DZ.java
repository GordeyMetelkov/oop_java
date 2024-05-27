public class DZ {
    public static void main(String[] args) {
        new Form();
    }
class Form {
    Button btn = new Button();
    public Form() {
        this.btn.addActionListener(new HelloWorldAction());
        this.btn.click();
    }
}
class Button extends Form {
    ButtonActionListener action;
    public void addActionListener(ButtonActionListener action){
        this.action = action;
    }
    public void click(){
        action.actionPerformed();
    }
}
public interface ButtonActionListener {
    public void actionPerformed();
    
}

class HelloWorldAction implements ButtonActionListener {

    @Override
    public void actionPerformed() {
        System.out.println("Hello World");
    }


}
class GoodbyeWorldAction implements ButtonActionListener {

    @Override
    public void actionPerformed() {
        System.out.println("Goobye World");
    }       
}
}
