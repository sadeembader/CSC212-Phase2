/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

/**
 *
 * @author sadee
 */
public interface List<T> {
    public void findFirst();
    public void findNext();
    public T retrieve();
    public void update(T e);
    public void insert(T e);
    public void remove();
    public boolean full();
    public boolean empty();
    public boolean last();
}
 