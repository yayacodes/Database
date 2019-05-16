// On my honor:
//
// - I have not used code obtained from another student,
// or any other unauthorized source, either modified or unmodified.
//
// - All code and documentation used in my program is either my original
// work, or was derived, by me, from the source code provided by the assignment.
//
// - I have not discussed coding details about this project with anyone
// other than my instructor, teaching assistants assigned to this
// course, except via the message board for the course. I understand that I
// may discuss the concepts of this program with other students, and that
// another student may help me debug my program so long as neither of us
// writes anything during the discussion or modifies any computer file
// during the discussion. I have violated neither the spirit nor letter of this restriction.

import java.io.*;
import java.util.*;

public class Database{

    private SplayTree<Integer> popTree = new SplayTree<>();
    private SplayTree<Integer> econTree = new SplayTree<>();
    private SplayTree<String> nameTree = new SplayTree<>();

    public class SplayTree<E extends Comparable<E>>{
        private Node root;

        public class Node{
            private E value;
            private Node left, right;
            private Record record;

            public Node(E value, Record record){
                this.value = value;
                this.record = new Record(record.getPopulation(), record.getEconomy(), record.getName());
            }
        }

        public void put(int k, Integer p, Integer e, String n){

            if(k == 1) {
                if(isEmpty()) {
                    root = new Node((E)p, new Record(p, e, n));
                }

                root = splay(root, (E) p);

                int comparison = ((E) p).compareTo(root.value);

                if (comparison < 0) {
                    Node newNode = new Node((E) p, new Record(p, e, n));
                    newNode.left = root.left;
                    newNode.right = root;
                    root.left = null;
                    root = newNode;
                } else if (comparison > 0) {
                    Node newNode = new Node((E) p, new Record(p, e, n));
                    newNode.right = root.right;
                    newNode.left = root;
                    root.right = null;
                    root = newNode;
                }
                else {
                    root.record = new Record(p, e, n);
                }
            }

            else if(k == 2){
                if (isEmpty()) {
                    root = new Node((E)e, new Record(p, e, n));
                }

                root = splay(root, (E) e);

                int comparison = ((E) e).compareTo(root.value);

                if (comparison < 0) {
                    Node newNode = new Node((E) e, new Record(p, e, n));
                    newNode.left = root.left;
                    newNode.right = root;
                    root.left = null;
                    root = newNode;
                } else if (comparison > 0) {
                    Node newNode = new Node((E) e, new Record(p, e, n));
                    newNode.right = root.right;
                    newNode.left = root;
                    root.right = null;
                    root = newNode;
                }
                else {
                    root.record = new Record(p, e, n);
                }
            }

            else if(k == 3){
                if (isEmpty()) {
                    root = new Node((E)n, new Record(p, e, n));
                }

                root = splay(root, (E) n);

                int comparison = ((E) n).compareTo(root.value);

                if (comparison < 0) {
                    Node newNode = new Node((E) n, new Record(p, e, n));
                    newNode.left = root.left;
                    newNode.right = root;
                    root.left = null;
                    root = newNode;
                } else if (comparison > 0) {
                    Node newNode = new Node((E) n, new Record(p, e, n));
                    newNode.right = root.right;
                    newNode.left = root;
                    root.right = null;
                    root = newNode;
                }
                else {
                    root.record = new Record(p, e, n);
                }
            }
        }

        private Node splay(Node n, E v){
            if(n == null)
                return null;

            int first_comparison = v.compareTo(n.value);

            if(first_comparison < 0){
                if(n.left == null){
                    return n;
                }

                int second_comparison = v.compareTo(n.left.value);

                if(second_comparison < 0){
                    n.left.left = splay(n.left.left, v);
                    n = rotateRight(n);
                }

                else if(second_comparison > 0){
                    n.left.right = splay(n.left.right, v);
                    if(n.left.right!= null)
                        n.left = rotateLeft(n.left);
                }

                if(n.left == null)
                    return n;

                else{
                    return rotateRight(n);
                }
            }

            else if(first_comparison > 0){
                if(n.right == null)
                    return n;

                int second_comparison = v.compareTo(n.right.value);

                if(second_comparison < 0){
                    n.right.left = splay(n.right.left, v);
                    if(n.right.left != null)
                        n.right = rotateRight(n.right);
                }

                else if(second_comparison > 0){
                    n.right.right = splay(n.right.right, v);
                    n = rotateLeft(n);
                }

                if(n.right == null)
                    return n;

                else{
                    return rotateLeft(n);
                }
            }
            else {
                return n;
            }
        }

        public boolean isEmpty(){
            return root == null;
        }


        private Node rotateRight(Node n){
            Node newNode = n.left;
            n.left = newNode.right;
            newNode.right = n;
            return newNode;
        }

        private Node rotateLeft(Node n){
            Node newNode = n.right;
            n.right = newNode.left;
            newNode.left = n;
            return newNode;
        }


        private int sizeofTree(){
            return getSize(root);
        }

        private int getSize(Node n){
            if(n == null)
                return 0;
            else{
                return 1 + getSize(n.left) + getSize(n.right);
            }
        }

        public Record getRecord(E v){
            root = splay(root, v);
            int x = v.compareTo(root.value);
            if (x == 0)
                return root.record;
            else {
                return null;
            }
        }

