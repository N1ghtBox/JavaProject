function div(name){
    if(name=="Menu"){
         return "<div class="+name+"><button type='submit'><img src='css/images/confirm.png'></button></form></div>";
    }
    if(name=="Id"){
        return "<div class="+name+"><input type='file' name='files' accept='image/png, image/jpeg' multiple></div>";
    }

    return  "<div class="+name+"><input name="+name+"> </div>";
}

function add(id){
    var elements = "<form action='/admin/add' method='POST' enctype='multipart/form-data'><div class='elements'>";
    var a = ["Id","City","Hotel","desc","Price","Rating","Menu"]
    for(i=0; i<a.length;i++){
        elements+= div(a[i]);
    }
    elements+= "</div>";
    var parent = document.createElement("div");
    parent.innerHTML=elements;
    document.getElementById("wrap").insertBefore(parent,document.getElementById("add"));
    document.getElementById("add").style.display="none";
}

