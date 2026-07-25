package dev.emanoel.shopcore.cart;
import org.springframework.http.ResponseEntity; import org.springframework.security.core.Authentication; import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/api/cart")
public class CartController { private final CartService service; public CartController(CartService s){service=s;} @GetMapping CartService.View get(Authentication a){return service.get(a.getName());} @PostMapping("/items") CartService.View add(Authentication a,@RequestBody CartService.AddItem r){return service.add(a.getName(),r);} @DeleteMapping("/items/{id}") ResponseEntity<Void> remove(Authentication a,@PathVariable Long id){service.remove(a.getName(),id);return ResponseEntity.noContent().build();} }
