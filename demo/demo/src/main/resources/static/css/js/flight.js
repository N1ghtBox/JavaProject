var done = false;
var flight = 0;
var activeNumber = 1;
var size = 0;

function change(number){
    activeNumber+=number;
    if(activeNumber>size){
        activeNumber-=size;
    } else if(activeNumber<=0){
        activeNumber=size;
    }
    document.getElementById("numbers").innerText = activeNumber+" z "+size;
    document.getElementById("scroll").style.background = "linear-gradient(rgba(0, 0, 0, 0.3),rgba(0, 0, 0, 0.2)), url(/css/images/"+flight+"/"+activeNumber+".jpg) no-repeat";
    document.getElementById("scroll").style.backgroundSize = "100% 140%";

}

function getFlightId(flightId,maxSize){
    flight = flightId;
    size = maxSize;
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

