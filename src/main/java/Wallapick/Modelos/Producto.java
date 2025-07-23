package Wallapick.Modelos;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nombre;

    @NotBlank
    @Column(length = 1000)
    private String descripcion;

    @Enumerated(EnumType.STRING)
    @NotNull
    private CategoryEnum category;

    @Positive
    private double precio;

    @Min(0)
    private int stock;

    private boolean enVenta = true;

    @PastOrPresent
    @Temporal(TemporalType.DATE)
    private Date fechaPublicacion = new Date();

    // Vendedor (N:1)
    @ManyToOne
    @JoinColumn(name = "id_vendedor", nullable = false)
    private Usuario vendedor;

    // Compradores (M:N)
    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Compra> compras = new ArrayList<>();


    public Producto(Long id, String nombre, String descripcion, CategoryEnum category, double precio, int stock, boolean enVenta, Date fechaPublicacion, Usuario vendedor, List<Compra> compras) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.category = category;
        this.precio = precio;
        this.stock = stock;
        this.enVenta = enVenta;
        this.fechaPublicacion = fechaPublicacion;
        this.vendedor = vendedor;
        this.compras = compras;

    }

    public Producto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public CategoryEnum getCategory() {
        return category;
    }

    public void setCategory(CategoryEnum category) {
        this.category = category;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public boolean isEnVenta() {
        return enVenta;
    }

    public void setEnVenta(boolean enVenta) {
        this.enVenta = enVenta;
    }

    public Date getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(Date fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public Usuario getVendedor() {
        return vendedor;
    }

    public void setVendedor(Usuario vendedor) {
        this.vendedor = vendedor;
    }

    public List<Compra> getCompras() {
        return compras;
    }
    public void setCompras(List<Compra> compras) {
        this.compras = compras;
    }
}