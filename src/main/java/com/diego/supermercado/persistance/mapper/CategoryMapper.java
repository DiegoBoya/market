package com.diego.supermercado.persistance.mapper;

import com.diego.supermercado.domain.Category;
import com.diego.supermercado.persistance.entity.Categoria;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    // mapea de un Entity a un Domain
    @Mappings({
            @Mapping(source= "idCategoria", target="categoryId"),
            @Mapping(source= "descripcion", target="name"),
            @Mapping(source= "estado", target="active")
    })
    Category toCategory(Categoria categoria);

    // esta anotacion hace lo inverso de la anotacion Mappings de arriba
    // se ignora el atributo productos de la class
    @InheritInverseConfiguration
    @Mapping(target="productos", ignore = true)
    Categoria toCategoria(Category category);
}
