
/**
 * Question:
 *
 * You are asked to merge two sorted linked lists (see the TODO below) but you need to
 * keep only one node for each distinct value
 *
 * Once it is done, copy-paste the complete class in Inginious, including the imports
 *
 *
 * Feel free to add methods or fields in the class, but do not modify
 * the signature and behavior of existing code
 *
 */
public class MergeSortedLinkedListDuplicate {


    /**
     * You receive two linked lists containing elements in increasing order.
     * You are asked to return a new linked list that contains the
     * elements of both linked lists in increasing order but without duplicates.
     * The input linked lists must remain unchanged.
     * Moreover, the final linkedList must not contain duplicate values
     * That is, instead of 1-1-2-5, you must return 1-2-5.
     *
     * The complexity of your method must be in O(n+m)
     * where n = size of list1, m = size of list2
     * @param list1 the first list containing elements in increasing order
     * @param list2 the second list containing elements in increasing order
     * @return a list that contains the elements of list1 and list2 in increasing order without duplicates
     */
    public static Node merge(Node list1, Node list2) {
        // TODO
        Node currentList1 = list1;
        Node currentList2 = list2;

        Node newList = new Node(0, null);
        Node current = newList;

        if (currentList1 != null || currentList2 != null){
            if (currentList1 != null && currentList2 != null){
                if (currentList1.value < currentList2.value){
                    newList.value = currentList1.value;
                    currentList1 = currentList1.next;
                } else {
                    newList.value = currentList2.value;
                    currentList2 = currentList2.next;
                }
            } else if (currentList1 != null) {
                newList.value = currentList1.value;
                currentList1 = currentList1.next;
            } else {
                newList.value = currentList2.value;
                currentList2 = currentList2.next;
            }
        }






        while (currentList1 != null || currentList2 != null){

            if (currentList1 != null && currentList2 != null){
                if (currentList1.value < currentList2.value){
                    if (current.value != currentList1.value){
                        current.next = new Node(currentList1.value, null);
                        current = current.next;
                    }
                    currentList1 = currentList1.next;
                } else {
                    if (current.value != currentList2.value){
                        current.next = new Node(currentList2.value, null);
                        current = current.next;
                    }
                    currentList2 = currentList2.next;
                }
            } else if (currentList1 != null){
                if (current.value != currentList1.value){
                    current.next = new Node(currentList1.value, null);
                    current = current.next;
                }
                currentList1 = currentList1.next;
            } else {
                if (current.value != currentList2.value){
                    current.next = new Node(currentList2.value, null);
                    current = current.next;
                }
                currentList2 = currentList2.next;
            }

        }
         return newList;
    }


    static class Node {

        int value;
        Node next;

        public Node(int value, Node next) {
            this.value = value;
            this.next = next;
        }

    }
}
