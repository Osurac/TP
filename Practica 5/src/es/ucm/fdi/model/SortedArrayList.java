package es.ucm.fdi.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;



public class SortedArrayList<E> extends ArrayList<E> {
	private static final long serialVersionUID = 1L;
	private Comparator<E> cmp;
	
	public SortedArrayList(Comparator<E> cmp){
		this.cmp=cmp;
	}
	
	@Override
	public boolean add(E e){
		//programar la insercion ordenada
		int indice=0;
		while(indice<super.size()&&(cmp.compare(e, super.get(indice))==0||cmp.compare(e, super.get(indice))==1)){
			indice++;
		}
		super.add(indice, e);
		return true;
	}
	
	@Override
	public boolean addAll(Collection<? extends E> c){
		//programar insercion ordenada(invocando a add)
		for(E e:c){
			this.add(e);
		}
		return true;
	}
	
	@Override
	public E get(int index){
		return super.get(index);
	}
	
	@Override
	public E remove(int index){
		return super.remove(index);
	}
	//sobreescribir los metodos que realizan operaciones de
	//insercion basados en un indice para que lancen excepcion.
	//Ten en cuenta que esta operacion romperia la ordenacion.
	//estos metodos son add(int Index, E element),
	//addAll(int index, Colection<? Extends E>) y E set(int index, E element).
	public void add(int index,E element){
		throw new Error("No puedes hacer un add con indice");
	}
	
	
	public boolean addAll(int index,Collection<? extends E> c){
		throw new Error("No puedes hacer un add con indice");
	}
	
	public E set(int index, E element){
		throw new Error("No puedes modificar un valor con indice");
	}
}
