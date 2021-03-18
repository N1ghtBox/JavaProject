var done = false;
var flight = 0;
var activeNumber = 1;
var size = 0;
var allFiles=[];

function change(number){
    activeNumber+=number;
    if(activeNumber>size){
        activeNumber-=size;
    } else if(activeNumber<=0){
        activeNumber=size;
    }
    document.getElementById("numbers").innerText = activeNumber+" z "+size;
    if(allFiles[0]=="null"){return 0;}
    document.getElementById("scroll").style.background = "linear-gradient(rgba(0, 0, 0, 0.3),rgba(0, 0, 0, 0.2)), url(/css/images/"+flight+"/"+allFiles[activeNumber-1]+") no-repeat";
    document.getElementById("scroll").style.backgroundSize = "100% 140%";

}

function getFlightId(flightId,listOfFiles){
    allFiles=listOfFiles.split(",");
    flight = flightId;
    size = allFiles.length;
    change(0);
}
function getPrice(price){
    var people = parseInt(document.getElementById("people").value);
    var nights = parseInt(document.getElementById("nights").value);
    var target = document.getElementById("price");
    if(!people||!nights) return 0;
    target.innerHTML = people*nights*price+" zł";
    if(!done) target.style.transform = "scaleY(1)";
    done=true;
}

