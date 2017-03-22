

/**
 * RBTree
 * An implementation of a Red Black Tree with
 * distinct double-typed values
 */
 

public class RBTree {
 
    public enum RBNodeColor {
        Red, Black
    }
 
    private RBNode _nullNode;  //The Null Node
    private RBNode _root;   // Root of the the tree
    private boolean _isBalanced;
    private int _nodeCount;
 
    public RBTree() {
        //set null node object
        _nullNode = new RBNode();
        _nullNode.color = RBNodeColor.Black;
 
        //construct root node
        _root = _nullNode;
        _root.color = RBNodeColor.Black;
 
        _isBalanced = true;
        _nodeCount = 0;
    }
 
    public RBTree(boolean isBalanced) {
        this();
        _isBalanced = isBalanced;
    }
 
    public boolean empty() {
        //if root is _nullnode
        return (_root.equals(_nullNode));
    }
 
    /**
     * @param x - key to find
     * @return pointer to the nodeResult else _nullnode
     */
    public RBNode find(double x) {
        RBNode iterator = _root;
        //Start travelling the tree from the root.
        while (true) {
            if (!(iterator.equals(_nullNode))) {
                if (iterator.key == x) {
                    return iterator;
                } else if (iterator.key < x) { 
                    iterator = iterator.right;
                } else {
                    iterator = iterator.left;
                }
            } else {
                return _nullNode;
            }
        }
    }
 
    /**
     * find the member in the tree and "give" him  the book
     */ 
    public void findAndGive(int x,Book book) {
        RBNode iterator = _root;
        //Start travelling the tree from the root.
        if(book!=null){
            while (true) {
                if (!(iterator.equals(_nullNode))) {
                    if (iterator.key == x) {
                        iterator.member.setBook(book); 
                        System.out.println( x+" rent the book- "+book.b_id);
                        return;
                    } else if (iterator.key < x) {  
                        iterator = iterator.right;
                    } else {
                        iterator = iterator.left;
                    }
                } else {
                    System.out.println(book+" not found");
                    return;
                }
            }
        }
    }
 
      /**
     * find the member in the tree and return the book
     */ 
   public void  findAndReturn(double x,String book){
        RBNode iterator = _root;
        //Start travelling the tree from the root.
        while (true) {
            if (!(iterator.equals(_nullNode))) {
                if (iterator.key == x) {
                     iterator.member.returnBook(book);
                   
                } else if (iterator.key < x) {  
                    iterator = iterator.right;
                } else {
                    iterator = iterator.left;
                }
            } else {
                return;
            }
        }
    }
    
     /**
     * find the member in the tree and delete him from the tree
     */ 
   public void findAndDelete(double x){
          RBNode iterator = _root;
        //Start travelling the tree from the root.
        while (true) {
            if (!(iterator.equals(_nullNode))) {
                if (iterator.key == x) {
                     iterator.member.returnAllBooks();
                     delete(x);
                      return;
                } else if (iterator.key < x) {  
                    iterator = iterator.right;
                } else {
                    iterator = iterator.left;
                }
            } else {
                return;
            }
        }
    }
       
    /**
     * public void printBooks(double x)
     * find x in the tree and print is books
     */
    public void printBooks(double x){
       RBNode b= find(x); 
       b.member.getBooks();
        }
    
    public boolean contains(double x) {
        return (!find(x).equals(_nullNode));
    }
 
    /**
     * public double insert(double x)
     * inserts the x into the binary tree; the tree
     * must remain valid (keep its invariants).
     * the return value is defined in the exercise
     * &lt;p/&gt;
     * precondition:  none
     * postcondition: contains(x) == true (that is, x is in the tree)
     *
     * @param x - key to insert
     * @return - x
     */
    public double insert(Member m) {
        double x=m.getId();
        RBNode newNode = new RBNode(x, _nullNode, _nullNode, _nullNode,m);
 
        if (_root.equals(_nullNode)) {
            //case root is _nullnode
            _root = newNode;
            _nodeCount++;
        } else {
            //Traveling the tree till find a place to add new key
            RBNode iterator = _root;
            while (true) {
                if (iterator.key < x) {
                    if (iterator.right.equals(_nullNode)) {
                        iterator.right = newNode;
                        newNode.parent = iterator;
 
                        _nodeCount++;
                        break;
                    } else {
                        iterator = iterator.right;
                    }
                } else if (iterator.key > x) {
                    if (iterator.left.equals(_nullNode)) {
                        iterator.left = newNode;
                        newNode.parent = iterator;
                        _nodeCount++;
                        break;
                    } else {
                        iterator = iterator.left;
                    }
                } else {
                    return x;
                }
            }
        }
        if (_isBalanced) {
            //check for Red/Black Rules, if the tree is RB.
            if (!newNode.equals(_root)) {
                //case 0  - parent is not black then fix up tree
                if (newNode.parent.color != RBNodeColor.Black) {
                    insertFixUp(newNode);
                }
            }
            _root.color = RBTree.RBNodeColor.Black;
        }
        return x;
    }
 
