$('#confirmacaoExclusaoModal').on('show.bs.modal', function(event){
	
	var button = $(event.relatedTarget);
	
	var codigoTitulo = button.data('codigo');
	var descricaoTitulo = button.data('descricao');
	
	var modal = $(this);
	var form = modal.find('form');
	var action = form.data('url-base');
	if(!action.endsWith('/')){
		action+= '/';
	}
	
	form.attr('action', action+codigoTitulo);
	
	modal.find('.modal-body span').html('Tem certeza que deseja excluir o título <strong>'+descricaoTitulo +'</strong?>');
});

/*quando a pagina é carregada ele cai nessa função
  event.preventDefault(); - não faça o comportamento default do link que é encaminhar a requisição
*/
$(function(){
	$('[rel="tootip"]').tooltip();
	$('.js-currency').maskMoney({decimal: ',',thousands: '.', allowZero:true});
	
	$('.js-atualizar-status').on('click', function(event){
		event.preventDefault();
		
		var botaoReceber = $(event.currentTarget);
		var urlReceber = botaoReceber.attr('href');
		
		console.log('urlReceber',urlReceber);
		
	});
});