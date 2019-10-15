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
		if(!super.isEmpty()){
			int indice=0;
			boolean busca=true;
			while(busca){
				if(cmp.compare(e, super.get(indice))==0||cmp.compare(e, super.get(indice))==1){
					indice++;
				}else{
					busca=false;
				}
				if(indice==super.size()){
					busca=false;
				}
			}
			if(indice==super.size()){
				super.add(indice,e);
			}else{
				E ele=super.get(indice);
				super.set(indice, e);
				super.add(indice+1, ele);
			}
		}else{
			super.add(0,e);
		}
		return true;
	}
	
	@Override
	public boolean addAll(Collection<? extends E> c){
		return false;
		//programar insercion ordenada(invocando a add)
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
}
