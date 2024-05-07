/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BST;

import BST.Node;
import java.util.function.Function;

/**
 *
 * @author Admin
 */
public class BinarySearchTree<T extends Comparable<T>> {

    public Node<T> root;

    public Node<T> getRoot() {
        return root;
    }

    public BinarySearchTree() {
        root = null;
    }

    // Hiển thị cây theo thứ tự in-order
    public void inOrderTraversal() {
        inOrderTraversalRec(root);
    }

    private void inOrderTraversalRec(Node<T> root) {
        if (root != null) {
            inOrderTraversalRec(root.left);
            System.out.println(root.data + " ");
            inOrderTraversalRec(root.right);
        }
    }

    // Thêm một phần tử vào cây
    public void insert(T data) {
        root = insertRec(root, data);
    }

    private Node<T> insertRec(Node<T> root, T data) {
        if (root == null) {
            return new Node<>(data);
        }

        if (data.compareTo(root.data) < 0) {
            root.left = insertRec(root.left, data);
        } else if (data.compareTo(root.data) > 0) {
            root.right = insertRec(root.right, data);
        }

        return root;
    }

    // Hàm sửa đổi dữ liệu trong cây
    public void modifyData(T oldData, T newData) {
        root = modifyDataRec(root, oldData, newData);
    }

    // Hàm đệ quy để sửa đổi dữ liệu
    private Node<T> modifyDataRec(Node<T> root, T oldData, T newData) {
        if (root == null) {
            return null;
        }

        int compareResult = oldData.compareTo(root.data);

        if (compareResult < 0) {
            root.left = modifyDataRec(root.left, oldData, newData);
        } else if (compareResult > 0) {
            root.right = modifyDataRec(root.right, oldData, newData);
        } else {
            // Đã tìm thấy Node cần sửa đổi
            root.data = newData;
        }

        return root;
    }

    // Tìm kiếm một phần tử trong cây
    public T search(T data) {
        return searchRec(root, data);
    }

    private T searchRec(Node<T> root, T data) {
        if (root == null) {
            return null;
        }

        if (data.compareTo(root.data) == 0) {
            return root.data;
        } else if (data.compareTo(root.data) < 0) {
            return searchRec(root.left, data);
        } else {
            return searchRec(root.right, data);
        }
    }

    // Xóa một phần tử khỏi cây
    public void delete(T data) {
        root = deleteRec(root, data);
    }

    private Node<T> deleteRec(Node<T> root, T data) {
        if (root == null) {
            return root;
        }

        if (data.compareTo(root.data) < 0) {
            root.left = deleteRec(root.left, data);
        } else if (data.compareTo(root.data) > 0) {
            root.right = deleteRec(root.right, data);
        } else {
            // Node với một hoặc không có con
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }

            // Node có hai con, lấy phần tử nhỏ nhất ở cây con bên phải
            root.data = minValue(root.right);

            // Xóa phần tử nhỏ nhất ở cây con bên phải
            root.right = deleteRec(root.right, root.data);
        }

        return root;
    }

    // Đếm số node trong cây
    public int getNumberOfNodes() {
        return countNodes(root);
    }

    private int countNodes(Node<T> root) {
        if (root == null) {
            return 0;
        } else {
            int leftCount = countNodes(root.left);
            int rightCount = countNodes(root.right);
            return 1 + leftCount + rightCount;
        }
    }

    // Xóa toàn bộ các node trong cây
    public void makeEmpty() {
        root = makeEmpty(root);
    }

    private Node<T> makeEmpty(Node<T> node) {
        if (node != null) {
            node.left = makeEmpty(node.left);
            node.right = makeEmpty(node.right);
        }
        return null;
    }

    private T minValue(Node<T> root) {
        T minValue = root.data;
        while (root.left != null) {
            minValue = root.left.data;
            root = root.left;
        }
        return minValue;
    }
}