    private void insertFixUp(RBNode current) {
        while (current.parent.color == RBNodeColor.Red && !current.equals(_root)) {
            if (current.parent.equals(current.parent.parent.left)) {
                //case for right uncle
                RBNode uncle = current.parent.parent.right;
                if (uncle.color == RBNodeColor.Red) {
                    //case 1 - uncle is red
                    current.parent.color = RBNodeColor.Black; //father
                    uncle.color = RBNodeColor.Black; //uncle
                    current.parent.parent.color = RBNodeColor.Red;//grandfather
                    current = current.parent.parent;
                } else {
                    //case 2 - current is a right child
                    if (current.equals(current.parent.right)) {
                        current = current.parent;
                        leftRotate(current);
                    }
                    //case 3
                    current.parent.color = RBNodeColor.Black;
                    current.parent.parent.color = RBNodeColor.Red;
                    rightRotate(current.parent.parent);
                }
            } else { //symetric cases for a left uncle
                RBNode uncle = current.parent.parent.left;
                if (uncle.color == RBNodeColor.Red) {
                    //case 1 - uncle is red
                    current.parent.color = RBNodeColor.Black; //father
                    uncle.color = RBNodeColor.Black; //uncle
                    current.parent.parent.color = RBNodeColor.Red;//grandfather
                    current = current.parent.parent;
                } else {
                    //case 2 - current is a right child
                    if (current.equals(current.parent.left)) {
                        current = current.parent;
                        rightRotate(current);
                    }
                    //case 3
                    current.parent.color = RBNodeColor.Black;
                    current.parent.parent.color = RBNodeColor.Red;
                    leftRotate(current.parent.parent);
                }
            }
        }
 
    }
 
    private void deleteFixUP(RBNode current) {
        while ((!current.equals(_root)) && current.color == RBNodeColor.Black) {
            if (current.equals(current.parent.left)) {
                RBNode brother = current.parent.right;
                if (brother.color == RBTree.RBNodeColor.Red) {
                    //case1 - right brother is red
                    brother.color = RBNodeColor.Black;
                    current.parent.color = RBNodeColor.Red;
                    leftRotate(current.parent);
                    brother = current.parent.right;
                }
                if (brother.left.color == RBNodeColor.Black &&
                        brother.right.color == RBNodeColor.Black) {
                    //case2 - right brother is black and both of his children black
                    brother.color = RBNodeColor.Red;
                    current = current.parent;
                } else {
                    //one child of right brother is red
                    if (brother.right.color == RBNodeColor.Black) {
                        //case3 - right brother\'s right child is black
                        brother.left.color = RBNodeColor.Black;
                        rightRotate(brother);
                        brother = current.parent.right;
                    }
                    //case4 - right brother\'s left child is black
                    brother.color = current.parent.color;
                    current.parent.color = RBNodeColor.Black;
                    brother.right.color = RBNodeColor.Black;
                    leftRotate(current.parent);
                    current = _root;
                }
            } else {
                //symetric cases as above
                RBNode brother = current.parent.left;
                if (brother.color == RBTree.RBNodeColor.Red) {
                    //case1 - left brother is red
                    brother.color = RBNodeColor.Black;
                    current.parent.color = RBNodeColor.Red;
                    rightRotate(current.parent);
                    brother = current.parent.left;
                }
                if (brother.left.color == RBNodeColor.Black &&
                        brother.right.color == RBNodeColor.Black) {
                    //case2 - left brother is black and both of his children black
                    brother.color = RBNodeColor.Red;
                    current = current.parent;
                } else {
                    //one child of left brother is red
                    if (brother.left.color == RBNodeColor.Black) {
                        //case3 - left brother\'s left child is black
                        brother.right.color = RBNodeColor.Black;
                        leftRotate(brother);
                        brother = current.parent.left;
                    }
                    //case4 - left brother\'s right child is black
                    brother.color = current.parent.color;
                    current.parent.color = RBNodeColor.Black;
                    brother.left.color = RBNodeColor.Black;
                    rightRotate(current.parent);
                    current = _root;
                }
            }
        }
        current.color = RBNodeColor.Black; //make sure curren is black
 
    }
 
    /**
     * The method makes newLeft to be the left child of current
     *
     * @param current - node to set the left child
     * @param newLeft - a new node to be the left child of current
     */
    private void setLeftNode(RBNode current, RBNode newLeft) {
        current.left = newLeft;
        newLeft.parent = current;
    }
 
