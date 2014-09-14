package com.antyzero.njumeter.tools;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 *
 */
public class ForwardingList<T> implements List<T> {

    private final List<T> list;

    public ForwardingList(List<T> tList) {
        list = tList;
    }

    public void add( int location, T object ) {
        list.add( location, object );
    }

    public boolean add( T object ) {
        return list.add( object );
    }

    public boolean addAll( int location, Collection<? extends T> collection ) {
        return list.addAll( location, collection );
    }

    public boolean addAll( Collection<? extends T> collection ) {
        return list.addAll( collection );
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public boolean contains( Object object ) {
        return list.contains( object );
    }

    @Override
    public boolean containsAll( Collection<?> collection ) {
        return list.containsAll( collection );
    }

    @Override
    public boolean equals( Object object ) {
        return list.equals( object );
    }

    @Override
    public T get( int location ) {
        return list.get( location );
    }

    @Override
    public int hashCode() {
        return list.hashCode();
    }

    @Override
    public int indexOf( Object object ) {
        return list.indexOf( object );
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public Iterator<T> iterator() {
        return list.iterator();
    }

    @Override
    public int lastIndexOf( Object object ) {
        return list.lastIndexOf( object );
    }

    @Override
    public ListIterator<T> listIterator() {
        return list.listIterator();
    }

    @Override
    public ListIterator<T> listIterator( int location ) {
        return list.listIterator( location );
    }

    @Override
    public T remove( int location ) {
        return list.remove( location );
    }

    @Override
    public boolean remove( Object object ) {
        return list.remove( object );
    }

    @Override
    public boolean removeAll( Collection<?> collection ) {
        return list.removeAll( collection );
    }

    @Override
    public boolean retainAll( Collection<?> collection ) {
        return list.retainAll( collection );
    }

    public T set( int location, T object ) {
        return list.set( location, object );
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public List<T> subList( int start, int end ) {
        return list.subList( start, end );
    }

    @Override
    public Object[] toArray() {
        return list.toArray();
    }

    @Override
    public <T1> T1[] toArray( T1[] array ) {
        return list.toArray( array );
    }
}