        public boolean contains(int i, E v){
            if(!isEmpty()) {
                if (i == 1)
                    return popTree.getRecord((Integer) v) != null;
                else if (i == 2)
                    return econTree.getRecord((Integer) v) != null;
                else if (i == 3)
                    return nameTree.getRecord(v.toString()) != null;
                else {
                    return false;
                }
            }
            else{
                return false;
            }
        }

        public void removeNode(E v){
            if(root == null)
                return;

            root = splay(root, v);
            int comparison = v.compareTo(root.value);

            if(comparison == 0){
                if(root.left == null) {
                    root = root.right;
                }
                else {
                    Node x = root.right;
                    root = root.left;
                    splay(root, v);
                    root.right = x;
                }
            }
        }

        public void listTree(Node node, int space){
            if(node == null)
                return;

            space+= 4;

            listTree(node.right, space);

            System.out.print("\n");
            for(int i = 4; i < space; i++)
                System.out.print(" ");
            System.out.print(node.value + "\n");

            listTree(node.left, space);
        }


    }

    public void insert(int population, int economy, String name){
        if(popTree.contains(1, population) || econTree.contains(2, economy) || nameTree.contains(3, name)) {
            System.out.println("** Error duplicate field **");
        }

        else{
            popTree.put(1, population, economy, name);
            econTree.put(2, population, economy, name);
            nameTree.put(3, population, economy, name);
            System.out.println("Successfully inserted record into database");
        }
    }

    public Record find(int i, Object o){
        if(i == 1 && popTree.contains(i,(Integer)o)) {
            return popTree.getRecord((Integer)o);
        }
        else if(i == 2 && econTree.contains(i, (Integer)o)) {
            return econTree.getRecord((Integer)o);
        }
        else if(i == 3 && nameTree.contains(i, o.toString())) {
            return nameTree.getRecord(o.toString());
        }
        else if(i < 1 || i > 3){
            System.out.println("Invalid field value. Please enter 1, 2, or 3");
            return null;
        }

        else{
            System.out.println("This is not in the database");
            return null;
        }
    }

    public void printRecord(Record record){
        if (record != null) {
            System.out.println("Found Record");
            System.out.print("Population: " + record.getPopulation());
            System.out.print(" Economy: " + record.getEconomy());
            System.out.print(" Name: " + record.getName() + "\n");
        }
    }

    public void remove(int i, Object o){
        if(find(i, o) == null){
        }
        else{
            if(i == 1){
                Record rec = popTree.getRecord((Integer)o);
                econTree.removeNode(rec.getEconomy());
                nameTree.removeNode(rec.getName());
                popTree.removeNode((Integer)o);
                System.out.println("Successfully removed record from database");
            }

            else if(i == 2){
                Record rec = econTree.getRecord((Integer)o);
                popTree.removeNode(rec.getPopulation());
                nameTree.removeNode(rec.getName());
                econTree.removeNode((Integer)o);
                System.out.println("Successfully removed record from database");
            }

            else if(i == 3){
                Record rec = nameTree.getRecord(o.toString());
                popTree.removeNode(rec.getPopulation());
                econTree.removeNode(rec.getEconomy());
                nameTree.removeNode(o.toString());
                System.out.println("Successfully removed record from database");
            }
        }
    }

    public void list(int i){
        if(i == 1)
            popTree.listTree(popTree.root, 0);
        else if(i == 2)
            econTree.listTree(econTree.root, 0);
        else if(i == 3)
            nameTree.listTree(nameTree.root, 0);
    }

    public static void main(String [] args){
        Scanner input = new Scanner(System.in);
        Database db = new Database();
        boolean play = false;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while(!play){
            System.out.print("Command: ('quit' to quit program) ");
            String before = "";
            try {
                before = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

            String after = before.trim().replaceAll(" +", " ");
            String[] commands = after.trim().split(" ");

            if(commands[0].equals("insert")) {
                db.insert(Integer.parseInt(commands[1]), Integer.parseInt(commands[2]), commands[3]);
                System.out.println("Population: " + commands[1] + "\nEconomy: " + commands[2] + "\nName: "+ commands[3]);
            }
            else if(commands[0].equals("quit"))
                play = true;
            else if(commands[0].equals("find")) {
                if ((Integer.parseInt(commands[1]) == 1))
                    db.printRecord(db.find(1, Integer.parseInt(commands[2])));
                else if ((Integer.parseInt(commands[1]) == 2))
                    db.printRecord(db.find(2, Integer.parseInt(commands[2])));
                else if ((Integer.parseInt(commands[1]) == 3))
                    db.printRecord(db.find(3, commands[2]));
                else
                    System.out.println("Only valid values are 1 or 2 or 3");
            }
            else if((commands[0].equals("remove"))){
                if ((Integer.parseInt(commands[1]) == 1)){
                    db.remove(1, Integer.parseInt(commands[2]));
                }
                else if ((Integer.parseInt(commands[1]) == 2)){
                    db.remove(2, Integer.parseInt(commands[2]));
                }
                else if ((Integer.parseInt(commands[1]) == 3)){
                    db.remove(3, commands[2]);
                }
            }

            else if((commands[0].equals("list"))){
                if ((Integer.parseInt(commands[1]) == 1)){
                    db.list(1);
                }
                else if ((Integer.parseInt(commands[1]) == 2)){
                    db.list(2);
                }
                else if ((Integer.parseInt(commands[1]) == 3)){
                    db.list(3);
                }
            }

            else if((commands[0].equals("makenull"))){
                db = new Database();
            }

            else{
                System.out.println("Invalid command");
            }
        }
    }
}
