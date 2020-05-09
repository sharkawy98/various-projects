$( document ).ready(function() {

    $( "#logForm" ).submit(function( event ) {
    var mail=$("#mail").val();
    var password=$("#password").val();
   
    
    if (password.length !=8 || password=="" || mail==""){
        if(password.length !=8|| password==""){
           $( "#notPass" ).text( "Not valid!" ).show();
           event.preventDefault();
            
        }
        if(mail==""){
           $( "#notMail" ).text( "Not valid!" ).show();
           event.preventDefault();
        
        }
        if(password.length ==8 &&mail=="" ){
             $( "#notMail" ).text( "Not valid!" ).show();
           event.preventDefault();
            
        }
        if(password.length !=8 &&mail!="" ){
             $( "#notPass" ).text( "Not valid!" ).show();
           event.preventDefault();
            
        }
 
    }
    else{
        return true;
        }
});
$( "#signForm" ).submit(function(event  ) {
    var name=$("#name").val();
    var phone=$("#phone").val();
    var mail=$("#mail").val();
    var password=$("#password").val();
   
    
    if (password.length !=8 || password=="" || mail=="" ||name=="" ||phone.length !=11  || phone==""){
        //validate password
        if(password.length !=8|| password==""){
           $( "#notPass" ).text( "Not valid!" ).show();
           event.preventDefault();
            
        }
        if((password.length ==8|| password!="") &&( mail==""|| name=="" ||phone.length !=11|| phone=="")){
           $( "#notPass" ).text( "" ).show();
           event.preventDefault();}
        
        // validate phone
        if(phone.length !=11|| phone=="" ){
           $( "#notPhone" ).text( "Not valid!" ).show();
           event.preventDefault();
            
        }
       
        if((phone.length ==11|| phone!="") &&( mail==""|| name=="" ||password.length !=8|| password=="")){
           $( "#notPhone" ).text( "" ).show();
           event.preventDefault();
            
        }
        // validate mail
        if(mail==""){
           $( "#notMail" ).text( "Not valid!" ).show();
           event.preventDefault();
        
        }
        if((mail!="") &&(phone.length !=11|| phone==""|| name=="" ||password.length !=8|| password=="")){
           $( "#notMail" ).text( "" ).show();
           event.preventDefault();
            
        }
        //vaildate name
        
        if(name==""){
           $( "#notName" ).text( "Not valid!" ).show();
           event.preventDefault();
        
        }
        if((name!="") &&(phone.length !=11|| phone==""|| mail=="" ||password.length !=8|| password=="")){
           $( "#notName" ).text( "" ).show();
           event.preventDefault();
            
        }
        
        
 
    }
    else{
        return true;
        }
});


});



/*
function validateEmail(email) {
    var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return re.test(email);
}


function validatePhone() {
    var content = $("#number").val();
    if (content != "" && (content.length < 11 || content.length > 11 || content[0] != 0 || content[1] != 1)) return false;
    else return true;
}*/