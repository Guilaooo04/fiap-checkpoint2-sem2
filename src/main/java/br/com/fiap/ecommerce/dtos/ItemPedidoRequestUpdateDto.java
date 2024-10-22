package br.com.fiap.ecommerce.dtos;

import java.math.BigDecimal;

import org.modelmapper.ModelMapper;

import br.com.fiap.ecommerce.model.ItemPedido;
import lombok.Data;


@Data
public class ItemPedidoRequestUpdateDto {
	private Long idPedido;
    private Long idProduto;
    private BigDecimal quantidade;
    private BigDecimal valorTotal;
    private static final ModelMapper modelMapper = new ModelMapper();
    
	static {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        TypeMap<ItemPedidoRequestUpdateDto, ItemPedido> typeMap = modelMapper.createTypeMap(ItemPedidoRequestUpdateDto.class, ItemPedido.class);

        typeMap.addMappings(mapper -> {
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

    public ItemPedido toModel(Long id) {
        ItemPedido itemPedido = modelMapper.map(this, ItemPedido.class);
        itemPedido.setId(id);
        return itemPedido;
    }
}
