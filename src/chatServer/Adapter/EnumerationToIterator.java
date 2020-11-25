package chatServer.Adapter;

import java.util.Enumeration;
import java.util.Iterator;

import socketDAO.UserInfo;

public class EnumerationToIterator implements Iterator<UserInfo> {
	Enumeration<UserInfo> enumeration;

	public EnumerationToIterator(Enumeration enumeration) {
		this.enumeration= enumeration;
	}

	@Override
	public boolean hasNext(){ 
		return enumeration.hasMoreElements();
	}

	@Override
	public UserInfo next() {
		return enumeration.nextElement();
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}
}
