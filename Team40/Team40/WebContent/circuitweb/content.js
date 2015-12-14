	
	
/*function statusreport(elem){
 	var imgURL = chrome.extension.getURL("tick1.png");
	$(elem).append('<img id="theImg" src="'+imgURL+'" />')
}*/

 



$ (document).ready( function() {
  console.log("loaded");

  var observeDOM = (function(){
      var MutationObserver = window.MutationObserver || window.WebKitMutationObserver,
          eventListenerSupported = window.addEventListener;
	 

      return function(obj, callback){
          if( MutationObserver ){
              // define a new observer
              var obs = new MutationObserver(function(mutations, observer){
                  if( mutations[0].addedNodes.length || mutations[0].removedNodes.length ) {
                      callback();
				  }
					  
              });
              // have the observer observe foo for changes in children
              obs.observe( obj, { childList:true, subtree:true });
          }
          else if( eventListenerSupported ){
              obj.addEventListener('DOMNodeInserted', callback, false);
              obj.addEventListener('DOMNodeRemoved', callback, false);
          }
      }
  })();


  if (document.getElementById("feed_visibility_wrapper") !== null) {   //'contentArea'
	  
    observeDOM(document.getElementById("feed_visibility_wrapper") ,function(){

		
        $(".QuestionText").each(function() {	
			
			var elem = this;
			console.log($(elem).text())
			
			if($(this).find(".Analysis").length==0){
				$(this).addClass("analysed");
           		$(this).append("<span class='Analysis'></span>");	
				
		
				
			
			chrome.runtime.sendMessage({requestQues:$(this).text()},
						function (response) {								
							if(response.label=='negative'){
								var imgURL = chrome.extension.getURL("tick1.png");				
								$(elem).append('<img id="theImg" src="'+imgURL+'" />')
								console.log(response.label)	
							}
							else if(response.label=='positive'){
								var imgURL = chrome.extension.getURL("tick.png");				
								$(elem).append('<img id="theImg" src="'+imgURL+'" />')
								console.log(response.label)	
							}
					}
				);					
			}
         
        });

    });
  }
  
  else if(document.getElementsByName(".question_qtext") !== null){
  	      $("h1").each(function() {	
			
			var elem = this;
			console.log($(elem).text())
			
			if($(this).find(".Analysis").length==0){
				$(this).addClass("analysed");
           		$(this).append("<span class='Analysis'></span>");	
				
		
				
			
			chrome.runtime.sendMessage({requestQues:$(this).text()},
						function (response) {								
							if(response.label=='negative'){
								var imgURL = chrome.extension.getURL("tick1.png");				
								$(elem).append('<img id="theImg" src="'+imgURL+'" />')
								console.log(response.label)	
							}
							else if(response.label=='positive'){
								var imgURL = chrome.extension.getURL("tick.png");				
								$(elem).append('<img id="theImg" src="'+imgURL+'" />')
								console.log(response.label)	
							}
					}
				);					
			}
         
        });
  }
});
