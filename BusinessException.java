package dev.emanoel.shopcore.cart;
import dev.emanoel.shopcore.product.Product; import jakarta.persistence.*;
@Entity @Table(name="cart_items",uniqueConstraints=@UniqueConstraint(columnNames={"cart_id","product_id"}))
public class CartItem { @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id; @ManyToOne(optional=false) @JoinColumn(name="cart_id") private Cart cart; @ManyToOne(optional=false) @JoinColumn(name="product_id") private Product product; @Column(nullable=false) private Integer quantity; protected CartItem(){} public CartItem(Cart c,Product p,int q){cart=c;product=p;quantity=q;} public Long getId(){return id;} public Product getProduct(){return product;} public Integer getQuantity(){return quantity;} public void add(int q){quantity+=q;} }
