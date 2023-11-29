package com.sinensia.pollosprimos.backend.business.services;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.sinensia.pollosprimos.backend.business.model.Pedido;
import com.sinensia.pollosprimos.backend.common.Pagina;

public interface PedidoServices {
	
	Long create(Pedido pedido);
	
	Optional<Pedido> read(Long numero);
	
	void update(Long numerPedido, Map<String, Object> atributos);
	
	void update(Pedido pedido);
	
	List<Pedido> getAll();
	
	/**
	 * 
	 * Cambia el estado de NUEVO a EN_PROCESO
	 * 
	 * Si el estado anterior es distinto a NUEVO lanza IllegalStateException
	 * 
	 */
	void procesar(Long numero);
	
	/**
	 * 
	 * Cambia el estado de EN_PROCESO a PENDIENTE_ENTREGA
	 * 
	 * Si el estado anterior es distinto a EN_PROCESO lanza IllegalStateException
	 * 
	 */
	void entregar(Long numero);
	
	/**
	 * 
	 * Cambia el estado de PENDIENTE_ENTREGA a SERVIDO
	 * 
	 * Si el estado anterior es distinto a PENDIENTE_ENTREGA IllegalStateException
	 * 
	 */
	void servir(Long numero);
	
	/**
	 * 
	 * Cambia del estado NUEVO, EN_PROCESO o PENDIENTE_ENTREGA a CANCELADO
	 * 
	 * Si el estado anterior es CANCELADO o SERVIDO lanza IllegalStateException
	 * 
	 */
	void cancelar(Long numero);
	
	
	public Pagina<Pedido> getPagina(int pageNumnber, int pageSize);
		
}
