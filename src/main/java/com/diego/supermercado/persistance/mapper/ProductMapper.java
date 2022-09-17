package com.diego.supermercado.persistance.mapper;

import com.diego.supermercado.domain.dto.Product;
import com.diego.supermercado.persistance.entity.Producto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CategoryMapper.class})
public interface ProductMapper {

    @Mappings({
            @Mapping(source="idProducto" , target="productId"),
            @Mapping(source="nombre" , target="name"),
            @Mapping(source="idCategoria" , target="categoryId"),
            @Mapping(source="precioVenta" , target="price"),
            @Mapping(source="cantidadStock" , target="stock"),
            @Mapping(source="estado" , target="active"),
            @Mapping(source="categoria" , target="category")
    })
    Product toProduct(Producto producto);
    //arriba definimos la conversion de Producto ---> Products
    //es lo mismo hacerlo para uno que para una lista, no tenemos que definir nada extra
    //el IDE ya lo reconoce
    List<Product> toProducts(List<Producto> productos);

    //cuando el destino sea codigo de barras que lo ignore
    @InheritInverseConfiguration
    @Mapping(target = "codigoBarras", ignore = true)
    Producto toProducto(Product product);
}
