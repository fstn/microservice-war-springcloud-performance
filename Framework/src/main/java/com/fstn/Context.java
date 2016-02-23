package com.fstn;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Context {

	Invoice invoice;
	List<StackCall> callStack = new ArrayList<StackCall>();
	
	public Context() {
		super();
	}
	
	/**
	 * @return the invoice
	 */
	public Invoice getInvoice() {
		return invoice;
	}
	/**
	 * @param invoice the invoice to set
	 */
	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}
	/**
	 * @return the callStack
	 */
	public List<StackCall> getCallStack() {
		return callStack;
	}
	/**
	 * @param callStack the callStack to set
	 */
	public void setCallStack(List<StackCall> callStack) {
		this.callStack = callStack;
	}

	public void addCall(StackCall call) {
		callStack.add(call);
		
	}
	
	
}
