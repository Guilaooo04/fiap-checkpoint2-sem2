package br.com.fiap.ecommerce.dtos;

import java.math.BigDecimal;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import br.com.fiap.ecommerce.model.ItemPedido;

import lombok.Getter;
import lombok.Setter;
import lombok.Data;


@Data
@Getter
@Setter
public class ItemPedidoRequestCreateDto {
	private Long idPedido;
    private Long idProduto;
    private BigDecimal quantidade;
    private BigDecimal valorTotal;
    private static final ModelMapper modelMapper = new ModelMapper();
	static {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        TypeMap<ItemPedidoRequestCreateDto, ItemPedido> typeMap = modelMapper.createTypeMap(ItemPedidoRequestCreateDto.class, ItemPedido.class);

        typeMap.addMappings(mapper -> {
            mapper.skip(ItemPedido::setId);
            mapper.map(src -> {
                Produto produto = new Produto();
                produto.setId(src.getIdProduto());
                return produto;
            }, ItemPedido::setIdProduto);
            mapper.map(src -> {
                Pedido pedido = new Pedido();
                pedido.setId(src.getIdPedido());
                return pedido;
            }, ItemPedido::setIdPedido);
        });
    }

    public ItemPedido toModel() {
        return modelMapper.map(this, ItemPedido.class);
    }
}

    
	

}
