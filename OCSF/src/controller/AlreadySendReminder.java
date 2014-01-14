package controller;

import java.util.HashMap;

import entity.Order;
import entity.Subscribe;

/**
 * AlreadySendReminder is hold all the Subscribe entities and Order entities that already been mailed
 * for not flood them
 *
 */
public class AlreadySendReminder {
	/**
	 * subscribe is holding all the subscribe that have been mailed
	 */
	HashMap<Integer, Subscribe> subscribe = new HashMap<Integer, Subscribe>();
	/**
	 * orders is holding all the subscribe that have been mailed
	 */
	HashMap<Integer,Order> orders = new HashMap<>();
	
	/**
	 * getSubscribe is give subscribes that been mailed
	 * @return subscribes that have been mailed
	 */
	public HashMap<Integer, Subscribe> getSubscribe() {
		return subscribe;
	}
	/**
	 * setSubscribe is add subscribe that have been mailed to the hashmap
	 * @param subscribe that have been mailed
	 */
	public void setSubscribe(Subscribe subscribe) {
		this.subscribe.put(subscribe.getSubscribeNum(),subscribe);
	}
	/**
	 * getOrders is give orders that been mailed
	 * @return orders that have been mailed
	 */
	public HashMap<Integer, Order> getOrders() {
		return orders;
	}
	/**
	 * setOrders is add order that have been mailed to the hashmap
	 * @param order that have been mailed
	 */
	public void setOrders(Order orders) {
		this.orders.put(orders.getIdorder(), orders);
	}
}
