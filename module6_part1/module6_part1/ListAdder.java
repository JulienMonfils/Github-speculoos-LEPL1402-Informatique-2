package module6_part1;

class Element {
    int value;
    Element next;
    public Element(int v) { this.value=v; }
}

class List {
    Element head;

    void add(int value) {
        Element newElement=new Element(value);
        newElement.next=head;
        head=newElement;
    }

    void remove() {
        if(head!=null) {
            head=head.next;
        }
    }
}

public class ListAdder {
    public static void main(String[] args) throws InterruptedException {
        List list=new List();

        Object someObject=new Object();

        // Without synchronized:

        Thread t1=new Thread(() -> {
      //      synchronized(someObject) {
                list.add(3);
      //      }
        });
        Thread t2=new Thread(() -> {
      //      synchronized(someObject) {
                list.add(4);
       //     }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(list.head.value);
        System.out.println(list.head.next.value);
    }
}
