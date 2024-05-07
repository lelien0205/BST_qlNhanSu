/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BST;

/**
 *
 * @author Admin
 */
public class Node<T extends Comparable<T>> {
    public T data;
    public Node<T> left, right;

    public Node(T data) {
        this.data = data;
        left = right = null;
    }
}
