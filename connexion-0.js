//COMPILE.
//Elle sert a creer les composants de base des messages.
function Message(id,auteur,texte,date,comments){
    this.id=id;
    this.auteur=auteur;
    this.texte=texte;
    if(comments==undefined)
        comments=[];
    this.comments=comments;
}

//COMPILE.
//Elle sert a afficher un message d'un utilisateur donne.
Message.prototype.getHTML=function(){
    return "<div id=\"message_"+
        this.id+
        "\"class=\"message\"\n<div class=\"text_message\">"+
        this.texte+
        "</div>\n<div class=\"infos_messages\">\n<span>Poste par<span class=\"link\" onClick=\"javascript:pageUser("+
        this.auteur.id+
        ",)"+
        this.auteur.login+
        ")\">"+
        this.auteur.login+
        "</span>le"+
        this.date+
        "</span><img src=\"cat.jpg\"title=\"afficher les messages\"alt=\"afficher les messages\"onClick=\"javascript:developpeMessage("+
        this.id+
        ")\"/>\n</div>\n<div class=\"comments\">\n</div>\n<div class=\"new_comment\">\n</div>\n</div>";
}

function revival(){
    
}

//COMPILE.
//Faire les deux autres methodes qu'elle appelle pour comprendre son fonctionnement.
function connexion(formulaire){
    var login=formulaire.login.value;
    var password=formulaire.pass.value;
    var ok=verif_formulaire_connexion(login,password);
    if(ok){
        connecte(login,password);
    }
}

//COMPILE.
//Elle sert a remplacer le precedent message d'erreur par le nouveau.
//Pour la tester le mieux serait de creer une page html.
function func_erreur(message){
    var s="<div id=\"msg_err_connexion\">"+message+"</div>";
    var old_msg=$("#msg_err_connexion");
    if(old_msg.length()==0){
        $("form").prepend(s);
    }
    else{
        old_msg.replacewith(s);
    }
    $("#msg_err_connexion").css({"color":"red","margin-top":"20px","margin-bottom":"30px","margin-left":"40px","word-wrap":"break-word"});
}

//COMPILE.
//Elle sert a verifier que ce que l'utilisateur a tape correspond bien aux conditions ie la taille de login et password
function verif_formulaire_connexion(login,password){
    if(login.length==0){
        func_erreur("login obligatoire");
    }
    if(login.length>20){
        func_erreur("taille maximale: 20");
    }
    if(password.length==0){
        func_erreur("password obligatoire");
    }
    if(password.length>20){
        func_erreur("taille maximale: 20");
    }
}