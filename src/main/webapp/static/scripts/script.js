$(function(){
	//срываем все блоки с класом modal (блок регистрации и входа на сайт)
	var hiddenDiv = $('.modal');
	hiddenDiv.hide();
	//при нажатии на ссылку Вход отображаем блоки
	$('.log-in-button').click(function(e){
		$('.modal.in').show();
	});
	$('.log-up-button').click(function(e){
		$('.modal.up').show();
	});
	//при нажатии на значок выхода скрываем блоки
	$('.exit').click(function(e){
		hiddenDiv.hide();
	});

    var wrapper = $( ".file_upload" ),
        inp = wrapper.find( "input" ),
        btn = wrapper.find( "span" ),
        lbl = wrapper.find( "mark" );
    btn.focus(function(){
        inp.focus()
    });
    // Crutches for the :focus style:
    inp.focus(function(){
        wrapper.addClass( "focus" );
    }).blur(function(){
        wrapper.removeClass( "focus" );
    });
    var file_api = ( window.File && window.FileReader && window.FileList && window.Blob ) ? true : false;

    inp.change(function(){
        var file_name;
        if( file_api && inp[ 0 ].files[ 0 ] )
            file_name = inp[ 0 ].files[ 0 ].name;
        else
            file_name = inp.val().replace( "C:\\fakepath\\", '' );

        if( ! file_name.length )
            return;

        if( lbl.is( ":visible" ) ){
            lbl.text( file_name );
            btn.text( "Выбрать" );
        }else
            btn.text( file_name );
    }).change();
});