    /**
     * The method makes newRight to be the right child of current
     *
     * @param current  - node to set the right child
     * @param newRight - a new node to be the right child of current
     */
    private void setRightNode(RBNode current, RBNode newRight) {
        current.right = newRight;
        newRight.parent = current;
    }
 
    /**
     * The method replace oldNode with newNode
     *
     * @param oldNode - node to be replaced.
     * @param newNode - new node to replace.
     */
    private void transplant(RBNode oldNode, RBNode newNode) {
        if (!oldNode.parent.equals(_nullNode)) {
            if (oldNode.equals(oldNode.parent.left)) {
                setLeftNode(oldNode.parent, newNode);
            } else {
                setRightNode(oldNode.parent, newNode);
            }
        } else {
            _root = newNode;
            _root.parent = _nullNode;
        }
 
    }
 
    /**
     * The method makes left rotate a round the give node.
     *
     * @param current - the node to rotate around.
     */
    private void leftRotate(RBNode current) {
        RBNode tmp = current.right;
        transplant(current, tmp);
        setRightNode(current, tmp.left);
        setLeftNode(tmp, current);
    }
 
    /**
     * The method makes right rotate a round the give node.
     *
     * @param current -  the node to rotate around.
     */
    private void rightRotate(RBNode current) {
        RBNode tmp = current.left;
        transplant(current, tmp);
        setLeftNode(current, tmp.right);
        setRightNode(tmp, current);
 
    }
 
 
    /**
     * public void delete(double x)
     * deletes x from the binary tree, if it is there;
     * the tree must remain valid (keep its invariants).
     * the return value is defined in the exercise
     * &lt;p/&gt;
     * precondition:  none
     * postcondition: contains(i) == false (that is, x is not in the tree)
     *
     * @param x - key to delete
     * @return x
     */
    public double delete(double x) {
        RBNode nodeToDelete = find(x);
 
        if (nodeToDelete.equals(_nullNode)) {
            return x;
        }
 
        RBNode node1, node2;
 
        if (nodeToDelete.left.equals(_nullNode) || nodeToDelete.right.equals(_nullNode)) {
            // one chile or no children
            node1 = nodeToDelete;
        } else {
            //two children
            node1 = Successor(nodeToDelete);
        }
 
        if (!node1.left.equals(_nullNode)) {
            node2 = node1.left;
        } else {
            node2 = node1.right;
        }
 
        node2.parent = node1.parent;
 
        if (!node1.parent.equals(_nullNode)) {
            if (node1.equals(node1.parent.left)) {
                node1.parent.left = node2;
            } else {
                node1.parent.right = node2;
            }
        } else {
            _root = node2;
            _root.parent = _nullNode;
        }
 
        if (!node1.equals(nodeToDelete)) {
            nodeToDelete.key = node1.key;
            nodeToDelete.member=node1.member;
        }
 
        if (_isBalanced) {
            if (node1.color == RBNodeColor.Black && !node2.equals(_nullNode)) {
                deleteFixUP(node2);
                _root.color = RBTree.RBNodeColor.Black;
            }
        }
        _nodeCount--;
        return x;
    }
 
    /**
     * The method returns the node to the successor of the given node
     *
     * @param x - node
     * @return the node holding the key next to x.key
     */
    private RBNode Successor(RBNode x) {
        RBNode baseNode = x;
 
        if (!baseNode.equals(_nullNode)) {
            if (!baseNode.right.equals(_nullNode)) {
                //return the minimum of right child
                return findMostLeft(baseNode.right);
            } else {
                RBNode baseParentNode = baseNode.parent;
                while (!baseParentNode.equals(_nullNode) && baseNode.equals(baseParentNode.right)) {
                    if (!baseParentNode.equals(_root)) {
                        baseNode = baseParentNode;
                        baseParentNode = baseNode.parent;
                    } else {
                        return _nullNode;
                    }
 
                }
                return baseParentNode;
            }
        } else {
            return _nullNode;
        }
    }
 
    /**
     * The method returns the most left node of a given node.
     *
     * @param current - node to start from
     * @return Most left node
     */
    private RBNode findMostLeft(RBNode current) {
        RBNode iterator = current;
        while (!iterator.left.equals(_nullNode)) {
            iterator = iterator.left;
        }
        return iterator;
    }
 
    /**
     * The method returns the most right node of a given node.
     *
     * @param current - node to start from
     * @return Most Right node
     */
    private RBNode findMostRight(RBNode current) {
        RBNode iterator = current;
        while (!iterator.right.equals(_nullNode)) {
            iterator = iterator.right;
        }
        return iterator;
    }
 
