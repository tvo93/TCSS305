/*
 * TCSS 305 Assignment 2 - Shopping Cart
 */


package model;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A class that stores information about the customer's overall purchase.
 * @author tvo93
 * @version 1/17/2016
 */
public class ShoppingCart {
    /**
     * Item Order class.
     */
    private ItemOrder myItemOrder;
    /**
     * Discount for bulk price if membership. 
     */
    private boolean myMembership;
    /** A List of item order. */
    private final List<ItemOrder> myItemList;
    /**
     * Constructor that creates an empty shopping cart.
     */
    public ShoppingCart() {
        myMembership = false;
        myItemList = new ArrayList<ItemOrder>();      
    } 

    /**
     * Add an order to the shopping cart.
     * @param theOrder the order
     */
    public void add(final ItemOrder theOrder) {
        myItemOrder = Objects.requireNonNull(theOrder);

        // use loop to add the order
        for (int i = 0; i < myItemList.size(); i++) {
            if (((ItemOrder) myItemList.get(i)).getItem().equals(theOrder.getItem())) {
                myItemList.set(i,  theOrder);
                return;
            }
        }
        myItemList.add(theOrder); // add into an array list
    }

    /**
     * A method that sets whether or not the customer for this shopping cart
     * has a store membership.
     * @param theMembership the membership
     */
    public void setMembership(final boolean theMembership) {
        myMembership = theMembership;
    }

    /**
     * A method that calculates for the total cost.
     * @return the total cost of this shopping cart
     */
    public BigDecimal calculateTotal() {    
        BigDecimal total = BigDecimal.ZERO;
        boolean isBulk; 
       
        // use loop to calculate for the total cost
        for (int i = 0; i < myItemList.size(); i++) {
         // check bulk price for each order
            isBulk = myItemList.get(i).getItem().isBulk();
            // calculate for bulk price if membership and bulk quantity are true
            if (myMembership && isBulk) { 
                total = total.add(bulkCost(myItemList.get(i)));        
            } else {
                // regular price
                total = total.add(regularCost(myItemList.get(i)));        
            }       
        }
     // round to 2 decimal number.  
        return total.setScale(2, RoundingMode.HALF_EVEN);     
    }
    
    /**
     * Calculate for the the bulk item cost.
     * @param theItemOrder
     * @return the cost of bulk item
     * @param theOrder the item order
     */
    private BigDecimal bulkCost(final ItemOrder theOrder) {
        myItemOrder = Objects.requireNonNull(theOrder);   
        BigDecimal total = BigDecimal.ZERO;
        // calculate for bulk item only if the bulk quantity is equal or greater 
        // than item quantities inputed by users
        if (myItemOrder.getQuantity() >= myItemOrder.getItem().getBulkQuantity()
                            && myItemOrder.getItem().getBulkQuantity() > 0) {
            
            // bulk cost
            final BigDecimal totalBulkCost = myItemOrder.getItem().getBulkPrice().multiply
                            (new BigDecimal(myItemOrder.getQuantity() 
                                            /  myItemOrder.getItem().getBulkQuantity()));
                
            // regular cost
            final BigDecimal totalRegularCost = myItemOrder.getItem().getPrice().multiply
                            (new BigDecimal(myItemOrder.getQuantity()
                                            % myItemOrder.getItem().getBulkQuantity()));
            
            // add up together if item quantities are greater than bulk quantities
            total = totalBulkCost.add(totalRegularCost);       
       
        } else {
            // regular cost
            total = myItemOrder.getItem().getPrice().multiply
                            (new BigDecimal(myItemOrder.getQuantity()));
        }
        return total;
    }   
    
    /**
     * Calculate for the the regular item cost.
     * @param theItemOrder
     * @return the cost of regular item
     * @param theOrder the item order
     */
    private BigDecimal regularCost(final ItemOrder theOrder) {
        myItemOrder = Objects.requireNonNull(theOrder);
        // return to regular cost total
        return myItemOrder.getItem().getPrice().multiply
                        (new BigDecimal(myItemOrder.getQuantity()));
    }
    
    
    /**
     * Removes all orders.
     */
    public void clear() {
        myItemList.clear();
    }

    @Override
    public String toString() {    
        return myItemList.toString();
    }

}
