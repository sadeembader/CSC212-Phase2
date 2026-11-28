// K is the type of the key, T is the type of the data
public class BST<K extends Comparable<K>, T> implements IBST<K, T> {
   private BSTNode<K, T> root, current;
   private int count; // number of keys in the tree

   public BST() {
      root = current = null;
      count = 0;
   }

   public boolean empty() {
      return root == null;
   }

   public int size() {
      return count;
   }

   public T retrieve() {
      return current.data;
   }

    // search for the key, if not found current stays on the last node we checked
   public boolean findKey(K tkey) {
      BSTNode<K, T> p = root, q = root;
   
      if (empty())
         return false;
   
      while (p != null) {
         q = p;
         if (tkey.compareTo(p.key) == 0) {
            current = p;
            return true;
         }
         else if (tkey.compareTo(p.key) < 0)
            p = p.left;
         else
            p = p.right;
      }
   
      current = q;
      return false;
   }

   public boolean insert(K k, T val) {
      BSTNode<K, T> p, q = current;
   
        // key already in the tree
      if (findKey(k)) {
         current = q;
         return false;
      }
   
      p = new BSTNode<K, T>(k, val);
      if (empty()) {
         root = current = p;
      }
      else {
            // current is the parent of the new node
         if (k.compareTo(current.key) < 0)
            current.left = p;
         else
            current.right = p;
         current = p;
      }
      count++;
      return true;
   }

    // returns the data sorted by key
   public LinkedList<T> inOrder() {
      LinkedList<T> result = new LinkedList<T>();
      inOrder(root, result);
      return result;
   }

    // left, then the node, then right
   private void inOrder(BSTNode<K, T> p, LinkedList<T> result) {
      if (p == null)
         return;
      inOrder(p.left, result);
      result.insert(p.data);
      inOrder(p.right, result);
   }

   public boolean removeKey(K key) {
      return false;
   }

   public int height() {
      return 0;
   }

   public int nbKeyComp(K key) {
      return 0;
   }

   @Override
   public LinkedList<T> rangeSearch(K low, K high) {
      LinkedList<T> result = new LinkedList<T>();
   
      if (low.compareTo(high) > 0)
         return result;
   
      rangeSearch(root, low, high, result);
      return result;
   }

   private void rangeSearch(BSTNode<K, T> p, K low, K high, LinkedList<T> result) {
      if (p == null)
         return;
   
      if (low.compareTo(p.key) < 0)
         rangeSearch(p.left, low, high, result);
   
      if (low.compareTo(p.key) <= 0 && high.compareTo(p.key) >= 0)
         result.insert(p.data);
   
      if (high.compareTo(p.key) > 0)
         rangeSearch(p.right, low, high, result);
   }  
    
    
    
}