    /**
     * public double min()
     * &lt;p/&gt;
     * Returns the smallest key in the tree. If the tree
     * is empty, returns -1;
     * &lt;p/&gt;
     * precondition: none
     * postcondition: none
     *
     * @return - Minimum key of the tree
     */
    public double min() {
        if (!_root.equals(_nullNode)) {
            return findMostLeft(_root).key;
        } else {
            return -1;
        }
 
    }
 
    /**
     * public double max()
     * Returns the largest key in the tree. If the tree
     * is empty, returns -1;
     * &lt;p/&gt;
     * precondition: none
     * postcondition: none
     *
     * @return - Maximum key of the tree
     */
    public double max() {
        if (!_root.equals(_nullNode)) {
            return findMostRight(_root).key;
        } else {
            return -1;
        }
    }
 
    /**
     * public double[] toDoubleArray()
     * returns an double[] array containing the values stored in the tree,
     * in ascending order.
     * preconditions: none
     * postconditions: returns an array containing exactly the tree\'s elements in
     * ascending order.
     */
    public double[] toDoubleArray() {
        double[] arr = new double[_nodeCount];
        inOrder(_root, arr, 0);
        return arr;
    }
 
 
    private int inOrder(RBNode current, double[] x, int i) {
 
        if (!(current.left.equals(_nullNode))) {
            i = inOrder(current.left, x, i);
        }
 
        x[i] = current.key;
        i++;
 
        if (!(current.right.equals(_nullNode))) {
            i = inOrder(current.right, x, i);
        }
 
        return i;
    }
 
    /**
     * public int getHeight()
     * Returns the height of the tree.
     * &lt;p/&gt;
     * precondition: none
     * postcondition:
     */
    public int getHeight() {
        return treeHeight(_root, 0);
 
    }
 
    /**
     * The method returns the height of the tree from a given node.
     *
     * @param current - node to start from
     * @return height of the tree
     */
    private int treeHeight(RBNode current, int i) {
        int iLeft;
        int iRight;
 
        if (!(current.left.equals(_nullNode))) {
            iLeft = treeHeight(current.left, i + 1);
        } else {
            iLeft = i + 1;
        }
        if (!(current.right.equals(_nullNode))) {
            iRight = treeHeight(current.right, i + 1);
        } else {
            iRight = i + 1;
        }
 
        if (iLeft >iRight) {
            i = iLeft; 
        } else {
            i = iRight;
        }
        return i;
    }
 
    /**
     * public double getAverageDepth()
     * Returns the average depth of a node in the tree.
     * &lt;p/&gt;
     * precondition: none
     * postcondition:
     */
    public double getAverageDepth() {
        return sumHeight(_root, 0) / (numOfNulls(_root, 0) + _nodeCount);
 
    }
 
    /**
     * The method returns the sum height of a sub tree from a given node.
     * @param current - node to start from
     * @return sum of nodes heights.
     */
 
    private int sumHeight(RBNode current, int i) {
        int sumLeft;
        int sumRight;
 
        if (current.equals(_nullNode)) {
            return i;
        }
        sumLeft = sumHeight(current.left, i + 1);
        sumRight = sumHeight(current.right, i + 1);
 
        i = i + sumLeft + sumRight;
 
 
        return i;
    }
 
 
    /**
     * The method returns the number of null nodes in a tree.
     * @param current - node to start from
     * @return number of null nodes in a tree
     */
 
    private double numOfNulls(RBNode current, double count) {
        if (current.equals(_nullNode)) {
 
            return count + 1;
        }
 
        count = numOfNulls(current.left, count) + numOfNulls(current.right, count);
        return count;
    }
 
 
    /**
     * public int size()
     * Returns the number of nodes in the tree.
     * precondition: none
     * postcondition:
     * @return how many nodes in the tree,
     */
    public int getSize() {
        return _nodeCount;
    }
 
    /**
     * public class RBNode
     * If you wish to implement classes other than RBTree
     * (for example RBNode), do it in this file, not in
     * another file
     */
    public class RBNode {
        public RBNode right;
        public RBNode left;
        public RBNode parent;
        public Member member;
        public double key;
        public RBNodeColor color = RBTree.RBNodeColor.Red; //default color of node is red.
 
        public RBNode(double _key) {
            this();
            key = _key;
        }
 
        public RBNode() {
            right = null;
            left = null;
            parent = null;
            member=null;
        }
 
        public RBNode(double k, RBNode l, RBNode r, RBNode p,Member m) {
            right = r;
            left = l;
            parent = p;
            key = m.getId();
            member=m;
        }
 
    }
}
