package exercise.mapper;

// BEGIN
import exercise.dto.ProductCreateDTO;
import exercise.dto.ProductDTO;
import exercise.dto.ProductUpdateDTO;
import exercise.model.Product;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class ProductMapper {

    @Mapping(target = "name", source = "title")
    @Mapping(target = "barcode", source = "vendorCode")
    @Mapping(target = "cost", source = "price")
    public abstract Product map(ProductCreateDTO productCreateDTO);


    @Mapping(target = "cost", source = "price")
    public abstract void update(ProductUpdateDTO productUpdateDTO, @MappingTarget Product product);

    @Mapping(target = "price", source = "cost")
    @Mapping(target = "title", source = "name")
    @Mapping(target = "vendorCode", source = "barcode")
    public abstract ProductDTO map(Product product);



}
// END
