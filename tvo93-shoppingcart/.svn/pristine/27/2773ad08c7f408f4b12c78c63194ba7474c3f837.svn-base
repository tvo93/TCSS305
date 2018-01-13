/*
 * TCSS 305 Assignment 2 - Shopping Cart
 */

package model;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Objects;

/**
 * A class that stores information about an individual item.
 * @author tvo93
 * @version 1/17/2016
 *
 */
public final class Item {
    
    /** Name of the Item. **/
    private final String myItemName;
    
    /** The price of item. **/
    private final BigDecimal myItemPrice;    
    
    /** The price of bulk item. **/
    private  BigDecimal myBulkPrice;
    
    /** The bulk quantity. **/
    private int myBulkQuantity;
    
 
    /**
     * Constructor that takes a name and a price as arguments.
     * @param theName name of the item
     * @param thePrice price of the item
     */
    public Item(final String theName, final BigDecimal thePrice) {
        myItemName = theName;
        myItemPrice = thePrice;      
    }
   
    /**
     * Constructor that takes a name, a single-item price, 
     * a bulk quantity, and a bulk price as arguments. 
     * @param theName the name of the item
     * @param thePrice the price of the item
     * @param theBulkQuantity  the bulk quantity
     * @param theBulkPrice the price of bulk items
     */
    public Item(final String theName, final BigDecimal thePrice, final int theBulkQuantity,
                final BigDecimal theBulkPrice) { 
        
        if ((theBulkQuantity < 0) || (thePrice.compareTo(BigDecimal.ZERO) < 0)
                        || (theBulkPrice.compareTo(BigDecimal.ZERO) < 0)) {
            throw new IllegalArgumentException("These values can not be negative");
        
        } 
//        else if (theName == null) {
//            throw new IllegalArgumentException("name can not be empty");
//        }
        myItemName = Objects.requireNonNull(theName);
        myItemPrice = thePrice;
        myBulkQuantity = theBulkQuantity;
        myBulkPrice = theBulkPrice;
    }
    
   
    /**
     * Price for single - item. 
     * @return the single item price
     */
    public BigDecimal getPrice() {       
        return myItemPrice;
    }
    
    /**
     * Quantity for bulk items.
     * @return the bulk quantity
     */
    public int getBulkQuantity() {     
        return myBulkQuantity;
    }
    
    /**
     * Price for bulk items.
     * @return the bulk price
     */
    public BigDecimal getBulkPrice() {   
        return myBulkPrice;
    }

    /**
     * Check for bulk pricing.
     * @return true if the item has bulk pricing
     * false if otherwise
     */
    public boolean isBulk() {
        boolean bulk = false;
        if (myBulkQuantity > 0) { 
            bulk = true;   // bulk quantity is true   
        } 
        return bulk;
    }
    
    @Override
    public String toString() {
     // Get a default NumberFormat instance.
        final NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.US);
        
        // use string builder to represent for the item
        final StringBuilder sb = new StringBuilder().append(myItemName);
        sb.append(", ");
        sb.append(nf.format(myItemPrice));
       
       // Print out more information for bulk item
        if (myBulkQuantity > 0) {
            sb.append(" (" + myBulkQuantity + " for " 
                            + nf.format(myBulkPrice) + ")");
        }
        return sb.toString();
    }


    @Override
    public boolean equals(final Object theOther) {
        boolean result = false;
        if ((theOther != null) && (theOther.getClass() == this.getClass())) {
            
            // Cast theOther to the correct type
            final Item itemInfor = (Item) theOther;
            result = itemInfor.myBulkPrice == myBulkPrice
                            && (itemInfor.myBulkQuantity == myBulkQuantity) 
                            && (itemInfor.myItemPrice.equals(myItemPrice)) 
                            && (itemInfor.myItemName.equals(myItemName));
        }
        return result;
    }
    


    @Override
    public int hashCode() {      
        return toString().hashCode();
    }

} // end of the class
