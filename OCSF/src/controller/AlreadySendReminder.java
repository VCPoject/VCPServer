package controller;

import java.util.HashMap;

import entity.Order;
import entity.Subscribe;

public class AlreadySendReminder {
	HashMap<Integer, Subscribe> subscribe = new HashMap<Integer, Subscribe>();
	HashMap<Integer,Order> orders = new HashMap<>();
	
	public HashMap<Integer, Subscribe> getSubscribe() {
		return subscribe;
	}
	public void setSubscribe(Subscribe subscribe) {
		this.subscribe.put(subscribe.getSubscribeNum(),subscribe);
	}
	public HashMap<Integer, Order> getOrders() {
		return orders;
	}
	public void setOrders(Order orders) {
		this.orders.put(orders.getIdorder(), orders);
	}
}
